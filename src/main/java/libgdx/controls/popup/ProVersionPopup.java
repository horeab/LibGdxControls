package libgdx.controls.popup;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MainButtonSize;
import libgdx.controls.button.MyButton;
import libgdx.controls.button.builders.ButtonWithIconBuilder;
import libgdx.controls.label.MyWrappedLabel;
import libgdx.game.Game;
import libgdx.resources.FontManager;
import libgdx.resources.MainResource;
import libgdx.resources.gamelabel.MainGameLabel;
import libgdx.screen.AbstractScreen;
import libgdx.screen.AbstractScreenManager;
import libgdx.utils.InternetUtils;
import libgdx.utils.ScreenDimensionsManager;
import libgdx.utils.model.FontColor;

public class ProVersionPopup extends MyPopup<AbstractScreen, AbstractScreenManager> {

    public ProVersionPopup(AbstractScreen abstractScreen) {
        super(abstractScreen);
    }

    @Override
    public void addButtons() {
        ButtonBuilder buttonBuilder = new ButtonWithIconBuilder(MainGameLabel.pro_version_download.getText(), MainResource.crown)
                .setSingleLineLabel()
                .setDefaultButton();
        if (!Game.getInstance().getAppInfoService().isPortraitMode()) {
            buttonBuilder.setFixedButtonSize(MainButtonSize.TWO_ROW_BUTTON_SIZE);
        }
        buttonBuilder.setFontColor(FontColor.BLACK);
        MyButton button = buttonBuilder.build();
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                InternetUtils.openAppUrl(Game.getInstance().getAppInfoService().getProVersionStoreAppId(), false);
            }
        });
        addButton(button);
    }

    @Override
    protected MyWrappedLabel getLabel() {
        MyWrappedLabel label = super.getLabel();
        if (label.getLabels().size() >= 3) {
            label.getLabels().get(1).setFontScale(FontManager.calculateMultiplierStandardFontSize(2.1f));
        }
        return label;
    }


    @Override
    protected String getLabelText() {
        return MainGameLabel.pro_version_download_info.getText(Game.getInstance().getAppInfoService().getAppName());
    }
}
