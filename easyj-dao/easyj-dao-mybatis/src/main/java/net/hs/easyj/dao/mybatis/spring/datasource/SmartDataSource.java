package net.hs.easyj.dao.mybatis.spring.datasource;

import org.aspectj.lang.JoinPoint;

import java.util.HashMap;
import java.util.Map;

/**
 * 智能数据源支持多数据源的智能切换
 *
 * @author Gavin Hu
 * @create 2015/6/5
 */
public class SmartDataSource extends DynamicDataSource {

    private Map<String, String> routeTable = new HashMap<>();

    private Map<String, String> routeTableCache = new HashMap<>();

    public void setRouteTable(Map<String, String> routeTable) {
        this.routeTable = routeTable;
    }

    public void select(JoinPoint joinPoint) throws Throwable {
        //
        Class targetClass = joinPoint.getTarget().getClass();
        Object[] targetArgs = joinPoint.getArgs();
        //
        String dataSourceKey = lookupDataSourceKey(targetClass, targetArgs);
        //
        setDataSourceKey(dataSourceKey);
    }

    protected String lookupDataSourceKey(Class targetClass, Object[] targetArgs) {
        String className = targetClass.getName();
        //
        if(routeTableCache.containsKey(className)) {
            return routeTableCache.get(className);
        }
        //
        String dataSourceKey = null;
        for(String pattern : routeTable.keySet()) {
            if(className.matches(pattern)) {
                dataSourceKey = routeTable.get(pattern);
                break;
            }
        }
        //
        routeTableCache.put(className, dataSourceKey);
        return dataSourceKey;
    }

}
