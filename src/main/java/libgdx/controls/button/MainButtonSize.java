package libgdx.controls.button;

import libgdx.resources.dimen.Dimen;
import libgdx.resources.dimen.MainDimen;

public enum MainButtonSize implements ButtonSize {

    STANDARD_IMAGE(MainDimen.side_btn_image.getDimen(), MainDimen.side_btn_image.getDimen()),
    BACK_BUTTON(MainDimen.side_btn_back.getDimen(), MainDimen.side_btn_back.getDimen()),

    ONE_ROW_BUTTON_SIZE(MainDimen.width_default_btn.getDimen(), MainDimen.height_default_btn.getDimen()),
    TWO_ROW_BUTTON_SIZE(MainDimen.width_default_btn.getDimen(), MainDimen.height_default_btn.getDimen() * 1.5f),
    THREE_ROW_BUTTON_SIZE(MainDimen.width_default_btn.getDimen(), MainDimen.height_default_btn.getDimen() * 2),;

    private float width;
    private float height;

    MainButtonSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }
}
