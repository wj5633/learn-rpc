package com.wj5633.framework.cluster.impl;

import com.wj5633.framework.helper.IPHelper;
import com.wj5633.framework.cluster.ClusterStrategy;
import com.wj5633.framework.model.ProviderService;

import java.util.List;

/**
 * 软负载哈希算法实现
 *
 * @author liyebing created on 17/4/23.
 * @version $Id$
 */
public class HashClusterStrategyImpl implements ClusterStrategy {

    @Override
    public ProviderService select(List<ProviderService> providerServices) {
        //获取调用方ip
        String localIP = IPHelper.localIp();
        //获取源地址对应的hashcode
        int hashCode = localIP.hashCode();
        //获取服务列表大小
        int size = providerServices.size();

        return providerServices.get(hashCode % size);
    }
}
