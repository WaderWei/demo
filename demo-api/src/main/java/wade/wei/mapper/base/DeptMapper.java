package wade.wei.mapper.base;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wade.wei.entity.base.SysDept;
import wade.wei.vo.base.DeptVO;

/**
 * @author Administrator
 */
@DS("base")
public interface DeptMapper extends BaseMapper<SysDept> {
    void add(DeptVO deptVO);
}
