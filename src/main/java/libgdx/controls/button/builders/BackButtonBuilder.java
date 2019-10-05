package libgdx.controls.button.builders;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import libgdx.controls.button.MainButtonSize;
import libgdx.controls.button.MainButtonSkin;
import libgdx.controls.button.MyButton;
import libgdx.resources.dimen.MainDimen;
import libgdx.screen.AbstractScreen;
import libgdx.utils.ScreenDimensionsManager;

public class BackButtonBuilder {

    public MyButton createScreenBackButton(final AbstractScreen screen) {
        return createScreenBackButton(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.onBackKeyPress();
            }
        }, screen);
    }

    public MyButton createScreenBackButton(ChangeListener changeListener, final AbstractScreen screen) {
        MyButton button = new ImageButtonBuilder(MainButtonSkin.BACK, screen)
                .setFixedButtonSize(MainButtonSize.BACK_BUTTON)
                .build();
        button.addListener(changeListener);
        return button;
    }

    public MyButton addHoverBackButton(AbstractScreen screen) {
        MyButton screenBackButton = new BackButtonBuilder().createScreenBackButton(screen);
        screenBackButton.setPosition(MainDimen.horizontal_general_margin.getDimen() * 2,
                ScreenDimensionsManager.getScreenHeight() - MainButtonSize.BACK_BUTTON.getHeight());
        if (Gdx.app.getType() == Application.ApplicationType.iOS) {
            screen.addActor(screenBackButton);
        }
        return screenBackButton;
    }
}
