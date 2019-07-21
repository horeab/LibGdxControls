package libgdx.controls.button;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import libgdx.transactions.TransactionAmount;
import libgdx.controls.label.MyLabel;
import libgdx.controls.labelimage.InventoryTableBuilder;
import libgdx.controls.labelimage.LabelImage;
import libgdx.controls.labelimage.LabelImageConfigBuilder;
import libgdx.game.Game;
import libgdx.resources.FontManager;
import libgdx.resources.Res;
import libgdx.resources.dimen.MainDimen;

public class ButtonBuilder {

    public static final String CENTER_TEXT_IMAGE_ROW_NAME = "centerTextImageColumn";

    private Table centerTextImageColumn = new Table();
    private List<Table> rewardRows = new ArrayList<>();

    private LabelImage centerTextImageColumnLabelImage;

    private ButtonSize fixedButtonSize;
    private ButtonSkin buttonSkin;
    private List<Action> actions = new ArrayList<>();
    private List<ClickListener> clickListeners = new ArrayList<>();
    private List<ChangeListener> changeListeners = new ArrayList<>();
    private String buttonName;
    private boolean disabled;

    public ButtonBuilder() {
    }

    public ButtonBuilder(String text) {
        this(text, FontManager.getNormalFontDim());
    }

    public ButtonBuilder(String text, float fontScale) {
        centerTextImageColumnLabelImage = createTextTable(text, fontScale);
    }

    protected LabelImage createTextTable(String text, float fontScale) {
        return createTextTable(text, new GlyphLayout(Game.getInstance().getFontManager().getFont(), text).width, fontScale);
    }

    protected LabelImage createTextTable(String text, float tableWidth, float fontScale) {
        return new LabelImage(new LabelImageConfigBuilder().setWrappedLineLabel(tableWidth).setFontScale(fontScale).setText(text).build());
    }

    public ButtonBuilder setSingleLineText(String text, float fontScale) {
        LabelImage labelImage = new LabelImage(new LabelImageConfigBuilder().setSingleLineLabel().setFontScale(fontScale).setText(text).build());
        addCenterTextImageColumn(labelImage);
        return this;
    }


    public ButtonBuilder setWrappedText(LabelImageConfigBuilder labelImageConfigBuilder) {
        LabelImage labelImage = new LabelImage(labelImageConfigBuilder.build());
        addCenterTextImageColumn(labelImage);
        return this;
    }

    public ButtonBuilder setWrappedText(String text, float width) {
        return setWrappedText(new LabelImageConfigBuilder().setText(text).setFontScale(FontManager.getNormalFontDim()).setWrappedLineLabel(width));
    }

    public ButtonBuilder setText(String text) {
        centerTextImageColumnLabelImage = createTextTable(text, FontManager.getNormalFontDim());
        return this;
    }

    public ButtonBuilder setButtonName(String buttonName) {
        this.buttonName = buttonName;
        return this;
    }

    public ButtonBuilder setFixedButtonSize(ButtonSize buttonSize) {
        this.fixedButtonSize = buttonSize;
        return this;
    }

    public ButtonBuilder setButtonSkin(ButtonSkin buttonSkin) {
        this.buttonSkin = buttonSkin;
        return this;
    }

    public ButtonBuilder setDefaultButton() {
        buttonSkin = MainButtonSkin.DEFAULT;
        return this;
    }

    public ButtonBuilder setDisabled(boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public ButtonBuilder setLowColorPopupButton() {
        ButtonBuilder buttonBuilder = setDefaultButton();
        buttonBuilder.setButtonSkin(MainButtonSkin.LOW_COLOR);
        return buttonBuilder;
    }

    public ButtonBuilder setRewardBottomRow(TransactionAmount transactionAmount) {
        if (Game.getInstance().getLoginService().isUserLoggedIn()) {
            addRewardBottomRow(createInventoryTableBuilder(transactionAmount));
        }
        return this;
    }

    private InventoryTableBuilder createInventoryTableBuilder(TransactionAmount transactionAmount) {
        return Game.getInstance().getMainDependencyManager().createInventoryTableBuilderCreator().create(transactionAmount);
    }

    public ButtonBuilder setRewardBottomRow(Table table) {
        addRewardTable(table);
        return this;
    }

    public void setLoggedOutRewardBottomRow(TransactionAmount transactionAmountEnum) {
        addRewardBottomRow(createInventoryTableBuilder(transactionAmountEnum).setAlsoForLoggedOut(true));
    }


    private void addRewardBottomRow(InventoryTableBuilder inventoryTableBuilder) {
        addRewardTable(inventoryTableBuilder.buildTransactionTable());
    }

    private void addRewardTable(Table table) {
        rewardRows.add(table);
    }

    public ButtonBuilder addCenterTextImageColumn(Table table) {
        centerTextImageColumn.add(table);
        return this;
    }

    public ButtonBuilder addClickListener(ClickListener clickListener) {
        clickListeners.add(clickListener);
        return this;
    }

    public ButtonBuilder addChangeListener(ChangeListener changeListener) {
        changeListeners.add(changeListener);
        return this;
    }

    public ButtonBuilder addAction(Action action) {
        actions.add(action);
        return this;
    }

    public MyButton build() {
        processButtonTable();
        MyButton myButton = new MyButton(getButtonSize(), buttonSkin);
        if (StringUtils.isNotBlank(buttonName)) {
            myButton.setName(buttonName);
        }
        addCenterRow(myButton);
        addListenersAndActions(myButton);
        myButton.setDisabled(disabled);
        float buttonLabelsHeight = getButtonLabelsHeight(myButton.getCenterRowLabels());
        while (buttonLabelsHeight > myButton.getHeight() / 1.1f) {
            myButton.setHeight(myButton.getHeight() * 1.1f);
        }
        return myButton;
    }

    private float getButtonLabelsHeight(Collection<MyLabel> labels) {
        float height = 0;
        for (MyLabel myLabel : labels) {
            height += myLabel.getPrefHeight();
        }
        return height;
    }

    private void addCenterRow(MyButton myButton) {
        if (!isTableEmpty(centerTextImageColumn)) {
            myButton.add(centerTextImageColumn).padTop(MainDimen.vertical_general_margin.getDimen()).padBottom(MainDimen.vertical_general_margin.getDimen()).expand();
        }
        myButton.row();
    }

    private void addListenersAndActions(MyButton myButton) {
        for (ClickListener listener : clickListeners) {
            if (listener != null) {
                myButton.addListener(listener);
            }
        }
        for (ChangeListener listener : changeListeners) {
            if (listener != null) {
                myButton.addListener(listener);
            }
        }
        for (Action action : actions) {
            if (action != null) {
                myButton.addAction(action);
            }
        }
    }

    private void processButtonTable() {
        if (centerTextImageColumnLabelImage != null) {
            centerTextImageColumn.add(centerTextImageColumnLabelImage);
        }
        addRewardRows();
        centerTextImageColumn.setName(CENTER_TEXT_IMAGE_ROW_NAME);
    }

    private void addRewardRows() {
        if (centerTextImageColumn.getChildren().size > 0 && centerTextImageColumn.getChildren().get(0) instanceof LabelImage) {
            LabelImage labelImage = (LabelImage) centerTextImageColumn.getChildren().get(0);
            for (Table reward : rewardRows) {
                labelImage.getLabelTable().row();
                labelImage.getLabelTable().add(reward);
            }
        }
    }

    protected ButtonSize getButtonSize() {
        if (fixedButtonSize != null) {
            return fixedButtonSize;
        }
        ButtonSize buttonSize = MainButtonSize.ONE_ROW_BUTTON_SIZE;
        if (nrOfRowsToDisplay() == 3) {
            buttonSize = MainButtonSize.THREE_ROW_BUTTON_SIZE;
        } else if (nrOfRowsToDisplay() == 2) {
            buttonSize = MainButtonSize.TWO_ROW_BUTTON_SIZE;
        }
        return buttonSize;
    }

    private int nrOfRowsToDisplay() {
        int nrOfRowsToDisplay = 0;
        nrOfRowsToDisplay = !isTableEmpty(centerTextImageColumn) ? nrOfRowsToDisplay + 1 : nrOfRowsToDisplay;
        nrOfRowsToDisplay = nrOfRowsToDisplay + rewardRows.size();
        return nrOfRowsToDisplay;
    }

    private boolean isTableEmpty(Table table) {
        boolean isTableEmpty = true;
        if (table.getChildren().size != 0) {
            for (Actor actor : table.getChildren()) {
                if (actor instanceof LabelImage) {
                    isTableEmpty &= ((LabelImage) actor).isEmpty();
                } else {
                    isTableEmpty = false;
                }
            }
        }
        return isTableEmpty;
    }
}
