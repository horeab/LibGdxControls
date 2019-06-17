package libgdx.controls.popup.notificationpopup;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import libgdx.resources.Res;
import libgdx.resources.dimen.MainDimen;
import libgdx.utils.ScreenDimensionsManager;


public class MyNotificationPopupConfigBuilder {

    private String text;
    private Res resource;
    private Float imageDimen = MainDimen.side_notification_popup_icon.getDimen();
    private Float popupWidth = ScreenDimensionsManager.getScreenWidthValue(70);
    private Table contentTable;


    public MyNotificationPopupConfigBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public MyNotificationPopupConfigBuilder setResource(Res resource) {
        this.resource = resource;
        return this;
    }


    public MyNotificationPopupConfigBuilder setImageDimen(Float imageDimen) {
        this.imageDimen = imageDimen;
        return this;
    }


    public MyNotificationPopupConfigBuilder setPopupWidth(Float popupWidth) {
        this.popupWidth = popupWidth;
        return this;
    }


    public MyNotificationPopupConfigBuilder setContentTable(Table contentTable) {
        this.contentTable = contentTable;
        return this;
    }
    public MyNotificationPopupConfig build() {
        MyNotificationPopupConfig config = new MyNotificationPopupConfig();
        config.setText(text);
        config.setResource(resource);
        config.setImageDimen(imageDimen);
        config.setPopupWidth(popupWidth);
        config.setContentTable(contentTable);
        return config;
    }
}
