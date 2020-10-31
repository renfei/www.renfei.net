package net.renfei.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.time.Duration;

/**
 * <p>Title: RedisConfig</p>
 * <p>Description: Redis缓存配置类</p>
 *
 * @author RenFei
 */
@Slf4j
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    private final RenFeiConfig renFeiConfig;

    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;

    public RedisConfig(RenFeiConfig renFeiConfig) {
        this.renFeiConfig = renFeiConfig;
    }

    /**
     * 设置自定义key{ClassName + methodName + params}
     *
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(",Method:");
            sb.append(method.getName());
            sb.append(",Params[");
            for (int i = 0; i < params.length; i++) {
                if (params[i] != null) {
                    sb.append(params[i].toString());
                    if (i != (params.length - 1)) {
                        sb.append(",");
                    }
                }
            }
            sb.append("]");
            log.debug("Data Caching Redis Key : {}", sb.toString());
            return sb.toString();
        };
    }

    /**
     * 自定义keyGenerator，Key生成器
     *
     * @return
     */
    @Bean
    public KeyGenerator updateByIdkeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(",Method:");
            sb.append("getById");
            sb.append(",Params[");
            try {
                Field id = params[0].getClass().getDeclaredField("id");
                id.setAccessible(true);
                sb.append(id.get(params[0]).toString());
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
            sb.append("]");
            log.debug("Data Caching Redis Key : {}", sb.toString());
            return sb.toString();
        };
    }

    /**
     * 自定义keyGenerator，Key生成器
     *
     * @return
     */
    @Bean
    public KeyGenerator deleteByIdkeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(",Method:");
            sb.append("getById");
            sb.append(",Params[");
            for (int i = 0; i < params.length; i++) {
                sb.append(params[i].toString());
                if (i != (params.length - 1)) {
                    sb.append(",");
                }
            }
            sb.append("]");
            log.debug("Data Caching Redis Key : {}", sb.toString());
            return sb.toString();
        };
    }


    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        GenericFastJsonRedisSerializer serializer = new GenericFastJsonRedisSerializer();
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(serializer)
        ).entryTtl(Duration.ofSeconds(renFeiConfig.getDataCahe().getExpireTime()));
        log.debug("Redis 缓存过期时间 : {}", renFeiConfig.getDataCahe().getExpireTime());
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 设置序列化
        GenericFastJsonRedisSerializer serializer = new GenericFastJsonRedisSerializer();
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY);
        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
