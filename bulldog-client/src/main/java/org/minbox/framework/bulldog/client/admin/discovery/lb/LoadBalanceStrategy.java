package org.minbox.framework.bulldog.client.admin.discovery.lb;

import org.minbox.framework.bulldog.client.MinBoxLoggingException;

/**
 * Load balance strategy
 *
 * @author 恒宇少年
 */
public interface LoadBalanceStrategy {
    /**
     * lookup Load-balanced addresses
     *
     * @param adminAddress logging admin address array
     * @return Load-balanced addresses
     * @throws MinBoxLoggingException MinBox Logging Exception
     */
    String lookup(String[] adminAddress) throws MinBoxLoggingException;
}
