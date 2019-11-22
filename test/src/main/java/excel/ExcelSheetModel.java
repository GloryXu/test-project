package excel;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @date 2018/7/27 16:42
 */
public class ExcelSheetModel {
    private String sheetName;
    private List<String> headerList;
    private List<Map<String, Object>> resListMap;

    public ExcelSheetModel() {
    }

    public String getSheetName() {
        return this.sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<String> getHeaderList() {
        return this.headerList;
    }

    public void setHeaderList(List<String> headerList) {
        this.headerList = headerList;
    }

    public List<Map<String, Object>> getResListMap() {
        return this.resListMap;
    }

    public void setResListMap(List<Map<String, Object>> resListMap) {
        this.resListMap = resListMap;
    }

    /*public void parseObjList(List list) {
        if (this.headerList != null && this.headerList.size() > 0) {
            Iterator var2 = list.iterator();

            while(var2.hasNext()) {
                Object obj = var2.next();

                try {
                    Map<String, Object> toMap = new HashMap();
                    Map<String, String> map = BeanUtils.describe(obj);
                    Iterator var6 = this.headerList.iterator();

                    while(var6.hasNext()) {
                        String header = (String)var6.next();
                        String value = (String)map.get(header);
                        toMap.put(header, value == null ? "" : value);
                    }

                    this.resListMap.add(toMap);
                } catch (IllegalAccessException var9) {
                    var9.printStackTrace();
                } catch (InvocationTargetException var10) {
                    var10.printStackTrace();
                } catch (NoSuchMethodException var11) {
                    var11.printStackTrace();
                }
            }
        }

    }*/
}

