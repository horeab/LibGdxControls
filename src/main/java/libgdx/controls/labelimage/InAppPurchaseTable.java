package libgdx.controls.labelimage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import libgdx.constants.Language;
import libgdx.controls.animations.ActorAnimation;
import libgdx.controls.popup.ProVersionPopup;
import libgdx.game.Game;
import libgdx.graphics.GraphicUtils;
import libgdx.resources.MainResource;
import libgdx.resources.Res;
import libgdx.resources.dimen.MainDimen;
import libgdx.resources.gamelabel.MainGameLabel;
import libgdx.utils.InAppPurchaseManager;
import libgdx.utils.ScreenDimensionsManager;
import libgdx.utils.Utils;

public class InAppPurchaseTable {


    public Table initExtraContentTable() {
        Table extraContentTable = null;
        if (!Utils.isValidExtraContent()) {
            extraContentTable = new Table();
        }
        return extraContentTable;
    }


    public Table createForProVersion(Table extraContentTable, boolean withParentalGate) {
        Table table = createUnlockTable(extraContentTable, getUnlockImageSideDimen());
        table.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ProVersionPopup proVersionPopup = new ProVersionPopup(Game.getInstance().getAbstractScreen(), withParentalGate) {

                };
                proVersionPopup.addToPopupManager();
            }
        });
        return table;
    }

    public Table create(Table extraContentTable, String defaultLanguage, String defaultText, float imgDimen) {
        return create(extraContentTable, defaultLanguage, defaultText, new Runnable() {
            @Override
            public void run() {
                InAppPurchaseManager.defaultRedirectScreenRunnable().run();
            }
        }, imgDimen);
    }


    public Table create(Table extraContentTable, String defaultLanguage, String defaultText) {
        return create(extraContentTable, defaultLanguage, defaultText, new Runnable() {
            @Override
            public void run() {
                InAppPurchaseManager.defaultRedirectScreenRunnable().run();
            }
        });
    }


    public Table create(Table extraContentTable, String defaultLanguage, String defaultText, final Runnable executeAfterBought) {
        return create(extraContentTable, defaultLanguage, defaultText, executeAfterBought, getUnlockImageSideDimen());
    }

    public Table create(Table extraContentTable, String defaultLanguage, String defaultText, final Runnable executeAfterBought, float imgDimen) {
        Table table = createUnlockTable(extraContentTable, imgDimen);
        table.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Game.getInstance().getInAppPurchaseManager().displayInAppPurchasesPopup(defaultLanguage, defaultText, executeAfterBought);
            }
        });
        return table;
    }

    private Table createUnlockTable(Table extraContentTable, float imgDimen) {
        Table lockBackgrTable = new Table();
        Image image = GraphicUtils.getImage(getUnlockRes());
        setLockedTableBackground(lockBackgrTable, image);
        image.setWidth(imgDimen);
        image.setHeight(imgDimen);
        lockBackgrTable.add(image).width(imgDimen).height(imgDimen);
        Table table = new Table();
        Stack stack = new Stack();
        stack.add(extraContentTable);
        stack.add(lockBackgrTable);
        table.add(stack).width(imgDimen).height(imgDimen);
        table.setTouchable(Touchable.enabled);
        return table;
    }

    protected Res getUnlockRes() {
        return MainResource.unlock;
    }

    protected void setLockedTableBackground(Table lockBackgrTable, Image image) {
        new ActorAnimation(image, Game.getInstance().getAbstractScreen()).animateFadeInFadeOut();
        lockBackgrTable.setBackground(GraphicUtils.getNinePatch(MainResource.inappurchase_background));
    }

    protected float getUnlockImageSideDimen() {
        return MainDimen.horizontal_general_margin.getDimen() * 15;
    }
}
