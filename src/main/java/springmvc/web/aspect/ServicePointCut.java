package springmvc.web.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ServicePointCut {

	private Log log = LogFactory.getLog(getClass());

	@Pointcut("execution(* springmvc.web.service.*.*(..))")
	public void businessService() {
	}

	@Before("businessService()")
	public void beforeService() {
		log.info("before point invoked");
	}

	@After("execution(* springmvc.web.service.*.*(..))")
	public void afterService() {
		log.info("after point invoked");
	}

	@Around("businessService()")
	public Object aroundService(ProceedingJoinPoint joinpoint) throws Throwable {
		log.info("before around point invoked");
		Object obj = joinpoint.proceed();
		log.info("after around point invoked");
		return obj;
	}

}
