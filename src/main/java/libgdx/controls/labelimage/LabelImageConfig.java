package libgdx.controls.labelimage;


import com.badlogic.gdx.graphics.Color;

import libgdx.resources.Res;

public class LabelImageConfig {

    private String text;
    private Color textColor;
    private Res image;
    private float imageSideDimension;
    private float fontScale;
    private float labelWidth;
    private float marginBetweenLabelImage;
    private boolean alignTextRight;
    private boolean singleLineLabel;

    public boolean isSingleLineLabel() {
        return singleLineLabel;
    }

    public void setSingleLineLabel(boolean shortLabel) {
        this.singleLineLabel = shortLabel;
    }

    public String getText() {
        return text;
    }

    void setText(String text) {
        this.text = text;
    }

    public Res getImage() {
        return image;
    }

    void setImage(Res image) {
        this.image = image;
    }

    public boolean isAlignTextRight() {
        return alignTextRight;
    }

    void setAlignTextRight(boolean alignTextRight) {
        this.alignTextRight = alignTextRight;
    }

    public float getFontScale() {
        return fontScale;
    }

    void setFontScale(float fontScale) {
        this.fontScale = fontScale;
    }

    public Color getTextColor() {
        return textColor;
    }

    public float getLabelWidth() {
        return labelWidth;
    }

    public void setLabelWidth(float labelWidth) {
        this.labelWidth = labelWidth;
    }

    void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public float getMarginBetweenLabelImage() {
        return marginBetweenLabelImage;
    }

    void setMarginBetweenLabelImage(float marginBetweenLabelImage) {
        this.marginBetweenLabelImage = marginBetweenLabelImage;
    }

    public float getImageSideDimension() {
        return imageSideDimension;
    }

    public void setImageSideDimension(float imageSideDimension) {
        this.imageSideDimension = imageSideDimension;
    }
}
