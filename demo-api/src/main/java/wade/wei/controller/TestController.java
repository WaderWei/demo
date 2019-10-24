package wade.wei.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import wade.wei.bean.ResultBean;
import wade.wei.enums.CommonEnum;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
public class TestController {

    @GetMapping("test")
    public ResultBean<String> test(@Validated User user, BindingResult result) {
        return new ResultBean<>()
                .setCode(CommonEnum.SUCCESS.getCode())
                .setMsg("test")
                .setData("test");
    }

    @PostMapping("user/login")
    public ResultBean<User> login(@Valid User user) {
        System.out.println(user);
        HashMap<String, String> token = new HashMap<>();
        token.put("token", "admin-token");
        return new ResultBean<>()
                .setData(token);
    }

    @GetMapping("user/info")
    public ResultBean<User> userInfo() {
        User user = new User()
                .setName("Super Admin")
                .setIntroduction("I am a super administrator")
                .setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
                .setRoles(Arrays.asList("admin", "editor"));
        return new ResultBean<>()
                .setData(user);

    }

    @PostMapping("user/logout")
    public ResultBean<String> logOut(String token) {
        return new ResultBean<>("success");
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
class User {
    @NotBlank(message = "用户名不能为空")
    @Length(min = 3, max = 6)
    private String username;
    private String password;
    private String introduction;
    private String avatar;
    private String name;
    private List<String> roles = new ArrayList<>();
}
