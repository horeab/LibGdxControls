package libgdx.controls;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import org.apache.commons.lang3.StringUtils;

import libgdx.resources.MainResource;
import libgdx.resources.dimen.MainDimen;
import libgdx.resources.ResourcesManager;
import libgdx.graphics.GraphicUtils;
import libgdx.utils.ScreenDimensionsManager;

public class MyTextField extends Table {

    private TextField textField = new TextField("", ResourcesManager.getSkin());

    public MyTextField() {
        build();
    }

    public void build() {
        add(GraphicUtils.getImage(MainResource.magnify_glass)).padRight(MainDimen.horizontal_general_margin.getDimen()).width(ScreenDimensionsManager.getScreenWidthValue(10)).height(ScreenDimensionsManager.getScreenHeightValue(5));
        setWidth(ScreenDimensionsManager.getScreenWidthValue(70));
        setHeight(ScreenDimensionsManager.getScreenHeightValue(10));
        textField.setOnlyFontChars(true);
        textField.setSize(ScreenDimensionsManager.getScreenWidthValue(70), ScreenDimensionsManager.getScreenHeightValue(10));
        textField.setTextFieldFilter(new TextField.TextFieldFilter() {
            public boolean acceptChar(TextField textField, char c) {
                return StringUtils.isAlphanumericSpace(String.valueOf(c));
            }
        });
        add(textField).width(ScreenDimensionsManager.getScreenWidthValue(70)).height(ScreenDimensionsManager.getScreenHeightValue(10));
    }

    public TextField getTextField() {
        return textField;
    }
}