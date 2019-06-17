package libgdx.controls.button.builders;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import libgdx.controls.button.MainButtonSize;
import libgdx.controls.button.MainButtonSkin;
import libgdx.controls.button.MyButton;
import libgdx.screen.AbstractScreen;

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
}
