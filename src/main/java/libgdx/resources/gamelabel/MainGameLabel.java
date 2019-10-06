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
    pro_version_info_unlock,
    pro_version_download,
    l_level,
    l_level_record,
    l_score,
    l_score_record,
    l_new_game,

    guest,

    privacy_policy,
    facebook_share_btn;

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
