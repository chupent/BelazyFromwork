package com.belazy.basics.gateway.config.ribbon;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.belazy.library.util.compute.RandomUtil;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CustomIsolationRule extends RoundRobinRule {
    /**
     * 注册中心元数据 版本号
     */
    private static final String METADATA_VERSION = "version";

    /**
     * 优先根据版本号取实例
     */
    @Override
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        String version = LbIsolationContextHolder.getVersion ();
        List<Server> targetList = null;
        List<Server> upList = lb.getReachableServers ();
        if (!StringUtils.isEmpty (version)) { //取指定版本号的实例
            targetList = upList.stream ().filter (server -> version.equals (((NacosServer) server).getMetadata ().get (METADATA_VERSION))).collect (Collectors.toList ());
        }
        if (targetList == null || targetList.size () == 0) {  //只取无版本号的实例
            targetList = upList.stream ().filter (server -> {
                String metadataVersion = ((NacosServer) server).getMetadata ().get (METADATA_VERSION);
                return StringUtils.isEmpty (metadataVersion);
            }).collect (Collectors.toList ());
        }
        if (targetList != null && targetList.size () > 0) {  //随机访问服务
            return getServer (targetList);
        }
        return super.choose (lb, key);
    }

    /**
     * 随机取一个实例
     */
    private Server getServer(List<Server> upList) {
        int nextInt = RandomUtil.randomInt (upList.size ());
        return upList.get (nextInt);
    }
}
