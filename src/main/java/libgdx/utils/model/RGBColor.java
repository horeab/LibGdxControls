package libgdx.utils.model;


import com.badlogic.gdx.graphics.Color;

public class RGBColor {

    public static final RGBColor DARK_BLUE = new RGBColor(1, 0, 0, 102);
    public static final RGBColor LIGHT_BLUE = new RGBColor(1, 179, 236, 255);
    public static final RGBColor LIGHT_MAUVE1 = new RGBColor(1, 191, 207, 240);
    public static final RGBColor LIGHT_MAUVE2 = new RGBColor(1, 219, 190, 234);
    public static final RGBColor LIGHT_FUCHSIA = new RGBColor(1, 233, 160, 210);
    public static final RGBColor LIGHT_RED1 = new RGBColor(1, 255, 235, 230);
    public static final RGBColor LIGHT_RED2 = new RGBColor(1, 247, 111, 128);
    public static final RGBColor RED = new RGBColor(1, 1, 0, 0);
    public static final RGBColor BLACK = new RGBColor(1, 0, 0, 0);
    public static final RGBColor GREY = new RGBColor(1, 192, 192, 192);
    public static final RGBColor DARK_GREEN = new RGBColor(1, 20, 140, 20);
    public static final RGBColor GREEN = new RGBColor(1, 0, 153, 0);
    public static final RGBColor WHITE = new RGBColor(1, 255, 255, 255);
    public static final RGBColor LIGHT_GREEN = new RGBColor(1, 0, 1, 0);
    public static final RGBColor YELLOW = new RGBColor(1, 1, 1, 0);


    public float a;
    public int r;
    public int g;
    public int b;

    public RGBColor(int r, int g, int b) {
        this(1, r, g, b);
    }

    public RGBColor(float a, int r, int g, int b) {
        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void setValue(RGBColor color) {
        this.a = color.a;
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
    }

    public Color toColor() {
        return new Color(r, g, b, a);
    }

    public String toHexadecimal() {
        return String.format("#%02x%02x%02x", r, g, b);
    }
}
