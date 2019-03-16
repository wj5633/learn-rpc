package com.wj5633.server.config;

import com.wj5633.common.service.IUserService;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wj
 * @create 2018-09-06 20:14
 **/

@SpringBootConfiguration
public class WebConfiguration extends WebMvcConfigurationSupport {

    //    @Bean
    public RmiServiceExporter rmiServiceExporter(IUserService service) {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("userRmiService");
        exporter.setRegistryPort(1199);
        exporter.setService(service);
        exporter.setServiceInterface(IUserService.class);
        return exporter;
    }

    @Bean
    public HessianServiceExporter hessianServiceExporter(IUserService service) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(service);
        exporter.setServiceInterface(IUserService.class);
        return exporter;
    }

//    @Bean
    public HttpInvokerServiceExporter httpInvokerServiceExporter(IUserService service) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(service);
        exporter.setServiceInterface(IUserService.class);
        return exporter;
    }

    @Bean
    protected HandlerMapping handlerMapping() {
        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
        handlerMapping.setOrder(3);
        Map<String, String> urlMap = new HashMap<>();
//        urlMap.put("/user.httpInvoker", "httpInvokerServiceExporter");
        urlMap.put("/user.hessianInvoker", "hessianServiceExporter");
        handlerMapping.setUrlMap(urlMap);
        return handlerMapping;
    }

}
