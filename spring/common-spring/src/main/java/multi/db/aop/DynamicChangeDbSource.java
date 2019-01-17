package multi.db.aop;

import multi.db.common.DataSourceConst;
import multi.db.common.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xuguangrong
 * @description aop 类
 * @date Created at 14:43 2019/1/17
 */
@Aspect
@Component
@Order(0)  // 表示在执行sql前就切换数据源
public class DynamicChangeDbSource {

    @Pointcut("execution(public * multi.db.service.TestService.*(..))")
    public void testMethod() {
    };

    @Pointcut("execution(public * multi.db.service.Test1Service.*(..))")
    public void test1Method() {
    };

    @Before("testMethod()")
    public void beforeinv(JoinPoint jp) {
        /*Object[] args = jp.getArgs();
        if(args == null){
            DataSourceContextHolder.setDataSourceType(DataSourceConst.MASTER);
        }*/
        DataSourceContextHolder.setDataSourceType(DataSourceConst.MASTER);
    }

    @Before("test1Method()")
    public void beforegalaxy(JoinPoint jp) {
        DataSourceContextHolder.setDataSourceType(DataSourceConst.SLAVE);
    }
}
