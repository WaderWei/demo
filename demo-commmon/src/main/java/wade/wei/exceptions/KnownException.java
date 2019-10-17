package wade.wei.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wade.wei.enums.ExceptionEnum;

/**
 * @author Administrator
 * 已知异常
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class KnownException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}
