package demo.spring.reactivespring.aspect;

import demo.spring.reactivespring.service.TweetService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditAspect {

    private static final Logger LOG = LoggerFactory.getLogger(TweetService.class);

    @Before("execution(public * demo.spring.reactivespring.repository.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringType().getName();
        LOG.info("Repository " + className + "::" + joinPoint.getSignature().getName() + " is being called");
    }

}
