package libgdx.resources;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import libgdx.game.Game;
import libgdx.resources.gamelabel.GameLabel;
import libgdx.resources.gamelabel.MainGameLabel;
import libgdx.utils.EnumUtils;
import libgdx.utils.ScreenDimensionsManager;

public class FontManager {

    private static final List<Color> AVAILABLE_COLORS = Arrays.asList(Color.BLACK, Color.RED, Color.LIGHT_GRAY, Color.GRAY);
    private Map<Color, BitmapFont> colorFonts = new HashMap<>();

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
        return getColorFonts().get(Color.BLACK);
    }

    public BitmapFont getFont(Color color) {
        BitmapFont font = getColorFonts().get(color);
        return font != null ? font : getFont();
    }

    private Map<Color, BitmapFont> getColorFonts() {
        init();
        return colorFonts;
    }

    private void init() {
        if (colorFonts.isEmpty()) {
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(MainResource.valueOf(MainGameLabel.font_name.getText()).getPath()));
            FreeTypeFontGenerator.setMaxTextureSize(2048);
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 32;
            parameter.borderWidth = 0.4f;
            parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + Game.getInstance().getMainDependencyManager().getAllFontChars();
            for (Color color : AVAILABLE_COLORS) {
                parameter.borderColor = color;
                parameter.color = color;
                BitmapFont font = generator.generateFont(parameter);
                font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                colorFonts.put(color, font);
            }
            generator.dispose();
        }
    }

}
