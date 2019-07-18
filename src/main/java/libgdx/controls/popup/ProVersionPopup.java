package libgdx.controls.popup;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MyButton;
import libgdx.game.Game;
import libgdx.resources.FontManager;
import libgdx.resources.gamelabel.MainGameLabel;
import libgdx.screen.AbstractScreen;
import libgdx.screen.AbstractScreenManager;
import libgdx.utils.InternetUtils;

public class ProVersionPopup extends MyPopup<AbstractScreen, AbstractScreenManager> {

    public ProVersionPopup(AbstractScreen abstractScreen) {
        super(abstractScreen);
    }

    @Override
    public void addButtons() {
        MyButton button = new ButtonBuilder().setSingleLineText(MainGameLabel.pro_version_download.getText(), FontManager.getNormalFontDim())
                .setDefaultButton()
                .build();
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                InternetUtils.openAppUrl(Game.getInstance().getAppInfoService().getProVersionStoreAppId(), false);
            }
        });
        addButton(button);
    }

    @Override
    protected String getLabelText() {
        return MainGameLabel.pro_version_info.getText(Game.getInstance().getAppInfoService().getAppName());
    }
}
