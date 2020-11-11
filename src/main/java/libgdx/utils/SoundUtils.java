package libgdx.utils;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import libgdx.controls.button.MyButton;
import libgdx.controls.button.builders.MusicIconButtonBuilder;
import libgdx.controls.button.builders.SoundIconButtonBuilder;
import libgdx.game.Game;
import libgdx.preferences.SettingsService;
import libgdx.resources.Res;
import libgdx.resources.dimen.MainDimen;
import libgdx.screen.AbstractScreen;

public class SoundUtils {

    private static Sound getSound(Res resource) {
        return Game.getInstance().getAssetManager().get(resource.getPath(), Sound.class);
    }

    private static Music getMusic(Res resource) {
        return Game.getInstance().getAssetManager().get(resource.getPath(), Music.class);
    }

    public static void playSound(Res resource) {
        if (new SettingsService().isSoundOn()) {
            getSound(resource).play();
        }
    }

    public static void playMusic(Res res) {
        Music music = getMusic(res);
        if (new SettingsService().isMusicOn()) {
            music.setLooping(true);
            music.setVolume(0.03f);
            music.play();
        } else {
            music.stop();
        }
    }

    public static void addSoundTable(AbstractScreen screen, Res music) {
        Table table = new Table();
        float margin = 0;
        if (music != null) {
            MyButton musicButton = new MusicIconButtonBuilder().createMusicButton(music);
            table.add(musicButton).height(musicButton.getHeight()).width(musicButton.getWidth());
            SoundUtils.playMusic(music);
            margin = MainDimen.horizontal_general_margin.getDimen();
        }
        MyButton soundButton = new SoundIconButtonBuilder().createSoundButton();
        table.add(soundButton).width(soundButton.getWidth()).height(soundButton.getHeight()).padLeft(margin);
        float x = ScreenDimensionsManager.getScreenWidth() - margin * 3 - soundButton.getWidth() / 1.5f;
        float y = ScreenDimensionsManager.getScreenHeight() - soundButton.getHeight() / 1.5f;
        table.setPosition(x, y);
        screen.addActor(table);
    }
}
