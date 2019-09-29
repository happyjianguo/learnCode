/**
* Copyright &copy; 2015 All rights reserved.
 */
package cn.yufu.system.common.persistence.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.persistence.dialect.Dialect;
import cn.yufu.system.common.persistence.dialect.db.SQLServer2005Dialect;
import cn.yufu.system.common.utils.Reflections;
import cn.yufu.system.common.utils.StringUtils;

import java.util.Properties;

/**
 * 数据库分页插件，只拦截查询语句.
 * @author poplar.yfyang / king
 * @version 2013-8-28
 */
@Intercepts({@Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PaginationInterceptor extends BaseInterceptor {

    private static final long serialVersionUID = 1L;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        final MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        
//        //拦截需要分页的SQL
////        if (mappedStatement.getId().matches(_SQL_PATTERN)) {
//        if (StringUtils.indexOfIgnoreCase(mappedStatement.getId(), _SQL_PATTERN) != -1) {
            Object parameter = invocation.getArgs()[1];
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            Object parameterObject = boundSql.getParameterObject();

            //获取分页参数对象
            Page<Object> page = null;
            if (parameterObject != null) {
                page = convertParameter(parameterObject, page);
            }

            //如果设置了分页对象，则进行分页
            if (page != null && page.getPageSize() != -1) {

            	if (StringUtils.isBlank(boundSql.getSql())){
                    return null;
                }
                String originalSql = boundSql.getSql().trim();
            	//针对 子查询不能使用ORDER BY 语句， getCount 方法报错的处理 
                String replaceSql = getReplaceSql(page, originalSql);
                //得到总记录数
                page.setCount(SQLHelper.getCount(replaceSql, null, mappedStatement, parameterObject, boundSql, log));

                //分页查询 本地化对象 修改数据库注意修改实现
                String pageSql = "";
                if (StringUtils.isNotEmpty(page.getJdbcType())) {
                	if ("SQLServer".equalsIgnoreCase(page.getJdbcType())) {
                		Dialect dia = new SQLServer2005Dialect();
                		pageSql = SQLHelper.generatePageSql(originalSql, page, dia);
					}
				} else {
					//默认oracle--配置文件配置
					pageSql = SQLHelper.generatePageSql(originalSql, page, DIALECT);
				}
//                if (log.isDebugEnabled()) {
//                    log.debug("PAGE SQL:" + StringUtils.replace(pageSql, "\n", ""));
//                }
                invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
                BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), pageSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
                //解决MyBatis 分页foreach 参数失效 start
                if (Reflections.getFieldValue(boundSql, "metaParameters") != null) {
                    MetaObject mo = (MetaObject) Reflections.getFieldValue(boundSql, "metaParameters");
                    Reflections.setFieldValue(newBoundSql, "metaParameters", mo);
                }
                //解决MyBatis 分页foreach 参数失效 end
                MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));

                invocation.getArgs()[0] = newMs;
            }
//        }
        return invocation.proceed();
    }


    private String getReplaceSql(Page<Object> page, String originalSql) {
    	if (page != null && StringUtils.isNotBlank(page.getJdbcType())) {
    		if ("SQLServer".equalsIgnoreCase(page.getJdbcType())) {
    			//针对SqlServer库 子查询不能使用ORDER BY 语句
    			if (originalSql.indexOf("ORDER BY ID DESC") >= 0) {	
    				int indexOf = originalSql.indexOf("ORDER BY ID DESC");
    				String prefix = originalSql.substring(0, indexOf);
    				String suffix = originalSql.substring(indexOf + 16, originalSql.length());
    				originalSql = prefix + suffix;
    			}
			}
		}
    	return originalSql;
	}


	@Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        super.initProperties(properties);
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms,
                                                    SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
                ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null) {
            for (String keyProperty : ms.getKeyProperties()) {
                builder.keyProperty(keyProperty);
            }
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        return builder.build();
    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
}
