package net.renfei.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Title: RenFeiConfig</p>
 * <p>Description: 自定义配置</p>
 *
 * @author RenFei
 */
@Data
@Component
@ConfigurationProperties(prefix = "renfei")
public class RenFeiConfig {
    private String version;
    private String siteName;
    private String domain;
    private String staticDomain;
    private DataCahe dataCahe;
    private String ipv4DataPath;
    private String ipv6DataPath;
    private String buildTime;
    private String totpSecret;
    private Aliyun aliyun;
    private Baidu baidu;
    private Google google;
    private WeChat weChat;

    @Data
    public static class DataCahe {
        private Integer expireTime;
    }

    @Data
    public static class Aliyun {
        private String accessKeyId;
        private String accessKeySecret;
        private Oss oss;
        private Green green;

        @Data
        public static class Oss {
            private String endpoint;
            private String bucketName;
            private String downloadBucketName;
            private String downloadHost;
        }

        @Data
        public static class Green{
            private String regionId;
        }
    }

    @Data
    public static class Baidu{
        private String tongji;
    }

    @Data
    public static class Google{
        private String analytics;
        private String ads;
    }

    @Data
    public static class WeChat{
        private String appId;
        private String appSecret;
        private String token;
        private String encodingAESKey;
    }
}
