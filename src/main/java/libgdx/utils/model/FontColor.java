package libgdx.utils.model;


import com.badlogic.gdx.graphics.Color;

public enum FontColor {

    WHITE(Color.WHITE),
    GREEN(Color.FOREST),
    BLACK(Color.BLACK),
    GRAY(Color.GRAY),
    RED(Color.RED);

    private Color color;

    FontColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
