package number;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DataPool implements Serializable {
    private String layout;
    private Map<String, Object> idatas = new HashMap();

    public Map<String, Object> getIdatas() {
        return this.idatas;
    }

    public void setIdatas(Map<String, Object> idatas) {
        this.idatas = idatas;
    }

    public DataPool() {
    }

    public DataPool(String layout) {
        this.setLayout(layout);
    }

    public String getLayout() {
        return this.layout;
    }

    public Object getValue(String name) {
        return this.idatas.get(name);
    }

    public void setValue(String name, Object value) {
        if (value == null) {
            this.idatas.put(name, "");
        } else {
            this.idatas.put(name, value);
        }
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String toString() {
        Set<String> keys = this.idatas.keySet();
        String s = "";

        String key;
        Object obj;
        for(Iterator i$ = keys.iterator(); i$.hasNext(); s = s + key + " = " + obj + ",") {
            key = (String)i$.next();
            obj = this.idatas.get(key);
        }

        return s;
    }
}