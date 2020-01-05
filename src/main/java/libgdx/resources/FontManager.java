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
import libgdx.utils.model.FontConfig;

import java.util.HashMap;
import java.util.Map;

public class FontManager {

    private String allChars;
    private Map<FontConfig, BitmapFont> usedFonts = new HashMap<>();
    private FreeTypeFontGenerator generator;

    private static final float STANDARD_FONT_SIZE = 9;

    private static final float BIG_FONT = STANDARD_FONT_SIZE * 1.5f;
    private static final float NORMAL_BIG_FONT = STANDARD_FONT_SIZE * 1.3f;
    private static final float NORMAL_FONT = STANDARD_FONT_SIZE;
    private static final float SMALL_FONT = STANDARD_FONT_SIZE * 0.9f;

    public FontManager() {
        allChars = FreeTypeFontGenerator.DEFAULT_CHARS + getGameAllFontChars();
        generator = new FreeTypeFontGenerator(Gdx.files.internal(MainResource.valueOf(MainGameLabel.font_name.getText()).getPath()));
    }

    protected String getGameAllFontChars() {
        return Game.getInstance().getSubGameDependencyManager().getAllFontChars();
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

    public BitmapFont getFont(FontColor fontColor) {
        return getFont(new FontConfig(fontColor.getColor(), FontConfig.FONT_SIZE));
    }

    public BitmapFont getFont(FontConfig fontConfig) {
        BitmapFont bitmapFont = usedFonts.get(fontConfig);
        if (bitmapFont == null) {
            createBitmapFont(fontConfig);
            return usedFonts.get(fontConfig);
        } else {
            return bitmapFont;
        }

    }

    private FreeTypeFontGenerator.FreeTypeFontParameter createFreeTypeFontParameter(Color color, Color borderColor, int fontSize, float borderWidth) {
        FreeTypeFontGenerator.FreeTypeFontParameter fontCreationParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontCreationParameter.borderWidth = borderWidth;
        fontCreationParameter.characters = allChars;
        fontCreationParameter.borderColor = borderColor;
        fontCreationParameter.color = color;
        fontCreationParameter.size = fontSize;
        return fontCreationParameter;
    }

    private void createBitmapFont(FontConfig fontConfig) {
        BitmapFont font = generator.generateFont(createFreeTypeFontParameter(fontConfig.getColor(), fontConfig.getBorderColor(), fontConfig.getFontSize(), fontConfig.getBorderWidth()));
        FreeTypeFontGenerator.setMaxTextureSize(2048);
        font.getData().setScale(fontConfig.getFontSize());
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        usedFonts.put(fontConfig, font);
    }

}
