package com.wj5633.client.config;

import com.wj5633.common.service.IUserService;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * @author wj
 * @create 2018-09-06 20:14
 **/

@SpringBootConfiguration
public class WebConfiguration {
//    @Bean("userService")
//    public RmiProxyFactoryBean userService() {
//        RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();
//        proxy.setServiceUrl("rmi://127.0.0.1:1199/userRmiService");
//        proxy.setServiceInterface(IUserService.class);
//        return proxy;
//    }

//    @Bean("userService")
//    public HttpInvokerProxyFactoryBean userService() {
//        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
//        proxy.setServiceUrl("http://127.0.0.1:8080/user.httpInvoker");
//        proxy.setServiceInterface(IUserService.class);
//        return proxy;
//    }

    @Bean("userService")
    public HessianProxyFactoryBean userService() {
        HessianProxyFactoryBean proxy = new HessianProxyFactoryBean();
        proxy.setServiceUrl("http://127.0.0.1:8080/user.hessianInvoker");
        proxy.setServiceInterface(IUserService.class);
        return proxy;
    }

}
