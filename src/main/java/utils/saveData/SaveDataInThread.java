package utils.saveData;

import java.util.HashMap;

public class SaveDataInThread {
    private static ThreadLocal<HashMap<String, String>> saveToMap = new ThreadLocal<HashMap<String, String>>() {
        @Override
        protected HashMap<String, String> initialValue() {
            return new HashMap<>();
        }
    };

    public static void saveValue (String id,String value) {
        saveToMap.get().put(id,value);
    }
    public static String getSaveValue (String key) {
        return  saveToMap.get().get(key);
    }
}
