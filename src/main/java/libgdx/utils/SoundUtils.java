package libgdx.utils;

import com.badlogic.gdx.audio.Sound;

import libgdx.game.Game;
import libgdx.preferences.SettingsService;
import libgdx.resources.Res;

public class SoundUtils {

    private static Sound getSound(Res resource) {
        return Game.getInstance().getAssetManager().get(resource.getPath(), Sound.class);
    }

    public static void playSound(Res resource) {
        if (new SettingsService().isSoundOn()) {
            getSound(resource).play();
        }
    }
}
