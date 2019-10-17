package wade.wei.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Getter
@AllArgsConstructor
public enum CommonEnum {
    SUCCESS(20000, "success"),
    UNKNOWN_FAIL(-1,"未知异常");
    private int code;
    private String msg;
}
