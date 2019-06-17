package libgdx.controls.button;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public interface ButtonSkin {

    Drawable getImgUp();

    Drawable getImgDown();

    Drawable getImgChecked();

    Drawable getImgDisabled();

    Color getButtonDisabledFontColor();
}
