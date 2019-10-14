package libgdx.utils.model;


import com.badlogic.gdx.graphics.Color;

import java.util.Objects;

public class FontConfig {

    private Color color;
    private Color borderColor;
    private int fontSize;
    private float borderWidth;

    public FontConfig(Color color, Color borderColor, int fontSize, float borderWidth) {
        this.color = color;
        this.borderColor = borderColor;
        this.fontSize = fontSize;
        this.borderWidth = borderWidth;
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
