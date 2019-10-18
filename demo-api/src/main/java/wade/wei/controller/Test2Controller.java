package wade.wei.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wade.wei.bean.ResultBean;
import wade.wei.enums.CommonEnum;

/**
 * @author Administrator
 */
@RestController("test2")
public class Test2Controller {
    @GetMapping("t")
    public ResultBean<String> test() {
        return new ResultBean<>("123");
    }
}
