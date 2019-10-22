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
@TableName("sys_acl")
public class SysAcl {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    @TableField("acl_module_id")
    private Integer aclModuleId;

    private String url;

    /**
     * 类型1. 菜单 2. 按钮 3. 其它
     */
    private Integer type;

    /**
     * 状态 0. 冻结 1. 正常
     */
    private Integer status;

    /**
     * 删除标志 0 未删除  1删除
     */
    @TableField("delete_flag")
    private Integer deleteFlag;

    private Integer seq;

    private String remark;

    private String operator;

    @TableField("operate_time")
    private Timestamp operateTime;

    @TableField("operate_ip")
    private String operateIp;
}
