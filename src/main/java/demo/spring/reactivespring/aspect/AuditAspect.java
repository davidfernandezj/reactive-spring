package demo.spring.reactivespring.aspect;

import demo.spring.reactivespring.service.TweetService;
import java.time.Duration;
import java.time.Instant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
//@Aspect
public class AuditAspect {

    private static final Logger LOG = LoggerFactory.getLogger(TweetService.class);

    @Around("execution(public * demo.spring.reactivespring.repository.*.*(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringType().getName();
        Instant from = Instant.now();
        joinPoint.proceed();
        Instant to = Instant.now();
        LOG.info("Repository " + className + "::" + joinPoint.getSignature().getName() + " took "  +
            Duration.between(from, to).toMillis() + " ms");

    }

}
