package wade.wei.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.apache.bcel.classfile.Code;

/**
 * @author Administrator
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParamException extends RuntimeException {
    private Integer code;
    private String msg;
}
