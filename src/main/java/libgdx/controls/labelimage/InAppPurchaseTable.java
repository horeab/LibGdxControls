package libgdx.controls.labelimage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import libgdx.controls.animations.ActorAnimation;
import libgdx.game.Game;
import libgdx.graphics.GraphicUtils;
import libgdx.resources.MainResource;
import libgdx.resources.dimen.MainDimen;
import libgdx.resources.gamelabel.MainGameLabel;
import libgdx.utils.InAppPurchaseManager;
import libgdx.utils.Utils;

public class InAppPurchaseTable {


    public Table initExtraContentTable() {
        Table extraContentTable = null;
        if (!Utils.isValidExtraContent()) {
            extraContentTable = new Table();
        }
        return extraContentTable;
    }


    public Table create(Table extraContentTable) {
        return create(extraContentTable, InAppPurchaseManager.defaultRedirectScreenRunnable());
    }

    public Table create(Table extraContentTable, final Runnable executeAfterBought) {
        Table lockBackgrTable = new Table();
        lockBackgrTable.setBackground(GraphicUtils.getNinePatch(MainResource.inappurchase_background));
        float imgDimen = MainDimen.horizontal_general_margin.getDimen() * 15;
        Image image = GraphicUtils.getImage(MainResource.unlock);
        image.setWidth(imgDimen);
        image.setHeight(imgDimen);
        new ActorAnimation(image, Game.getInstance().getAbstractScreen()).animateFadeInFadeOut();
        lockBackgrTable.add(image).width(imgDimen).height(imgDimen);
        Table table = new Table();
        Stack stack = new Stack();
        stack.add(extraContentTable);
        stack.add(lockBackgrTable);
        table.add(stack);
        table.setTouchable(Touchable.enabled);
        table.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Game.getInstance().getInAppPurchaseManager().displayInAppPurchasesPopup(executeAfterBought);
            }
        });
        return table;
    }
}
