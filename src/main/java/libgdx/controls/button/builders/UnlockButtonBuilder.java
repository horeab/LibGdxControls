package libgdx.controls.button.builders;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import libgdx.controls.animations.ActorAnimation;
import libgdx.controls.button.MainButtonSize;
import libgdx.controls.button.MainButtonSkin;
import libgdx.controls.button.MyButton;
import libgdx.game.Game;
import libgdx.resources.dimen.MainDimen;
import libgdx.resources.gamelabel.MainGameLabel;
import libgdx.screen.AbstractScreen;
import libgdx.utils.ScreenDimensionsManager;

public class UnlockButtonBuilder {

    private MyButton createScreenUnlockButton(final AbstractScreen screen, final Runnable afterBuy) {
        MyButton button = new ImageButtonBuilder(MainButtonSkin.UNLOCK_EXTRA_CONTENT, screen)
                .setFixedButtonSize(MainButtonSize.UNLOCK_CONTENT_BUTTON)
                .build();
        button.setTransform(true);
        new ActorAnimation(button, Game.getInstance().getAbstractScreen()).animateZoomInZoomOut();
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                displayInAppPurchasesPopup(afterBuy);
            }
        });
        return button;
    }

    public MyButton addHoverUnlockButton(AbstractScreen screen, Runnable afterBuy) {
        MyButton unlockBtn = createScreenUnlockButton(screen, afterBuy);
        unlockBtn.setPosition(
                ScreenDimensionsManager.getScreenWidth() - MainDimen.horizontal_general_margin.getDimen() * 1.5f - MainButtonSize.UNLOCK_CONTENT_BUTTON.getWidth(),
                ScreenDimensionsManager.getScreenHeight() - MainButtonSize.UNLOCK_CONTENT_BUTTON.getHeight() - MainDimen.vertical_general_margin.getDimen());
        screen.addActor(unlockBtn);
        return unlockBtn;
    }

    private void displayInAppPurchasesPopup(final Runnable afterBuy) {
        Game.getInstance().getInAppPurchaseManager().displayInAppPurchasesPopup(MainGameLabel.l_extracontent.getText(), new Runnable() {
            @Override
            public void run() {
                afterBuy.run();
            }
        });
    }
}
