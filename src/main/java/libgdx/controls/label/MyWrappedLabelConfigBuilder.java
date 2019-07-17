package libgdx.controls.label;

import com.badlogic.gdx.graphics.Color;

import libgdx.constants.ScreenContrast;
import libgdx.game.Game;
import libgdx.resources.ResourcesManager;
import libgdx.resources.FontManager;
import libgdx.utils.ScreenDimensionsManager;

public class MyWrappedLabelConfigBuilder {

    private float width = ScreenDimensionsManager.getScreenWidthValue(80);
    private float fontScale = FontManager.getNormalFontDim();
    private Color textColor = Color.BLACK;
    private String text;
    private boolean singleLineLabel = false;

    public MyWrappedLabelConfigBuilder setWidth(float width) {
        this.width = width;
        return this;
    }

    public MyWrappedLabelConfigBuilder setFontScale(float fontScale) {
        this.fontScale = fontScale;
        return this;
    }

    public float getFontScale() {
        return fontScale;
    }

    public MyWrappedLabelConfigBuilder setTextColor(Color color) {
        this.textColor = color;
        return this;
    }

    public MyWrappedLabelConfigBuilder setStyleDependingOnContrast() {
        this.textColor = getScreenContrastStyle();
        return this;
    }

    public MyWrappedLabelConfigBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public MyWrappedLabelConfigBuilder setSingleLineLabel(boolean singleLineLabel) {
        return singleLineLabel ? setSingleLineLabel() : setWrappedLineLabel(width);
    }

    public MyWrappedLabelConfigBuilder setSingleLineLabel() {
        this.singleLineLabel = true;
        return this;
    }

    public MyWrappedLabelConfigBuilder setWrappedLineLabel(float width) {
        this.singleLineLabel = false;
        this.width = width;
        return this;
    }

    public MyWrappedLabelConfig build() {
        MyWrappedLabelConfig myWrappedLabelConfig = new MyWrappedLabelConfig();
        myWrappedLabelConfig.setWidth(width);
        myWrappedLabelConfig.setFontScale(fontScale);
        myWrappedLabelConfig.setSingleLineLabel(singleLineLabel);
        myWrappedLabelConfig.setText(text);
        myWrappedLabelConfig.setTextColor(textColor);
        return myWrappedLabelConfig;
    }

    public static Color getScreenContrastStyle() {
        return getScreenContrastStyle(Color.WHITE, Color.BLACK);
    }

    public static Color getScreenContrastStyle(Color darkContrastStyle, Color lightContrastStyle) {
        return Game.getInstance().getSubGameDependencyManager().getScreenContrast() == ScreenContrast.LIGHT ? lightContrastStyle : darkContrastStyle;
    }
}