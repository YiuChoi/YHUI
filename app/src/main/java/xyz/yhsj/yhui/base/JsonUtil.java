package xyz.yhsj.yhui.base;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Gson���ķ�װ�����࣬ר�Ÿ������json����</br>
 * �ڲ�ʵ����Gson����ĵ���
 *
 * @author LOVE
 * @version 1.0
 * @since 2015/6/19
 */
public class JsonUtil {

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        }
    }

    private JsonUtil() {

    }

    /**
     * ������ת����json��ʽ
     *
     * @param ts
     * @return
     */
    public static String objectToJson(Object ts) {
        String jsonStr = null;
        if (gson != null) {
            jsonStr = gson.toJson(ts);
        }
        return jsonStr;
    }

    /**
     * ������ת����json��ʽ(���Զ������ڸ�ʽ)
     *
     * @param ts
     * @return
     */
    public static String objectToJsonDateSerializer(Object ts, final String dateformat) {
        String jsonStr = null;
        gson = new GsonBuilder().registerTypeHierarchyAdapter(Date.class, new JsonSerializer<Date>() {
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                SimpleDateFormat format = new SimpleDateFormat(dateformat);
                return new JsonPrimitive(format.format(src));
            }
        }).setDateFormat(dateformat).create();
        if (gson != null) {
            jsonStr = gson.toJson(ts);
        }
        return jsonStr;
    }

    /**
     * ��json��ʽת����list����
     *
     * @param jsonStr
     * @return
     */
    public static List<?> jsonToList(String jsonStr) {

        jsonStr = stringFormat(jsonStr);

        List<?> objList = null;
        if (gson != null) {
            java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<?>>() {
            }.getType();
            objList = gson.fromJson(jsonStr, type);
        }
        return objList;
    }

    /**
     * ��json��ʽת����list���󣬲�׼ȷָ������
     *
     * @param jsonStr
     * @param type
     * @return
     */
    public static List<?> jsonToList(String jsonStr, java.lang.reflect.Type type) {

        jsonStr = stringFormat(jsonStr);

        List<?> objList = null;
        if (gson != null) {
            objList = gson.fromJson(jsonStr, type);
        }
        return objList;
    }

    /**
     * ��json��ʽת����map����
     *
     * @param jsonStr
     * @return
     */
    public static Map<?, ?> jsonToMap(String jsonStr) {

        jsonStr = stringFormat(jsonStr);

        Map<?, ?> objMap = null;
        if (gson != null) {
            java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {
            }.getType();
            objMap = gson.fromJson(jsonStr, type);
        }
        return objMap;
    }

    /**
     * ��jsonת����bean����
     *
     * @param jsonStr
     * @return
     */
    public static Object jsonToBean(String jsonStr, Class<?> cl) {

        jsonStr = stringFormat(jsonStr);

        Object obj = null;
        if (gson != null) {
            obj = gson.fromJson(jsonStr, cl);
        }
        return obj;
    }

    /**
     * ��jsonת����bean����(���Զ������ڸ�ʽ)
     *
     * @param jsonStr
     * @param cl
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T jsonToBeanDateSerializer(String jsonStr, Class<T> cl, final String pattern) {

        jsonStr = stringFormat(jsonStr);

        Object obj = null;
        gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                String dateStr = json.getAsString();
                try {
                    return format.parse(dateStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }).setDateFormat(pattern).create();
        if (gson != null) {
            obj = gson.fromJson(jsonStr, cl);
        }
        return (T) obj;
    }

    /**
     * ����key ��ȡֵ
     *
     * @param jsonStr
     * @param key
     * @return
     */
    public static Object getJsonValue(String jsonStr, String key) {

        jsonStr = stringFormat(jsonStr);

        Object rulsObj = null;
        Map<?, ?> rulsMap = jsonToMap(jsonStr);
        if (rulsMap != null && rulsMap.size() > 0) {
            rulsObj = rulsMap.get(key);
        }
        return rulsObj;
    }

    /**
     * ��ʽ���ַ���,�����淶��json�ַ�
     *
     * @param jsonStr
     * @return
     */
    private static String stringFormat(String jsonStr) {
        jsonStr = jsonStr.replace("\\", "");

        if (jsonStr.startsWith("\"")) {
            jsonStr = jsonStr.substring(1, jsonStr.length());
        }

        if (jsonStr.endsWith("\"")) {
            jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
        }

        return jsonStr;
    }

}


