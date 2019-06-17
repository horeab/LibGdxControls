package libgdx.resources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;

import libgdx.game.Game;

public enum MainResource implements Res {

    skin("skin/skin.json", Skin.class),
    font("skin/font.fnt", BitmapFont.class),
    big_font("skin/big_font.fnt", BitmapFont.class),
    textureAtlas("skin/skin.atlas", TextureAtlas.class),

    popup_background("backgrounds/popup_background.png", Texture.class),
    transparent_background("backgrounds/transparent_background.png", Texture.class),

    background_texture("general/background_texture.png", Texture.class),
    splashtitle("general/splashtitle.png", Texture.class),
    remove("general/remove.png", Texture.class),
    magnify_glass("general/magnify_glass.png", Texture.class),
    error("general/error.png", Texture.class),
    play("general/play.png", Texture.class),
    skip("general/skip.png", Texture.class),

    btn_lowcolor_down("buttons/btn_lowcolor_down.png", Texture.class),
    btn_lowcolor_up("buttons/btn_lowcolor_up.png", Texture.class),
    btn_menu_down("buttons/btn_menu_down.png", Texture.class),
    btn_menu_up("buttons/btn_menu_up.png", Texture.class),
    btn_settings_up("buttons/btn_settings_up.png", Texture.class),
    btn_settings_down("buttons/btn_settings_down.png", Texture.class),
    btn_back_up("buttons/btn_back_up.png", Texture.class),
    btn_back_down("buttons/btn_back_down.png", Texture.class),

    main_labels_da("labels/main_labels_da", I18NBundle.class),
    main_labels_de("labels/main_labels_de", I18NBundle.class),
    main_labels_en("labels/main_labels_en", I18NBundle.class),
    main_labels_es("labels/main_labels_es", I18NBundle.class),
    main_labels_it("labels/main_labels_it", I18NBundle.class),
    main_labels_fr("labels/main_labels_fr", I18NBundle.class),
    main_labels_hu("labels/main_labels_hu", I18NBundle.class),
    main_labels_nl("labels/main_labels_nl", I18NBundle.class),
    main_labels_pl("labels/main_labels_pl", I18NBundle.class),
    main_labels_pt("labels/main_labels_pt", I18NBundle.class),
    main_labels_ro("labels/main_labels_ro", I18NBundle.class),
    main_labels_tr("labels/main_labels_tr", I18NBundle.class),
    ;

    private String path;
    private Class<?> classType;

    MainResource(String path, Class<?> classType) {
        this.path = path;
        this.classType = classType;
    }

    @Override
    public Class<?> getClassType() {
        return classType;
    }

    @Override
    public String getPath() {
        return Game.getInstance().getAppInfoService().getMainResourcesFolder() + path;
    }
}
