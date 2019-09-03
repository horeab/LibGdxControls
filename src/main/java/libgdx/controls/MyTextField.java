package libgdx.controls;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import libgdx.game.Game;
import libgdx.resources.FontManager;
import org.apache.commons.lang3.StringUtils;

import libgdx.resources.MainResource;
import libgdx.resources.dimen.MainDimen;
import libgdx.resources.ResourcesManager;
import libgdx.graphics.GraphicUtils;
import libgdx.utils.ScreenDimensionsManager;

public class MyTextField extends Table {

    TextField textField = new TextField("", ResourcesManager.getSkin());

    public MyTextField() {
        textField.getStyle().font= Game.getInstance().getFontManager().getFont();
        textField.setOnlyFontChars(true);
    }

    public TextField getTextField() {
        return textField;
    }

    public String getText(){
        return textField.getText();
    }
}