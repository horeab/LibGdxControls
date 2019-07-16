package libgdx.controls.labelimage;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import libgdx.controls.TextTable;
import libgdx.controls.label.MyLabel;
import libgdx.controls.label.MyWrappedLabel;
import libgdx.controls.label.MyWrappedLabelConfigBuilder;
import libgdx.graphics.GraphicUtils;

public class LabelImage extends TextTable {

    private Table labelTable= new Table();
    private MyWrappedLabel label;
    private Image image;

    public LabelImage(LabelImageConfig labelImageConfig) {
        label = new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setText(labelImageConfig.getText()).setSingleLineLabel(labelImageConfig.isSingleLineLabel()).setWidth(labelImageConfig.getLabelWidth()).setFontScale(labelImageConfig.getFontScale()).setTextColor(labelImageConfig.getTextColor()).build());
        labelTable.add(label);
        if (labelImageConfig.getImage() != null) {
            image = GraphicUtils.getImage(labelImageConfig.getImage());
        }
        float imageSideDimension = labelImageConfig.getImageSideDimension();
        if (labelImageConfig.isAlignTextRight()) {
            if (image != null) {
                add(image).width(imageSideDimension).height(imageSideDimension);
            }
            add(labelTable).padLeft(labelImageConfig.getMarginBetweenLabelImage());
        } else {
            add(labelTable).padRight(labelImageConfig.getMarginBetweenLabelImage());
            if (image != null) {
                add(image).width(imageSideDimension).height(imageSideDimension);
            }
        }
        if (getLabels().size() > 1) {
            for (MyLabel label : getLabels()) {
                label.setFontScale(labelImageConfig.getFontScale() * 0.9f);
            }
        }
        setWidth(label.getWidth() + imageSideDimension);
        setHeight(imageSideDimension);
    }

    public Table getLabelTable() {
        return labelTable;
    }

    @Override
    public String getText() {
        return label.getText();
    }

    @Override
    public List<MyLabel> getLabels() {
        return label.getLabels();
    }

    public Image getImage() {
        return image;
    }

    public boolean isEmpty() {
        return (label == null || StringUtils.isBlank(label.getText())) && image == null;
    }

    public void removeElements() {
        this.label = null;
        this.image = null;
    }

}
