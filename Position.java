public class Position {
    private int x,y;
    private Colour colour;

    public Position(int x, int y, Colour colour) {
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Colour getColour() {
        return colour;
    }
}