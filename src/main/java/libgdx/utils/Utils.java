package libgdx.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class Utils {

    public static <T> List<T> getListFromJsonString(String jsonListString, Class<T> clazz) {
        List<T> lst = new ArrayList<T>();
        if (StringUtils.isNotBlank(jsonListString)) {
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(jsonListString).getAsJsonArray();

            for (final JsonElement json : array) {
                T entity = new Gson().fromJson(json, clazz);
                lst.add(entity);
            }
        }
        return lst;

    }

    public static JSONObject getJsonObjectFromString(String jsonString) {
        JSONObject existingGameInfoJson = new JSONObject();
        try {
            if (!StringUtils.isBlank(jsonString)) {
                existingGameInfoJson = new JSONObject(jsonString);
            }
        } catch (JSONException e) {
            return null;
        }
        return existingGameInfoJson;
    }

    public static float getValueForPercent(float val, float percent) {
        return val * (percent / 100f);
    }

    public static Object getLastElement(final Collection c) {
        Object lastElement = null;
        if (c != null && !c.isEmpty()) {
            final Iterator itr = c.iterator();
            lastElement = itr.next();
            while (itr.hasNext()) {
                lastElement = itr.next();
            }
        }
        return lastElement;
    }

}
