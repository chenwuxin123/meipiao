package com.example.practice.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 *
 * @Author: Chenwx
 * @Date: 2020/4/1 8:14
 */
@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = MySqlDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class MySqlDataSourceConfig {

    // 精确到 mysql 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.example.practice.dao"; //dao层中mysql文件
    static final String MAPPER_LOCATION = "classpath:mapper/test1/*.xml";

    @Value("${test1.datasource.url}")//配置文件
    private String url;

    @Value("${test1.datasource.username}")
    private String user;

    @Value("${test1.datasource.password}")
    private String password;

    @Value("${test1.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "mysqlDataSource")
    @Primary
    public DataSource mysqlDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(mysqlDataSource());
    }

    @Bean(name = "mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource mysqlDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mysqlDataSource);
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(MySqlDataSourceConfig.MAPPER_LOCATION));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session
                .Configuration();
        configuration.setMapUnderscoreToCamelCase(true);   //驼峰
        return sessionFactory.getObject();
    }

    @Bean(name = "mysqlSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mysqlSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
