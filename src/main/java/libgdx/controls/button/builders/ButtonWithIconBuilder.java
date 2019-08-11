package libgdx.controls.button.builders;

import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MainButtonSkin;
import libgdx.controls.button.MyButton;
import libgdx.controls.labelimage.LabelImageConfigBuilder;
import libgdx.resources.Res;
import libgdx.controls.labelimage.LabelImage;
import libgdx.resources.dimen.MainDimen;

public class ButtonWithIconBuilder extends ButtonBuilder {

    private boolean singleLineLabel;
    private String text;
    private Res icon;

    public ButtonWithIconBuilder(String text, Res icon) {
        this.text = text;
        this.icon = icon;
    }

    @Override
    public MyButton build() {
        LabelImage table = createTableLabelImage(text, icon);
        addCenterTextImageColumn(table);
        return super.build();
    }

    public ButtonBuilder setSingleLineLabel() {
        singleLineLabel = true;
        return this;
    }

    @Override
    protected LabelImage createTableLabelImage(String text, Res icon) {
        return new LabelImage(getLabelImageConfigBuilder(text, icon)
                .build());
    }

    private LabelImageConfigBuilder getLabelImageConfigBuilder(String text, Res icon) {
        LabelImageConfigBuilder builder = new LabelImageConfigBuilder()
                .setImage(icon)
                .setMarginBetweenLabelImage(MainDimen.horizontal_general_margin.getDimen())
                .setText(text)
                .setAlignTextRight(true);
        if (singleLineLabel) {
            builder.setSingleLineLabel();
        } else {
            builder.setWrappedLineLabel(getButtonSize().getWidth() - LabelImageConfigBuilder.DEFAULT_IMAGE_SIDE_DIMENSION * 2f);
        }
        return builder;
    }

}
