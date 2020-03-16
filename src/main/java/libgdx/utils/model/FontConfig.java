package libgdx.utils.model;


import com.badlogic.gdx.graphics.Color;
import libgdx.resources.FontManager;

import java.util.Objects;

public class FontConfig {

    public static final int FONT_SIZE = 32;
    public static final float STANDARD_BORDER_WIDTH = 0.4f;

    private Color color;
    private Color borderColor;
    private int fontSize;
    private float borderWidth;

    public FontConfig(Color color, Color borderColor, float fontSize, float borderWidth) {
        this(color, borderColor, Math.round(fontSize), borderWidth);
    }

    public FontConfig(Color color, Color borderColor, int fontSize, float borderWidth) {
        this.color = color;
        this.borderColor = borderColor;
        this.fontSize = fontSize;
        this.borderWidth = borderWidth;
    }

    public FontConfig(Color color, int fontSize) {
        this(color, color, fontSize, STANDARD_BORDER_WIDTH);
    }

    public FontConfig(Color color, float fontSize) {
        this(color, color, fontSize, STANDARD_BORDER_WIDTH);
    }

    public FontConfig(Color color, Color borderColor, float borderWidth) {
        this(color, borderColor, FONT_SIZE, borderWidth);
    }

    public FontConfig(Color color, Color borderColor) {
        this(color, borderColor, FONT_SIZE, STANDARD_BORDER_WIDTH);
    }

    public FontConfig(Color color) {
        this(color, FONT_SIZE);
    }

    public FontConfig(int fontSize) {
        this(FontManager.getBaseColorForContrast().getColor(), fontSize);
    }

    public FontConfig(float fontSize) {
        this(FontManager.getBaseColorForContrast().getColor(), Math.round(fontSize));
    }

    public FontConfig(int fontSize, float borderWidth) {
        this(FontManager.getBaseColorForContrast().getColor(), FontManager.getBaseColorForContrast().getColor(), fontSize, borderWidth);
    }


    public FontConfig() {
        this(FontManager.getBaseColorForContrast().getColor());
    }

    public Color getColor() {
        return color;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public int getFontSize() {
        return fontSize;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FontConfig that = (FontConfig) o;
        return fontSize == that.fontSize &&
                Float.compare(that.borderWidth, borderWidth) == 0 &&
                Objects.equals(color, that.color) &&
                Objects.equals(borderColor, that.borderColor);
    }

    @Override
    public int hashCode() {

        return Objects.hash(color, borderColor, fontSize, borderWidth);
    }
}
