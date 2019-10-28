package wade.wei.controller.base;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wade.wei.bean.ResultBean;
import wade.wei.entity.base.SysUser;

import java.util.List;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public ResultBean<String> login(SysUser sysUser) {
        return new ResultBean<>().setCode(20000).setData("admin-token");
    }

    @GetMapping("/info")
    public ResultBean<User> login(String token) {
        return new ResultBean<>().setCode(20000).setData(new User("wade","123",
                "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
                Lists.newArrayList("admin")));
    }
}

@Data
@AllArgsConstructor
class User {
    private String name;
    private String introduction;
    private String avatar;
    private List<String> roles;
}
