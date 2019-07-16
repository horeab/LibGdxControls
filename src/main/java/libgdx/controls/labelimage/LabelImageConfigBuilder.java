package libgdx.controls.labelimage;

import com.badlogic.gdx.graphics.Color;

import libgdx.resources.Res;
import libgdx.resources.FontManager;
import libgdx.utils.ScreenDimensionsManager;

public class LabelImageConfigBuilder {

    public static final float DEFAULT_IMAGE_SIDE_DIMENSION = ScreenDimensionsManager.getScreenWidthValue(9);

    private String text;
    private Res image;
    private boolean alignTextRight;
    private float marginBetweenLabelImage;
    private float imageSideDimension = DEFAULT_IMAGE_SIDE_DIMENSION;
    private float fontScale = FontManager.getNormalFontDim();
    private Color textColor = Color.BLACK;
    private boolean singleLineLabel = true;
    private float labelWidth = ScreenDimensionsManager.getScreenWidthValue(50);

    public LabelImageConfigBuilder setSingleLineLabel() {
        this.singleLineLabel = true;
        return this;
    }

    public LabelImageConfigBuilder setWrappedLineLabel(float labelWidth) {
        this.singleLineLabel = false;
        this.labelWidth = labelWidth;
        return this;
    }


    public LabelImageConfigBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public LabelImageConfigBuilder setImage(Res image) {
        this.image = image;
        return this;
    }

    public LabelImageConfigBuilder setAlignTextRight(boolean alignTextRight) {
        this.alignTextRight = alignTextRight;
        return this;
    }

    public LabelImageConfigBuilder setFontScale(float fontScale) {
        this.fontScale = fontScale;
        return this;
    }

    public LabelImageConfigBuilder setTextColor(Color textColor) {
        this.textColor = textColor;
        return this;
    }

    public LabelImageConfigBuilder setMarginBetweenLabelImage(float marginBetweenLabelImage) {
        this.marginBetweenLabelImage = marginBetweenLabelImage;
        return this;
    }

    public LabelImageConfigBuilder setImageSideDimension(float imageSideDimension) {
        this.imageSideDimension = imageSideDimension;
        return this;
    }

    public LabelImageConfig build() {
        LabelImageConfig labelImageConfig = new LabelImageConfig();
        labelImageConfig.setText(text);
        labelImageConfig.setAlignTextRight(alignTextRight);
        labelImageConfig.setFontScale(fontScale);
        labelImageConfig.setImage(image);
        labelImageConfig.setLabelWidth(labelWidth);
        labelImageConfig.setSingleLineLabel(singleLineLabel);
        labelImageConfig.setImageSideDimension(imageSideDimension);
        labelImageConfig.setTextColor(textColor);
        labelImageConfig.setMarginBetweenLabelImage(marginBetweenLabelImage);
        return labelImageConfig;
    }
}
