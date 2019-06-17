package libgdx.resources.gamelabel;

import libgdx.game.Game;
import libgdx.resources.properties.PropertiesUtils;

public enum MainGameLabel implements GameLabel {

    loading,
    privacy_policy,

    facebook_share_btn,

    rate_rate_now,
    rate_rate_later,
    rate_message,

    sound_on,
    sound_off,

    billing_remove_ads,;

    @Override
    public String getText(Object... params) {
        String language = Game.getInstance().getAppInfoService().getLanguage();
        return GameLabelUtils.getText(getKey(), language, GameLabelUtils.getMainLabelRes(language).getPath(), params);
    }

    @Override
    public String getKey() {
        return name();
    }
}
