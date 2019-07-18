package libgdx.resources.gamelabel;

import libgdx.game.Game;
import libgdx.resources.properties.PropertiesUtils;

public enum MainGameLabel implements GameLabel {

    font_name,
    loading,

    rate_rate_now,
    rate_rate_later,
    rate_message,

    billing_remove_ads,
    pro_version_info,
    pro_version_download,;

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
