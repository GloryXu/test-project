package multi.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author glory
 * @description 建立动态数据源
 * @date Created at 下午10:53 19-1-15
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // 在进行DAO操作前，通过上下文环境变量，获得数据源的类型
        return DataSourceContextHolder.getDataSourceType();
    }

}
