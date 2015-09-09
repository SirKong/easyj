package net.hs.easyj.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.expression.MapAccessor;
import org.springframework.core.Ordered;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 日志切面
 *
 * @author Gavin Hu
 * @create 2015/9/4
 */
public class LoggerAspect implements Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    private String levelBefore = "info";

    private String levelAfter = "info";

    private String levelAfterReturning = "info";

    private String levelAfterThrowing = "error";

    private Map<String, String> before = new LinkedHashMap<>();

    private Map<String, String> after = new LinkedHashMap<>();

    private Map<String, String> afterReturning = new LinkedHashMap<>();

    private Map<String, String> afterThrowing = new LinkedHashMap<>();

    private ThreadLocal<Map<String, String>> definitionLocal = new ThreadLocal<>();

    private ExpressionParser expressionParser = new ExpressionParser();

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }

    public void setLevelBefore(String levelBefore) {
        this.levelBefore = levelBefore.toLowerCase();
    }

    public void setLevelAfter(String levelAfter) {
        this.levelAfter = levelAfter.toLowerCase();
    }

    public void setLevelAfterReturning(String levelAfterReturning) {
        this.levelAfterReturning = levelAfterReturning.toLowerCase();
    }

    public void setLevelAfterThrowing(String levelAfterThrowing) {
        this.levelAfterThrowing = levelAfterThrowing.toLowerCase();
    }

    public void setBefore(Map<String, String> before) {
        this.before = before;
    }

    public void setAfter(Map<String, String> after) {
        this.after = after;
    }

    public void setAfterReturning(Map<String, String> afterReturning) {
        this.afterReturning = afterReturning;
    }

    public void setAfterThrowing(Map<String, String> afterThrowing) {
        this.afterThrowing = afterThrowing;
    }

    public Object around(ProceedingJoinPoint joinPoint) {
        Object returnValue = null;
        try {
            before(joinPoint);
            //
            returnValue = joinPoint.proceed();
            //
            after(joinPoint);
            //
            afterReturning(joinPoint, returnValue);
            //
        } catch (Exception exception) {
            after(joinPoint);
            //
            afterThrowing(joinPoint, exception);
            //
        } finally {
            return returnValue;
        }
    }

    public void before(JoinPoint joinPoint) {
        Map<String, Object> context = getContext(joinPoint, null, null);
        String methodName = joinPoint.getSignature().getName();
        String expression = before.get(methodName);
        String message = parseExpression(context, expression);
        //
        log(joinPoint, levelBefore, message, null);
    }

    public void after(JoinPoint joinPoint) {
        Map<String, Object> context = getContext(joinPoint, null, null);
        String methodName = joinPoint.getSignature().getName();
        String expression = after.get(methodName);
        String message = parseExpression(context, expression);
        //
        log(joinPoint, levelAfter, message, null);

    }

    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        Map<String, Object> context = getContext(joinPoint, null, null);
        String methodName = joinPoint.getSignature().getName();
        String expression = afterReturning.get(methodName);
        String message = parseExpression(context, expression);
        //
        log(joinPoint, levelAfterReturning, message, null);
    }

    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        Map<String, Object> context = getContext(joinPoint, null, null);
        String methodName = joinPoint.getSignature().getName();
        String expression = afterThrowing.get(methodName);
        String message = parseExpression(context, expression);
        //
        log(joinPoint, levelAfterThrowing, message, null);
    }

    private String parseExpression(Map<String, Object> context, String expression) {
        try {
            return expressionParser.parse(context, expression, String.class);
        } catch (Exception e) {
            //
            logger.error("日志表达式解析失败！", e);
            //
            return "日志表达式解析失败！";
        }
    }

    private void log(JoinPoint joinPoint, String level, String message, Exception exception) {
        Logger logger = getLogger(joinPoint);
        //
        switch (level) {
            case "trace":
                logger.trace(message, exception);
                break;
            case "debug":
                logger.debug(message, exception);
                break;
            case "info":
                System.out.println(logger.getName());
                logger.info(message, exception);
                break;
            case "warn":
                logger.warn(message, exception);
                break;
            case "error":
                logger.error(message, exception);
                break;
            default:
                break;
        }

    }

    private Map<String, Object> getContext(JoinPoint joinPoint, Object returnValue, Exception exception) {
        Map<String, Object> context = new HashMap<>();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            context.put("argument" + i, joinPoint.getArgs()[i]);
        }
        //
        if (returnValue != null) {
            context.put("returnValue", returnValue);
        }
        if (exception != null) {
            context.put("exception", exception);
        }
        //
        return context;
    }

    private Logger getLogger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getTarget().getClass());
    }

    private class ExpressionParser {

        private SpelExpressionParser expressionParser = new SpelExpressionParser();

        private StandardEvaluationContext evaluationContext = new StandardEvaluationContext();

        private Map<String, Expression> expressionMap = new HashMap<String, Expression>();

        public ExpressionParser() {
            evaluationContext.addPropertyAccessor(new MapAccessor());
        }

        public <T> T parse(Object rootObject, String expressionString, Class<T> requiredType) {
            //
            Expression expression = expressionMap.get(expressionString);
            if (expression == null) {
                expression = expressionParser.parseExpression(expressionString, new TemplateParserContext("${","}"));
                expressionMap.put(expressionString, expression);
            }
            //
            evaluationContext.setRootObject(rootObject);
            return expression.getValue(evaluationContext, requiredType);
        }

    }

}
