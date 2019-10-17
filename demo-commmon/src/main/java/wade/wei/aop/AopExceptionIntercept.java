package wade.wei.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import wade.wei.bean.PageResultBean;
import wade.wei.bean.ResultBean;
import wade.wei.enums.CommonEnum;
import wade.wei.enums.ExceptionEnum;
import wade.wei.exceptions.KnownException;

/**
 * @author Administrator
 * 异常统一处理
 */
@Aspect
@Component
@Slf4j
public class AopExceptionIntercept {
    private ThreadLocal<ResultBean<?>> resultBeanThreadLocal = new ThreadLocal<>();
    private ThreadLocal<PageResultBean<?>> pageResultBeanThreadLocal = new ThreadLocal<>();
    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public wade.wei.bean.PageResultBean *(..))")
    public void handlerPageResultBeanMethod() {
    }

    @Pointcut("execution(public wade.wei.bean.ResultBean *(..))")
    public void handlerResultBeanMethod() {
    }


    @Around(value = "handlerPageResultBeanMethod()")
    public Object handlerPageResultBeanMethod(ProceedingJoinPoint pjp) {
        startTime.set(System.currentTimeMillis());
        try {
            pageResultBeanThreadLocal.set((PageResultBean<?>) pjp.proceed());
            log.info(pjp.getSignature().getName() + "--->方法执行耗时: " + (System.currentTimeMillis() - startTime.get()));
        } catch (Throwable throwable) {
            ResultBean<?> resultBean = handlerException(pjp, throwable);
            pageResultBeanThreadLocal.set(new PageResultBean<>().setCode(resultBean.getCode()).setMsg(resultBean.getMsg()));
        }
        return pageResultBeanThreadLocal.get();
    }

    @Around(value = "handlerResultBeanMethod()")
    public Object handlerResultBeanMethod(ProceedingJoinPoint pjp) {
        startTime.set(System.currentTimeMillis());
        try {
            resultBeanThreadLocal.set((ResultBean<?>) pjp.proceed());
            log.info(pjp.getSignature().getName() + "--->方法执行耗时: " + (System.currentTimeMillis() - startTime.get()));
        } catch (Throwable throwable) {
            resultBeanThreadLocal.set(handlerException(pjp, throwable));
        }
        return resultBeanThreadLocal.get();
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> resultBean = new ResultBean<>();
        log.error(pjp.getSignature().getName() + "---->方法发生异常：" + e.toString());
        if (e instanceof KnownException) {
            ExceptionEnum exceptionEnum = ((KnownException) e).getExceptionEnum();
            resultBean.setCode(exceptionEnum.getCode()).setMsg(exceptionEnum.getMsg());
        } else {
            //未知异常发生后，要及时通知负责人（邮件）
            CommonEnum unknownFail = CommonEnum.UNKNOWN_FAIL;
            resultBean.setCode(unknownFail.getCode()).setMsg(unknownFail.getMsg());
        }
        return resultBean;
    }
}
