package libgdx.resources;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import java.util.Arrays;
import java.util.List;

import libgdx.game.Game;

public class ResourcesManager {

    private ResourcesManager() {
        super();
    }

    public static Skin getSkin() {
        return Game.getInstance().getAssetManager().get(MainResource.skin.getPath(), Skin.class);
    }

    public static String getPopupBackground() {
        return "dialog";
    }

    public static Drawable getTableBackgroundDefault() {
        return getSkin().getDrawable("grey_normal");
    }

    public static String getLabelTitle() {
        return "title";
    }

    public static String getLabelBlue() {
        return "blue";
    }

    public static String getLabelBlack() {
        return "default";
    }

    public static String getLabelGrey() {
        return "grey";
    }

    public static String getLabelWhite() {
        return "white";
    }

    public static String getLabelDarkGrey() {
        return "dark_grey";
    }

    public static String getLabelGreen() {
        return "green";
    }

    public static String getLabelDarkGreen() {
        return "dark_green";
    }

    public static String getLabelMoreDarkGreen() {
        return "more_dark_green";
    }
}
