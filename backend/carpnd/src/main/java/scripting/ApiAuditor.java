package scripting;


import org.apache.log4j.Logger;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import java.util.LinkedList;
import java.util.List;

@Aspect
public class ApiAuditor {

    private static Logger LOGGER = Logger.getLogger("api.rest.info");

    @Before("execution(* api..*(..))")
    public void auditRestMethod(JoinPoint jp) throws Throwable {
        String target = jp.getTarget().getClass().getName();
        String invokingMethodName = jp.getSignature().getName();
        Object[] signatureArgs = jp.getArgs();
        String args = buildStringArgs(signatureArgs);
        String toLog = target + "." + invokingMethodName + "(" + args + ")";
        LOGGER.info(toLog);
    }

    private String buildStringArgs(Object[] args) {
        if (args.length == 0) {
            return "";
        } else {
            List<String> stringArgs = new LinkedList<String>();
            for (Object signatureArg: args) {
                stringArgs.add(signatureArg.toString());
            }
            return "Parameters: ".concat(String.join(", ", stringArgs));
        }
    }

}