package libgdx.controls.button;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import libgdx.graphics.GraphicUtils;
import libgdx.resources.Res;
import libgdx.resources.MainResource;

public enum MainButtonSkin implements ButtonSkin {

    DEFAULT(MainResource.btn_menu_up, MainResource.btn_menu_down, MainResource.btn_menu_up, MainResource.btn_lowcolor_up, null),
    BACK(MainResource.btn_back_up, MainResource.btn_back_down, MainResource.btn_back_up, MainResource.btn_back_up, null),
    SOUND_ON(MainResource.sound_on, MainResource.sound_on, MainResource.sound_on, MainResource.sound_on, null),
    SOUND_OFF(MainResource.sound_off, MainResource.sound_off, MainResource.sound_off, MainResource.sound_off, null),
    LOW_COLOR(MainResource.btn_lowcolor_up, MainResource.btn_lowcolor_down, MainResource.btn_lowcolor_up, MainResource.btn_lowcolor_up, null),;

    MainButtonSkin(Res imgUp, Res imgDown, Res imgChecked, Res imgDisabled, Color buttonDisabledFontColor) {
        this.imgUp = imgUp;
        this.imgDown = imgDown;
        this.imgChecked = imgChecked;
        this.imgDisabled = imgDisabled;
        this.buttonDisabledFontColor = buttonDisabledFontColor;
    }

    private Res imgUp;
    private Res imgDown;
    private Res imgChecked;
    private Res imgDisabled;
    private Color buttonDisabledFontColor;

    @Override
    public Drawable getImgUp() {
        return GraphicUtils.getImage(imgUp).getDrawable();
    }

    @Override
    public Drawable getImgDown() {
        return GraphicUtils.getImage(imgDown).getDrawable();
    }

    @Override
    public Drawable getImgChecked() {
        return GraphicUtils.getImage(imgChecked).getDrawable();
    }

    @Override
    public Drawable getImgDisabled() {
        return GraphicUtils.getImage(imgDisabled).getDrawable();
    }

    @Override
    public Color getButtonDisabledFontColor() {
        return buttonDisabledFontColor;
    }
}
