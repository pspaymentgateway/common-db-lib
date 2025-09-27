package paysecure.common.library;

import java.util.List;

import javax.sql.DataSource;

import paysecure.common.library.LoggingContext;
import paysecure.common.library.WhiteListedKeys;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.QueryExecutionListener;
import net.ttddyy.dsproxy.proxy.ParameterSetOperation;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;



@Configuration
public class DataSourceLoggingConfig {

    @Autowired
    LoggingContext logger;

    /**
     * Convert parameter list to human-readable comma-separated values.
     */
    private String formatParams(List<List<ParameterSetOperation>> paramsList) {
        StringBuilder sb = new StringBuilder();
        for (List<ParameterSetOperation> paramGroup : paramsList) {
            sb.append("[");
            for (int i = 0; i < paramGroup.size(); i++) {
                ParameterSetOperation op = paramGroup.get(i);
                Object value = op.getArgs()[1]; // index 1 is the actual param value
                sb.append(value);
                if (i < paramGroup.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
        }
        return sb.toString();
    }



    private QueryExecutionListener loggingListener() {
        return new QueryExecutionListener() {
            @Override
            public void beforeQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
                logger.dbLog("DB_QUERY_INITIATE","DB query initiated");
            }

        @Override
        public void afterQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
            queryInfoList.forEach(queryInfo -> {
                try {

                    if(WhiteListedKeys.checkIfWhiteListed("sql")){
                        MDC.put("sql", queryInfo.getQuery());
                    }else{
                        MDC.put("sql", "FILTERED");
                    }

                    if(WhiteListedKeys.checkIfWhiteListed("params")){
                        MDC.put("params", formatParams(queryInfo.getParametersList()));
                    }else{
                        MDC.put("params", "FILTERED");
                    }

                    MDC.put("db_latency_ms", String.valueOf(execInfo.getElapsedTime()));
                    logger.dbLog("DB_QUERY_EXECUTED","DB query executed");
                } finally {
                    MDC.remove("sql");
                    MDC.remove("params");
                    MDC.remove("db_latency_ms");
                }
            });
        }


        };
    }


    @Bean(name = "loggingPrimaryDataSource")
    public DataSource loggingPrimaryDataSource(@Qualifier("primaryDataSource") DataSource original) {
        return ProxyDataSourceBuilder
                .create(original)
                .listener(loggingListener())
                .build();
    }

    @Bean(name = "loggingDashbrdDataSource")
    public DataSource loggingDashbrdDataSource(@Qualifier("dashbrdDataSource") DataSource original) {
        return ProxyDataSourceBuilder
                .create(original)
                .listener(loggingListener())
                .build();
    }
}