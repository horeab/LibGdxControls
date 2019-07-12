package libgdx.controls.button.builders;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import libgdx.controls.button.MainButtonSkin;
import libgdx.controls.button.MyButton;
import libgdx.preferences.SettingsService;
import libgdx.resources.MainResource;
import libgdx.resources.Res;
import libgdx.screen.AbstractScreen;

public class SoundIconButtonBuilder {

    private SettingsService settingsService = new SettingsService();

    public MyButton createSoundButton() {
        final MyButton button = new libgdx.controls.button.ButtonBuilder("")
                .setButtonSkin(getButtonSkin())
                .build();
        button.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                settingsService.toggleSound();
                button.setButtonSkin(getButtonSkin());
            }
        });
        return button;
    }

    private MainButtonSkin getButtonSkin() {
        return settingsService.isSoundOn() ? MainButtonSkin.SOUND_OFF : MainButtonSkin.SOUND_ON;
    }
}
