package wade.wei.vo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class DeptVO {
    private Integer id;

    @NotBlank(message = "部门名称不能为空")
    @Length(min = 2, max = 15, message = "部门名称的长度需要在2-15个字之间")
    private String name;

    private Integer parentId;

    @NotNull(message = "展示顺序不能为空")
    private Integer seq;

    @Length(max = 200, message = "备注的长度只能在200字之内")
    private String remark;
}
