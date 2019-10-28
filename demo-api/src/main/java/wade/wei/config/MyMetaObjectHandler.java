package wade.wei.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author Administrator
 * 自动填充
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    private final String operator = "operator";
    private final String operateTime = "operateTime";
    private final String operateIp = "operateIp";

    @Override
    public void insertFill(MetaObject metaObject) {
        commonHandler(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        commonHandler(metaObject);
    }

    private void commonHandler(MetaObject metaObject) {
        setField(operator, "wade", metaObject);
        setField(operateTime, new Timestamp(System.currentTimeMillis()), metaObject);
        setField(operateIp, "192.168.0.120", metaObject);
    }

    /**
     * 判断这个表中有此字段并且这个字段为null才自动填充
     *
     * @param field
     * @param t
     * @param metaObject
     * @param <T>
     */
    private <T> void setField(String field, T t, MetaObject metaObject) {
        if (metaObject.hasSetter(field) && getFieldValByName(field, metaObject) == null) {
            this.setFieldValByName(field, t, metaObject);
        }
    }
}
