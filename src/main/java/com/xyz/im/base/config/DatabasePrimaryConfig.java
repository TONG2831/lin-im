package com.xyz.im.base.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author xyz
 * @date 2019-07-04
 */
@Configuration
@MapperScan(basePackages = {"com.xyz.im.dao.general", "com.xyz.im.dao.extend"}, sqlSessionFactoryRef = "sqlSessionFactoryPri")
public class DatabasePrimaryConfig {

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactoryPri")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplatePri")
    protected SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
