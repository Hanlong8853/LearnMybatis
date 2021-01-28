package com.lun.c08.interceptor;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;


//完成插件签名，用于拦截哪个对象的哪个方法
@Intercepts({
    @Signature(type=StatementHandler.class,method="parameterize",args=java.sql.Statement.class)
})
public class MyFirstPlugin implements Interceptor {
    /**
     * intercept:拦截
     * */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("myfirstplugin...intercept：" + invocation.getMethod());
        Object target = invocation.getTarget();
        System.out.println("当前拦截到的对象："+target);
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("sql语句中的参数是："+value);
        metaObject.setValue("parameterHandler.parameterObject", 3);
        //执行目标方法
        Object proceed = invocation.proceed();
        //返回执行后的返回值
        return proceed;
    }
    
    //包装目标对象，为目标对象创建一个代理对象
    @Override
    public Object plugin(Object target) {
        System.out.println("-->myfirstplugin...plugin，将要包装的对象："+target);
        // TODO Auto-generated method stub
        Object wrap = Plugin.wrap(target, this);
        //返回为当前target创建的动态代理
        return wrap;
    }
    
    //将插件注册时的property属性设置进来
    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub
        System.out.println("插件配置的信息："+properties);
    }

}
