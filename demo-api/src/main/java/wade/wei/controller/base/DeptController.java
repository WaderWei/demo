package wade.wei.controller.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wade.wei.bean.ResultBean;
import wade.wei.dto.TreeDto;
import wade.wei.entity.base.SysDept;
import wade.wei.service.base.DeptService;
import wade.wei.vo.base.DeptVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;


    @GetMapping("")
    public ResultBean<List<TreeDto>> FindAll() {
        return new ResultBean<>(deptService.getDeptTree());
    }

    @PostMapping("/add")
    public ResultBean<SysDept> add(@Valid DeptVO deptVO) {
        return new ResultBean<SysDept>(deptService.add(deptVO));
    }
}
