package com.bee.sample.ch5.conf;

import javax.sql.DataSource;

import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.ibeetl.starter.BeetlSqlCustomize;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "dataSource")
    public DataSource datasource(Environment env) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        return ds;
    }


    @Bean
    public BeetlSqlDataSource beetlSqlDataSource(@Qualifier("dataSource") DataSource dataSource) {
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(dataSource);
        return source;
    }

//	@Bean
//	public BeetlSqlCustomize beetlSqlCustomize() {
//		return new BeetlSqlCustomize() {
//			@Override
//			public void customize(SqlManagerFactoryBean sqlManager) {
//				sqlManager.setDbStyle(new MySqlStyle());
//				
//			}
//			
//		};
//	}
}