package cn.net.yzl.common.zt.config.aop;

import cn.net.yzl.common.zt.config.mysql.DataSourceContextHolder;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 在dao层决定数据源(注：如果用这方式，service层不能使用事务，否则出问题，因为打开事务打开时，就会觉得数据库源了）
 *
 */
//@Aspect
//@Component
public class DataSourceAopInDao {

	private static Logger log = LoggerFactory.getLogger(DataSourceAopInDao.class);
	
	@Before("execution(* cn.net.yzl.common.zt.mapper..*.find*(..)) "
            + " or execution(* cn.net.yzl.common.zt.mapper..*.get*(..)) "
            + " or execution(* cn.net.yzl.common.zt.mapper..*.query*(..))"
            + " or execution(* cn.net.yzl.common.zt.mapper..*.select*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.setRead();
    }

    @Before("execution(* cn.net.yzl.common.zt.mapper..*.insert*(..)) "
    		+ " or execution(* cn.net.yzl.common.zt.mapper..*.update*(..))"
            + " or execution(* cn.net.yzl.common.zt.mapper..*.add*(..))"
            + " or execution(* cn.net.yzl.common.zt.mapper..*.create*(..))"
            + " or execution(* cn.net.yzl.common.zt.mapper..*.save*(..))"
            + " or execution(* cn.net.yzl.common.zt.mapper..*.delete*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.setWrite();
    }
    
    
}
