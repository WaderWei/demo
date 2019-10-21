package wade.wei.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import wade.wei.bean.PageResultBean;
import wade.wei.bean.ResultBean;
import wade.wei.common.ThreadLocalCommon;
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

    @Pointcut("execution(public wade.wei.bean.PageResultBean *(..))")
    public void handlerPageResultBeanMethod() {
    }

    @Pointcut("execution(public wade.wei.bean.ResultBean *(..))")
    public void handlerResultBeanMethod() {
    }


    @Around(value = "handlerPageResultBeanMethod()")
    public Object handlerPageResultBeanMethod(ProceedingJoinPoint pjp) {
        ThreadLocalCommon.add(System.currentTimeMillis());
        try {
            ThreadLocalCommon.add((PageResultBean<?>) pjp.proceed());
            log.info(pjp.getSignature().getName() + "--->方法执行耗时: " + (System.currentTimeMillis() - ThreadLocalCommon.getLong()));
        } catch (Throwable throwable) {
            ResultBean<?> resultBean = handlerException(pjp, throwable);
            ThreadLocalCommon.add(new PageResultBean<>().setCode(resultBean.getCode()).setMsg(resultBean.getMsg()));
        }
        return ThreadLocalCommon.getPageResultBean();
    }

    @Around(value = "handlerResultBeanMethod()")
    public Object handlerResultBeanMethod(ProceedingJoinPoint pjp) {
        ThreadLocalCommon.add(System.currentTimeMillis());
        try {
            ThreadLocalCommon.add((ResultBean<?>) pjp.proceed());
            log.info(pjp.getSignature().getName() + "--->方法执行耗时: " + (System.currentTimeMillis() - ThreadLocalCommon.getLong()));
        } catch (Throwable throwable) {
            ThreadLocalCommon.add(handlerException(pjp, throwable));
        }
        return ThreadLocalCommon.getResultBean();
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
