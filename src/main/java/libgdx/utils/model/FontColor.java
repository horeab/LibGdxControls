package libgdx.utils.model;


import com.badlogic.gdx.graphics.Color;

public enum FontColor {

    WHITE(Color.WHITE),
    GREEN(Color.FOREST),
    LIGHT_GREEN(Color.GREEN),
    OLIVE(Color.OLIVE),
    BLACK(Color.BLACK),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    GRAY(Color.GRAY),
    DARK_RED(Color.FIREBRICK),
    RED(Color.RED);

    private Color color;

    FontColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
