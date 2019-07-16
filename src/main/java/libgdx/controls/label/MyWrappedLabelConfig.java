package libgdx.controls.label;

import com.badlogic.gdx.graphics.Color;

public class MyWrappedLabelConfig {

    private float width;
    private float fontScale;
    private Color textColor;
    private String text;
    private boolean singleLineLabel;

    MyWrappedLabelConfig() {
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getFontScale() {
        return fontScale;
    }

    public void setFontScale(float fontScale) {
        this.fontScale = fontScale;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSingleLineLabel() {
        return singleLineLabel;
    }

    public void setSingleLineLabel(boolean singleLineLabel) {
        this.singleLineLabel = singleLineLabel;
    }
}
