package cn.net.yzl.msg.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/3/5 16:05
 * @title: DataSourceConfig
 * @description:
 */
@Configuration
@MapperScan(basePackages = "cn.net.yzl.msg.mapper", sqlSessionFactoryRef = "msgSqlSessionFactory")
public class MsgDataSourceConfig {

    @Value("${msg.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean("msgDataSource")
    @ConfigurationProperties(prefix = "msg.datasource")
    public DataSource getMsgDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean("msgSqlSessionFactory")
    public SqlSessionFactory msgSqlSessionFactory(@Qualifier("msgDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        //配置mapper的xml形式文件位置
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/msg/*.xml"));
        //添加分页插件、打印sql插件
        Interceptor[] plugins = new Interceptor[]{pageInterceptor(),new SqlPrintInterceptor()};
        sessionFactoryBean.setPlugins(plugins);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        sessionFactoryBean.setConfiguration(configuration);
        //开启驼峰规则
        configuration.setMapUnderscoreToCamelCase(true);
        return sessionFactoryBean.getObject();
    }

    @Bean("msgSqlSessionTemplate")
    public SqlSessionTemplate msgSqlSessionTemplate(@Qualifier("msgSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("returnPageInfo", "check");
        p.setProperty("params", "count=countSql");
        pageInterceptor.setProperties(p);
        return pageInterceptor;
    }

}
