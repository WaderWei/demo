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
@TableName("sys_log")
public class SysLog {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限更新类型 1部门2用户3权限模块4权限5角色6角色用户关系7角色权限关系
     */
    private Integer type;

    /**
     * 目标id 基于type后指定的对象id,比如用户，权限，角色表的主键
     */
    @TableField("target_id")
    private Integer targetId;

    @TableField("old_value")
    private String oldValue;

    @TableField("new_value")
    private String newValue;

    /**
     * 状态 当前是否复原过，0没有1复原过
     */
    private Integer status;

    private String operator;

    @TableField("operate_time")
    private Timestamp operateTime;

    @TableField("operate_ip")
    private String operateIp;
}
