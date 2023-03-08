package com.longyao.config;//package com.labelImg.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.ResourcePatternResolver;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = "com.labelimg.dao",sqlSessionTemplateRef="dddddSqlSessionTemplate")
//public class DataSourceConfig{
//
//    //数据源
//    @Primary
//    @Bean(name = "dddddDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.ddddd")
//    public DataSource dataSouce(){
//        return DruidDataSourceBuilder.create().bulid();
//    }
//
//    @Primary
//    @Bean(name = "dddddSqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("dddddDataSource")DataSource dataSource){
//        SqlSessionFactoryBaen bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        ResourcePatternResolver reslover = new PathMatchingResoucePatternResolver();
//        try{
//            bean.setMapperLocations(resolver.getResource("classpath:mapper/*Mapper.xml"));
//            return bean.getObject();
//        }catch(Exception e){
//            e.printStackTrace();
//            throw new RuntimeException();
//        }
//    }
//
//    @Bean(name = "dddddSqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("SqlSessionFactory")SqlSessionFactory sqlSessionFactory){
//        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
//        return template;
//    }
//}
