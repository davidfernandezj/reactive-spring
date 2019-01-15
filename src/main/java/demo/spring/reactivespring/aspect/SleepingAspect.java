package demo.spring.reactivespring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;

@Aspect
public class SleepingAspect {

    @Value("${sleepingTime:0}")
    private long sleepingTime;

    @Before("execution(* demo.spring.reactivespring.repository.TweetRepository.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        try {
            System.out.println("SleepingAspect sleeping before hitting: " + joinPoint.getSignature().getName());
            Thread.sleep(sleepingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
