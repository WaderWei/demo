package wade.wei.entity.base;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * @author Administrator
 * 权限点
 */
@Data
@Accessors(chain = true)
@TableName("sys_dept")
public class SysDept {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    @TableField("parent_id")
    private Integer parentId;

    private String level;

    /**
     * 删除标志 0 未删除  1删除
     */
    @TableField("delete_flag")
    private Integer deleteFlag;

    private Integer seq;

    private String remark;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String operator;

    @TableField(value = "operate_time", fill = FieldFill.INSERT_UPDATE)
    private Timestamp operateTime;

    @TableField(value = "operate_ip", fill = FieldFill.INSERT_UPDATE)
    private String operateIp;
}
