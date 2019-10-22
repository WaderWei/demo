package wade.wei.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * @author Administrator
 * 权限点
 */
@Data
@Accessors(chain = true)
@TableName("sys_role_user")
public class SysRoleUser {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("role_id")
    private Integer roleId;

    @TableField("user_id")
    private Integer userId;

    private String operator;

    @TableField("operate_time")
    private Timestamp operateTime;

    @TableField("operate_ip")
    private String operateIp;
}
