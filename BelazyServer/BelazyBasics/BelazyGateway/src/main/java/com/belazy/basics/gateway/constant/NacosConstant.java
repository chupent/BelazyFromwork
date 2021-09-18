package com.belazy.basics.gateway.constant;

/**
 * @author tangcp
 */
public interface NacosConstant {
    /**
     * nacos dev 地址
     */
    String NACOS_DEV_ADDR = "127.0.0.1:8848";

    /**
     * nacos prod 地址
     */
    String NACOS_PROD_ADDR = "172.30.0.48:8848";

    /**
     * nacos test 地址
     */
    String NACOS_TEST_ADDR = "172.30.0.48:8848";
    /**
     * nacos 配置前缀
     */
    String NACOS_CONFIG_PREFIX = "finance";
    /**
     * nacos 配置文件类型
     */
    String NACOS_CONFIG_FORMAT = "yaml";
    /**
     * nacos json配置文件类型
     */
    String NACOS_CONFIG_JSON_FORMAT = "json";

    /**
     * nacos 是否刷新
     */
    String NACOS_CONFIG_REFRESH = "true";

    /**
     * nacos 分组
     */
    String NACOS_CONFIG_GROUP = "DEFAULT_GROUP";
    /**开发环境*/
    String DEV_CODE = "dev";
    /**生产环境*/
    String PROD_CODE = "prod";
    /**测试环境*/
    String TEST_CODE = "test";
    /**
     * 构建服务对应的 dataId
     *
     * @param appName 服务名
     * @param profile 环境变量
     * @return dataId
     */
    static String dataId(String appName, String profile) {
        return dataId (appName, profile, NACOS_CONFIG_FORMAT);
    }

    /**
     * 构建服务对应的 dataId
     *
     * @param appName 服务名
     * @param profile 环境变量
     * @param format  文件类型
     * @return dataId
     */
    static String dataId(String appName, String profile, String format) {
        return appName + "-" + profile + "." + format;
    }

    /**
     * 动态获取nacos地址
     *
     * @param profile 环境变量
     * @return addr
     */
    static String nacosAddr(String profile) {
        switch (profile) {
            case (PROD_CODE):
                return NACOS_PROD_ADDR;
            case (TEST_CODE):
                return NACOS_TEST_ADDR;
            default:
                return NACOS_DEV_ADDR;
        }
    }

}
