package libgdx.controls.labelimage;

import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import libgdx.graphics.GraphicUtils;
import libgdx.resources.MainResource;

public class InAppPurchaseTable {

    public Table create(Table extraContentTable) {
        Table lockBackgrTable = new Table();
        lockBackgrTable.setBackground(GraphicUtils.getNinePatch(MainResource.popup_background));
        Table table = new Table();
        Stack stack = new Stack();
        stack.add(extraContentTable);
        stack.add(lockBackgrTable);
        table.add(stack);
        return table;
    }
}
