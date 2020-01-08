package libgdx.controls.popup;

import libgdx.controls.button.MyButton;
import libgdx.game.Game;
import libgdx.graphics.GraphicUtils;
import libgdx.resources.MainResource;
import libgdx.resources.dimen.MainDimen;
import libgdx.resources.gamelabel.MainGameLabel;
import libgdx.screen.AbstractScreen;
import libgdx.screen.AbstractScreenManager;

public class InAppPurchasesPopup extends MyPopup<AbstractScreen, AbstractScreenManager> {

    private String description;
    private MyButton buyButton;
    private MyButton restoreButton;

    public InAppPurchasesPopup(AbstractScreen abstractScreen,
                               String description,
                               MyButton buyBtn,
                               MyButton restoreBtn) {
        super(abstractScreen);
        this.description = description;
        this.buyButton = buyBtn;
        this.restoreButton = restoreBtn;
    }

    @Override
    public void addButtons() {
        addButton(buyButton);
        float imgDimen = MainDimen.horizontal_general_margin.getDimen() * 13;
        getContentTable().add(GraphicUtils.getImage(MainResource.mug)).height(imgDimen).width(imgDimen);
        addButton(restoreButton);
    }

    @Override
    protected String getLabelText() {
        return description;
    }
}
