package multi.db.common;

/**
 * @author glory
 * @description 获得和设置上下文环境 主要负责改变上下文数据源的名称
 * @date Created at 下午10:52 19-1-15
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<DataSourceConst> contextHolder = new ThreadLocal<DataSourceConst>() {
        @Override
        protected DataSourceConst initialValue(){
            return DataSourceConst.MASTER;
        }
    }; // 线程本地环境

    // 设置数据源类型
    public static void setDataSourceType(DataSourceConst dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    // 获取数据源类型
    public static DataSourceConst getDataSourceType() {
        return contextHolder.get();
    }

    // 清除数据源类型
    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    public static void reset(){
        contextHolder.set(DataSourceConst.MASTER);
    }
}
