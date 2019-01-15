package multi.db;

/**
 * @author glory
 * @description 获得和设置上下文环境 主要负责改变上下文数据源的名称
 * @date Created at 下午10:52 19-1-15
 */
public class DataSourceContextHolder {
    private static final ThreadLocal contextHolder = new ThreadLocal(); // 线程本地环境

    // 设置数据源类型
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    // 获取数据源类型
    public static String getDataSourceType() {
        return (String) contextHolder.get();
    }

    // 清除数据源类型
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
