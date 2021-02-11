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
    private String active;
    private String siteName;
    private String domain;
    private String staticDomain;
    private DataCahe dataCahe;
    private String ipv4DataPath;
    private String ipv6DataPath;
    private String buildTime;
    private String totpSecret;
    private String authMode;
    private Aliyun aliyun;
    private Baidu baidu;
    private Google google;
    private WeChat weChat;
    private Jwt jwt;
    private UCenter uCenter;
    private Cloudflare cloudflare;
    private LetsEncrypt letsEncrypt;

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
        private Sms sms;

        @Data
        public static class Oss {
            private String endpoint;
            private String bucketName;
            private String downloadBucketName;
            private String downloadHost;
        }

        @Data
        public static class Green {
            private String regionId;
        }

        @Data
        public static class Sms {
            private String regionId;
            private String signName;
            private String templateCode;
        }
    }

    @Data
    public static class Baidu {
        private String tongji;
    }

    @Data
    public static class Google {
        private String analytics;
        private String ads;
        private ReCAPTCHA reCAPTCHA;

        @Data
        public static class ReCAPTCHA {
            private String clientKey;
            private String serverKey;
        }
    }

    @Data
    public static class WeChat {
        private String appId;
        private String appSecret;
        private String token;
        private String encodingAESKey;
    }

    @Data
    public static class Jwt {
        private String secret;
        private String issuer;
        private Long expiration;
    }

    @Data
    public static class UCenter {
        private String api;
        private String key;
        private String appId;
        private String connect;
    }

    @Data
    public static class Cloudflare {
        private String apiToken;
    }

    @Data
    public static class LetsEncrypt {
        private String email;
        private String dirPath;
    }
}
