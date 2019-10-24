package wade.wei.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ExceptionEnum {
    ILLEGAL_TOKEN(50008, "非法的token"),
    LOGGED_OUT(50012, "其他客户端登录了"),
    EXPIRED_TOKEN(50014, "Token过期了"),
    NO_PERMISSION(60008, "无权限"),
    PARAM_ERROR(7001, "参数错误");
    private int code;
    private String msg;
}
