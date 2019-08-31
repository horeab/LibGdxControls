package libgdx.controls;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import libgdx.resources.ResourcesManager;
import libgdx.utils.ScreenDimensionsManager;

public class NumberTextField extends MyTextField {

    private TextField textField = new TextField("", ResourcesManager.getSkin());

    public NumberTextField() {
        build();
    }

    public void build() {
        setWidth(ScreenDimensionsManager.getScreenWidthValue(70));
        setHeight(ScreenDimensionsManager.getScreenHeightValue(10));
        textField.setSize(ScreenDimensionsManager.getScreenWidthValue(70), ScreenDimensionsManager.getScreenHeightValue(10));
    }

    public TextField getTextField() {
        return textField;
    }
}