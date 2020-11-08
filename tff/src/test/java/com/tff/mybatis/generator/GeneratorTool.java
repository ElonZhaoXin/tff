package com.tff.mybatis.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;

public class GeneratorTool {
    public static void main(String[] args) throws Exception{
        new GeneratorTool().mySql();
    }
    @Test
    public void mySql() throws SQLException {
        AutoGenerator auto = new AutoGenerator();
        auto.setDataSource(mysqlDataSourceConfig())    //数据库配置
            .setGlobalConfig(globalConfig())        //自动生成目录配置
            .setPackageInfo(packageConfig())        //输出包名配置
            .setStrategy(strategyConfig())          //表与映射配置
            .setTemplateEngine(new FreemarkerTemplateEngine())  //使用模板配置
            .setTemplate(templateConfig())
            .setCfg(injectionConfig(auto))          //自定义模板时，注入参数配置
            .execute();
    }

    /**
     * H2数据源配置
     */
    private DataSourceConfig h2DataSourceConfig() throws SQLException {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:h2:mem:tff;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;CASE_INSENSITIVE_IDENTIFIERS=TRUE")
                .setUsername("sa")
                .setPassword("")
                .setDriverName(org.h2.Driver.class.getName())
                .setDbType(DbType.H2)
                .setSchemaName("PUBLIC");
        runScript(dataSourceConfig);
        return dataSourceConfig;
    }

    private void runScript(DataSourceConfig dataSourceConfig) throws SQLException {
        try (Connection connection = dataSourceConfig.getConn()) {
            InputStream inputStream = GeneratorTool.class.getResourceAsStream("/db/init.sql");
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.setAutoCommit(true);
            scriptRunner.runScript(new InputStreamReader(inputStream));
        }
    }

    /**
     * Mysql数据源配置
     */
    private DataSourceConfig mysqlDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                .setUrl("jdbc:mysql://localhost:3306/tff?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8")
                .setUsername("root")
                .setPassword("root")
                .setDriverName(com.mysql.cj.jdbc.Driver.class.getName())
                .setDbType(DbType.MYSQL);
        return dataSourceConfig;
    }

    /**
     * 全局配置
     */
    private GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        //生成代码输出路径
        globalConfig.setOutputDir("F:"+ File.separator+ "codegen")
                .setAuthor("tff")
                .setOpen(true)  //生成后打开文件夹
                .setFileOverride(true);
        return globalConfig;
    }

    /**
     * 包配置
     */
    private PackageConfig packageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.tff")
                     .setModuleName("account");
        return packageConfig;
    }

    /**
     * 策略配置
     */
    private StrategyConfig strategyConfig() {
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("account")   //表名
                .setTablePrefix("t_")   //去掉前缀
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true);
        return strategyConfig;
    }

    /**
     * 自定义模板配置
     */
    private TemplateConfig templateConfig() {
        return new TemplateConfig()
                .disable(TemplateType.CONTROLLER);  //不生成controller
    }

    /**
     * 自定义配置
     */
    private InjectionConfig injectionConfig(AutoGenerator auto) {
        return new InjectionConfig(){
            @Override
            public void initMap() {
                //添加自定义模板的上下文参数
                //模板中获取到自定义的-->${foo}
                this.setMap(Collections.singletonMap("foo", "bar"));
            }
        };
    }
}
