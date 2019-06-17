package libgdx.controls.button.builders;

import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MyButton;
import libgdx.resources.Res;
import libgdx.controls.labelimage.LabelImage;

public class ButtonWithIconBuilder extends ButtonBuilder {

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
}
