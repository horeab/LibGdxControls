package libgdx.utils.model;


public enum FontColor {

    WHITE(RGBColor.WHITE),
    GREEN(RGBColor.GREEN),
    BLACK(RGBColor.BLACK),
    RED(RGBColor.RED);

    private RGBColor color;

    FontColor(RGBColor color) {
        this.color = color;
    }

    public RGBColor getColor() {
        return color;
    }
}
