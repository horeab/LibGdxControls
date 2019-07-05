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

import libgdx.game.external.AppInfoService;


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

    public static AppInfoService cloneAppInfoService(AppInfoService currentAppInfoService, String newLang) {
        return new AppInfoService() {
            @Override
            public String getAppName() {
                return currentAppInfoService.getAppName();
            }

            @Override
            public String getStoreAppId() {
                return currentAppInfoService.getStoreAppId();
            }

            @Override
            public boolean screenShotMode() {
                return currentAppInfoService.screenShotMode();
            }

            @Override
            public void showRewardedVideoAd() {
                currentAppInfoService.screenShotMode();
            }

            @Override
            public String getGameIdPrefix() {
                return currentAppInfoService.getGameIdPrefix();
            }

            @Override
            public String proVersionStoreAppId() {
                return currentAppInfoService.proVersionStoreAppId();
            }

            @Override
            public void showPopupAd() {
                currentAppInfoService.showPopupAd();
            }

            @Override
            public String getImplementationGameResourcesFolder() {
                return currentAppInfoService.getImplementationGameResourcesFolder();
            }

            @Override
            public String getLanguage() {
                return newLang;
            }

            @Override
            public String getMainResourcesFolder() {
                return currentAppInfoService.getMainResourcesFolder();
            }

            @Override
            public String getResourcesFolder() {
                return currentAppInfoService.getResourcesFolder();
            }

            @Override
            public boolean isPortraitMode() {
                return currentAppInfoService.isPortraitMode();
            }

            @Override
            public boolean isProVersion() {
                return currentAppInfoService.isProVersion();
            }

            @Override
            public boolean googleFacebookLoginEnabled() {
                return currentAppInfoService.googleFacebookLoginEnabled();
            }

            @Override
            public float gameScreenTopMargin() {
                return currentAppInfoService.gameScreenTopMargin();
            }
        };
    }

}
