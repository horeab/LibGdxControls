package libgdx.resources;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.Arrays;
import java.util.List;

import libgdx.game.Game;
import libgdx.resources.gamelabel.GameLabel;
import libgdx.resources.gamelabel.MainGameLabel;
import libgdx.utils.EnumUtils;
import libgdx.utils.ScreenDimensionsManager;

public class FontManager {

    private BitmapFont font;

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

    public BitmapFont getFont() {
        if (font == null) {
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(MainResource.valueOf(MainGameLabel.font_name.getText()).getPath()));
            FreeTypeFontGenerator.setMaxTextureSize(2048);
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 32;
            parameter.borderWidth = 0.4f;
            parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + collectAllLabelChars();
            font = generator.generateFont(parameter);
            generator.dispose();
        }
        return font;
    }

    private String collectAllLabelChars() {
        StringBuilder allChars = new StringBuilder();
        for (GameLabel label : MainGameLabel.values()) {
            allChars.append(label.getText());
        }
        for (GameLabel label : (GameLabel[]) EnumUtils.getValues(Game.getInstance().getMainDependencyManager().getGameLabelClass())) {
            allChars.append(label.getText());
        }
        return allChars.toString();
    }
}
