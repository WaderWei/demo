package wade.wei.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import wade.wei.bean.PageResultBean;
import wade.wei.bean.ResultBean;

/**
 * @author Administrator
 * 线程级别的值
 */
public class ThreadLocalCommon {
    private final static ThreadLocal<ResultBean<?>> resultBeanThreadLocal = new ThreadLocal<>();
    private final static ThreadLocal<PageResultBean<?>> pageResultBeanThreadLocal = new ThreadLocal<>();
    private final static ThreadLocal<Long> startTime = new ThreadLocal<>();

    public static void add(ResultBean resultBean){
        resultBeanThreadLocal.set(resultBean);
    }

    public static void add(PageResultBean pageResultBean){
        pageResultBeanThreadLocal.set(pageResultBean);
    }

    public static void add(Long time){
        startTime.set(time);
    }

    public static ResultBean getResultBean() {
        return resultBeanThreadLocal.get();
    }

    public static PageResultBean getPageResultBean() {
        return pageResultBeanThreadLocal.get();
    }

    public static Long getLong() {
        return startTime.get();
    }

    public static void remove(){
        resultBeanThreadLocal.remove();
        pageResultBeanThreadLocal.remove();
        startTime.remove();
    }
}
