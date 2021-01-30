package net.renfei.service.aliyun;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import lombok.extern.slf4j.Slf4j;
import net.renfei.config.RenFeiConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class AliyunOSS extends AliyunService {
    public AliyunOSS(RenFeiConfig renFeiConfig) {
        super(renFeiConfig, null);
    }

    public String upload(String path, MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空!");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = UUID.randomUUID().toString().replace("-", "") + suffixName; // 新文件名
        uploadFile(file.getInputStream(), path + fileName);
        return "//" + renFeiConfig.getStaticDomain() + "/" + path + fileName;
    }

    /**
     * 获取签名URL进行临时授权，默认有效期24小时
     *
     * @param objectName 对象地址
     * @return
     */
    public String getPresignedUrl(String objectName) {
        // 设置URL过期时间为24小时。1天(d)=86400000毫秒(ms)
        Date expiration = new Date(System.currentTimeMillis() + 86400000);
        return getPresignedUrl(renFeiConfig.getAliyun().getOss().getDownloadBucketName(), objectName, expiration);
    }

    /**
     * 获取签名URL进行临时授权
     *
     * @param objectName 对象地址
     * @param expiration 授权过期时间
     * @return
     */
    public String getPresignedUrl(String objectName, Date expiration) {
        return getPresignedUrl(renFeiConfig.getAliyun().getOss().getDownloadBucketName(), objectName, expiration);
    }

    /**
     * 获取签名URL进行临时授权
     *
     * @param bucketName 储存桶
     * @param objectName 对象地址
     * @param expiration 授权过期时间
     * @return
     */
    public String getPresignedUrl(String bucketName, String objectName, Date expiration) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder()
                .build(renFeiConfig.getAliyun().getOss().getEndpoint(), renFeiConfig.getAliyun().getAccessKeyId(), renFeiConfig.getAliyun().getAccessKeySecret());
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
        // 关闭OSSClient。
        ossClient.shutdown();
        return renFeiConfig.getAliyun().getOss().getDownloadHost() + url.getPath() + "?" + url.getQuery();
    }

    /**
     * 获取限速下载的签名URL
     *
     * @param bucketName 储存桶
     * @param objectName 对象地址
     * @param expiration 授权过期时间
     * @param speed      限速（单位KB/s）
     * @return
     */
    public String getTrafficLimitUrl(String bucketName, String objectName, Date expiration, int speed) {
        // 限速 （X） KB/s。
        int limitSpeed = speed * 1024 * 8;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder()
                .build(renFeiConfig.getAliyun().getOss().getEndpoint(), renFeiConfig.getAliyun().getAccessKeyId(), renFeiConfig.getAliyun().getAccessKeySecret());
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        request.setExpiration(expiration);
        request.setTrafficLimit(limitSpeed);
        URL url = ossClient.generatePresignedUrl(request);
        ossClient.shutdown();
        return renFeiConfig.getAliyun().getOss().getDownloadHost() + url.getPath() + "?" + url.getQuery();
    }

    private boolean uploadFile(InputStream inputStream, String objectName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder()
                .build(renFeiConfig.getAliyun().getOss().getEndpoint(), renFeiConfig.getAliyun().getAccessKeyId(), renFeiConfig.getAliyun().getAccessKeySecret());
        ossClient.putObject(renFeiConfig.getAliyun().getOss().getBucketName(), objectName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        return true;
    }
}
