package net.renfei.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * <p>Title: PrimaryDataSourceConfig</p>
 * <p>Description: 主数据源配置</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Configuration
@EnableAutoConfiguration(exclude={DruidDataSourceAutoConfigure.class})
@MapperScan(basePackages = "net.renfei.repository", sqlSessionTemplateRef = "PrimarySessionTemplate", annotationClass = Repository.class)
public class PrimaryDataSourceConfig {
    @Primary
    @Bean(name = "PrimaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource PrimaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "PrimarySessionFactory")
    public SqlSessionFactory PrimarySessionFactory(@Qualifier("PrimaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 由于mybatis多数据源的xml扫描需要配置在数据源的配置类里即可，否则会扫描不到。
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name = "PrimaryTransactionManager")
    public DataSourceTransactionManager PrimaryTransactionManager(@Qualifier("PrimaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "PrimarySessionTemplate")
    public SqlSessionTemplate PrimarySessionTemplate(@Qualifier("PrimarySessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
