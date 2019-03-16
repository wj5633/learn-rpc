package com.wj5633.framework.cluster;

import com.wj5633.framework.model.ProviderService;

import java.util.List;

/**
 * @author liyebing created on 17/2/12.
 * @version $Id$
 */
public interface ClusterStrategy {

    /**
     * 负载策略算法
     *
     * @param providerServices
     * @return
     */
    public ProviderService select(List<ProviderService> providerServices);
}
