package wade.wei.mapper.business;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wade.wei.entity.business.Role;

/**
 * @author Administrator
 */
@DS("business")
public interface DemoMapper extends BaseMapper<Role> {
}
