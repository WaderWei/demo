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
@TableName("sys_user")
public class SysUser {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String telephone;

    private String mail;

    private String password;

    @TableField("dept_id")
    private Integer deptId;

    /**
     * 状态 0. 冻结 1. 正常
     */
    private Integer status;

    /**
     * 删除标志 0 未删除  1删除
     */
    @TableField("delete_flag")
    private Integer deleteFlag;

    private String remark;

    private String operator;

    @TableField("operate_time")
    private Timestamp operateTime;

    @TableField("operate_ip")
    private String operateIp;
}
