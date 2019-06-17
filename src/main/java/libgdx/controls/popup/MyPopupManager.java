package libgdx.controls.popup;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

import libgdx.screen.AbstractScreen;
import libgdx.utils.ActorPositionManager;

public class MyPopupManager extends PopupManager<MyPopup> {

    public MyPopupManager(AbstractScreen abstractScreen) {
        super(abstractScreen);
    }

    @Override
    protected boolean popupShouldBeDisplayed(MyPopup popup) {
        return !isPopupDisplayed(MyPopup.class);
    }

    @Override
    protected void processShowPopup(MyPopup popup) {
        super.processShowPopup(popup);
        popup.show(getAbstractScreen().getStage());
        ActorPositionManager.setActorCenterExternalScreen(popup);
    }

}
