package libgdx.resources;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import libgdx.game.Game;
import libgdx.resources.gamelabel.MainGameLabel;
import libgdx.utils.ScreenDimensionsManager;
import libgdx.utils.model.FontColor;

import java.util.HashMap;
import java.util.Map;

public class FontManager {

    private Map<FontColor, BitmapFont> colorFonts = new HashMap<>();

    private static final float STANDARD_FONT_SIZE = 9;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontCreationParameter;

    private static final float BIG_FONT = STANDARD_FONT_SIZE * 1.5f;
    private static final float NORMAL_BIG_FONT = STANDARD_FONT_SIZE * 1.3f;
    private static final float NORMAL_FONT = STANDARD_FONT_SIZE;
    private static final float SMALL_FONT = STANDARD_FONT_SIZE * 0.9f;

    public FontManager() {
        fontCreationParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontCreationParameter.size = 32;
        fontCreationParameter.borderWidth = 0.4f;
        fontCreationParameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + Game.getInstance().getSubGameDependencyManager().getAllFontChars();
    }

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
        return getFont(FontColor.BLACK);
    }

    public BitmapFont getFont(FontColor color) {
        if (colorFonts.isEmpty()) {
            initFont();
        }
        return colorFonts.get(color);
    }

    private void initFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(MainResource.valueOf(MainGameLabel.font_name.getText()).getPath()));
        FreeTypeFontGenerator.setMaxTextureSize(2048);
        for (FontColor fontColor : FontColor.values()) {
            Color color = fontColor.getColor();
            fontCreationParameter.borderColor = color;
            fontCreationParameter.color = color;
            BitmapFont font = generator.generateFont(fontCreationParameter);
            font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            colorFonts.put(fontColor, font);
        }
        generator.dispose();
    }

}
