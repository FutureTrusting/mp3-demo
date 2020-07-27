package com.fengwenyi.mp3demo.config;

/**
 * @author : Caixin
 * @date 2019/7/26 9:52
 */

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: TokenCleanTimer
 * 数据源 配置文件 账号、密码加密 类
 */
@Configuration
public class DBConfig {

//    @Resource
//    DataSourceProperties properties;
//
//    //	秘钥：随机生成的128位字符串
//    private final static String PWD = "76Y7kHp3OFR2Twwzgo";
//    @Bean
//    public DataSource dataSource() throws Exception {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(properties.getUrl());
//        dataSource.setUsername(properties.getUsername());
////		获取配置中的加密密码，解密之后置入dataSource，使用默认秘钥
//        dataSource.setPassword(SymmetricEncoder.AESDncode(PWD,properties.getPassword()));
//        dataSource.setDriverClassName(properties.getDriverClassName());
//        return dataSource;
//    }

}
