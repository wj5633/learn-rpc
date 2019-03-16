package com.wj5633.framework.cluster.impl;

import com.wj5633.framework.cluster.ClusterStrategy;
import com.wj5633.framework.model.ProviderService;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * 软负载随机算法实现
 *
 * @author liyebing created on 17/2/12.
 * @version $Id$
 */
public class RandomClusterStrategyImpl implements ClusterStrategy {
    @Override
    public ProviderService select(List<ProviderService> providerServices) {
        int MAX_LEN = providerServices.size();
        int index = RandomUtils.nextInt(0, MAX_LEN - 1);
        return providerServices.get(index);
    }

}
