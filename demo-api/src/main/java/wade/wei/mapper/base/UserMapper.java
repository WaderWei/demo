package wade.wei.mapper.base;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wade.wei.entity.base.User;

/**
 * @author Administrator
 */
@DS("base")
public interface UserMapper extends BaseMapper<User> {

}
