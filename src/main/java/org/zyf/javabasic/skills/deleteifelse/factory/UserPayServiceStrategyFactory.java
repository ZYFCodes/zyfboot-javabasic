package org.zyf.javabasic.skills.deleteifelse.factory;

import org.springframework.util.Assert;
import org.zyf.javabasic.skills.deleteifelse.service.UserPayService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述：获取UserPayService的各个策略类
 * UserPayServiceStrategyFactory中定义了一个Map，用来保存所有的策略类的实例，并提供一个getByUserType方法，可以根据类型直接获取对应的类的实例。
 *
 * @author yanfengzhang
 * @date 2019-12-31 18:28
 */
public class UserPayServiceStrategyFactory {
    private static Map<String, UserPayService> services = new ConcurrentHashMap<String,UserPayService>();

    public  static UserPayService getByUserType(String type){
        return services.get(type);
    }

    public static void register(String userType,UserPayService userPayService){
        Assert.notNull(userType,"userType can't be null");
        services.put(userType,userPayService);
    }

}
