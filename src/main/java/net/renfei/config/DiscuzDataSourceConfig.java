package net.renfei.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
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
 * <p>Title: DiscuzDataSourceConfig</p>
 * <p>Description: Discuz论坛数据源配置</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Configuration
@MapperScan(basePackages = "net.renfei.discuz.repository", sqlSessionTemplateRef = "DiscuzSessionTemplate", annotationClass = Repository.class)
public class DiscuzDataSourceConfig {
    @Bean(name = "DiscuzDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.discuz")
    public DataSource DiscuzDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "DiscuzSessionFactory")
    public SqlSessionFactory DiscuzSessionFactory(@Qualifier("DiscuzDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "DiscuzTransactionManager")
    public DataSourceTransactionManager DiscuzTransactionManager(@Qualifier("DiscuzDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "DiscuzSessionTemplate")
    public SqlSessionTemplate DiscuzSessionTemplate(@Qualifier("DiscuzSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
