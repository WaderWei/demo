package wade.wei.service.base;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wade.wei.dto.TreeDto;
import wade.wei.entity.base.SysDept;
import wade.wei.enums.ExceptionEnum;
import wade.wei.exceptions.ParamException;
import wade.wei.mapper.base.DeptMapper;
import wade.wei.vo.base.DeptVO;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private TreeService treeService;


    public SysDept add(DeptVO deptVO) {
        if (checkExist(deptVO)) {
            throw new ParamException(ExceptionEnum.PARAM_ERROR.getCode(), "同一层级下存在相同的部门名称");
        }
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(deptVO, sysDept);
        deptMapper.insert(sysDept);
        return sysDept;
    }

    /**
     * 检查是否存在相同的部门名称
     *
     * @param deptVO
     * @return
     */
    private Boolean checkExist(DeptVO deptVO) {
        return deptMapper.selectCount(Wrappers.<SysDept>lambdaQuery()
                .eq(SysDept::getParentId, deptVO.getParentId())
                .eq(SysDept::getName, deptVO.getName())) > 0;
    }

    public List<TreeDto> getDeptTree() {
        List<SysDept> sysDepts = this.FindAll();
        List<TreeDto> treeDtoList = Lists.newArrayList();
        for (SysDept sysDept : sysDepts) {
            treeDtoList.add(TreeDto.adapt(sysDept));
        }
        return treeService.GenerateTree(treeDtoList);
    }

    /**
     * 所有的部门信息
     *
     * @return
     */
    public List<SysDept> FindAll() {
        return deptMapper.selectList(null);
    }
}
