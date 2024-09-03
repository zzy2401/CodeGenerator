package com.zzy;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class CodeGenerator {

    // Database configuration variables
    static String databaseHost = "localhost";
    static String databasePort = "3306";
    static String databaseName = "your_database";
    static String databaseUser = "root";
    static String databasePassword = "your_password";

    public static void main(String[] args) {
        new AutoGenerator()
                .setGlobalConfig(globalConfig())
                .setDataSource(dsc())
                .setPackageInfo(packageConfig())
                .setStrategy(strategyConfig())
                .execute();
    }

    private static GlobalConfig globalConfig() {
        return new GlobalConfig()
                .setOutputDir(System.getProperty("user.dir") + "/src/main/java")
                .setFileOverride(true)
                .setOpen(false)
                .setAuthor("zzy")
                .setIdType(IdType.AUTO)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setServiceName("%sService")
                .setDateType(DateType.ONLY_DATE);
    }

    private static DataSourceConfig dsc() {

        return new DataSourceConfig()
                .setUrl(String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", databaseHost, databasePort, databaseName))
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername(databaseUser)
                .setPassword(databasePassword);
    }

    private static PackageConfig packageConfig() {
        return new PackageConfig()
                .setParent("com.zzy")
                .setMapper("mapper")
                .setXml("mapper.xml")
                .setEntity("entity")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("controller");
    }

    private static StrategyConfig strategyConfig() {
        return new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setLogicDeleteFieldName("deleted")
                .setVersionFieldName("version")
                .setInclude(scanner("Table names (comma-separated)").split(","))
                .setTableFillList(Arrays.asList(
                        new TableFill("create_time", FieldFill.INSERT),
                        new TableFill("update_time", FieldFill.INSERT_UPDATE)
                ));
    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter " + tip + ":");

        return Optional.ofNullable(scanner.next())
                .filter(ipt -> !ipt.trim().isEmpty())
                .orElseThrow(() -> new MybatisPlusException("Enter valid " + tip + "!"));
    }
}
