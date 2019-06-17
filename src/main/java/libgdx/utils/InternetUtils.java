package libgdx.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

import java.net.URL;
import java.net.URLConnection;

import libgdx.game.Game;

public class InternetUtils {


    public static boolean hasInternet() {
        return urlCanBeOpened("https://www.google.com/");
    }

    public static boolean urlCanBeOpened(String url) {
        boolean hasInternet;
        try {
            final URLConnection connection = new URL(url).openConnection();
            connection.connect();
            hasInternet = true;
        } catch (final Exception e) {
            hasInternet = false;
        }
        return hasInternet;
    }

    public static void openAppUrl() {

        StringBuilder url = new StringBuilder();
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            url.append("market://details?id=");
        }
        if (Gdx.app.getType() == Application.ApplicationType.iOS) {
            url.append("itms-apps://itunes.apple.com/xy/app/foo/id");
        }
        url.append(Game.getInstance().getAppInfoService().getStoreAppId());
        if (Gdx.app.getType() == Application.ApplicationType.iOS) {
            url.append("?action=write-review");
        }

        Gdx.net.openURI(url.toString());
    }
}
