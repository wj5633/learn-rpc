package com.wj5633.rpc.invoke;

import com.wj5633.rpc.framework.ProviderReflect;
import com.wj5633.rpc.service.HelloService;
import com.wj5633.rpc.service.HelloServiceImpl;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/2/28 2:59
 * @description
 */

public class RpcProviderMain {
    public static void main(String[] args) throws IOException {
        HelloService service = new HelloServiceImpl();
        ProviderReflect.provider(service, 8083);
    }
}
