package utils.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class WithUser {


    @Around("call(*)")
    public void aroundSetMethods (JoinPoint jp)
    {
        System.out.println ("aroundSetMethod called");
    }

}
