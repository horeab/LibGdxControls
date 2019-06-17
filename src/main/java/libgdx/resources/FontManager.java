package libgdx.resources;


import com.badlogic.gdx.graphics.g2d.BitmapFont;

import libgdx.utils.ScreenDimensionsManager;

public class FontManager {

    private static final float STANDARD_FONT_SIZE = 9;

    private static final float BIG_FONT = STANDARD_FONT_SIZE * 1.5f;
    private static final float NORMAL_BIG_FONT = STANDARD_FONT_SIZE * 1.3f;
    private static final float NORMAL_FONT = STANDARD_FONT_SIZE;
    private static final float SMALL_FONT = STANDARD_FONT_SIZE * 0.9f;

    public static float getNormalBigFontDim() {
        return calculateFontSize(NORMAL_BIG_FONT);
    }

    public static float getBigFontDim() {
        return calculateFontSize(BIG_FONT);
    }

    public static float getSmallFontDim() {
        return calculateFontSize(SMALL_FONT);
    }

    public static float getNormalFontDim() {
        return calculateFontSize(NORMAL_FONT);
    }

    private static float calculateFontSize(float fontSize) {
        return ScreenDimensionsManager.getScreenHeightValue(fontSize / 100);
    }

    public static float calculateMultiplierStandardFontSize(float multiplier) {
        return calculateFontSize(STANDARD_FONT_SIZE * multiplier);
    }

    public static BitmapFont getFont() {
        return ResourcesManager.getSkin().getFont(ResourcesManager.getFontName());
    }

    public static BitmapFont getBigFont() {
        return ResourcesManager.getSkin().getFont(ResourcesManager.getBigFontName());
    }


}
