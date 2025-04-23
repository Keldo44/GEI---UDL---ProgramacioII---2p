

public class Cell {

    private static final char WHITE = 'w';
    private static final char BLACK = 'b';
    private static final char EMPTY = '·';

    private char state;

    private Cell(char state) {
        throw new UnsupportedOperationException("Step 3");
    }

    public static Cell empty() {
        throw new UnsupportedOperationException("Step 3");
    }

    public static Cell white() {
        throw new UnsupportedOperationException("Step 3");
    }

    public static Cell black() {
        throw new UnsupportedOperationException("Step 3");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("Step 3");
    }

    public boolean isWhite() {
        throw new UnsupportedOperationException("Step 3");
    }

    public boolean isBlack() {
        throw new UnsupportedOperationException("Step 3");
    }

    public void setWhite() {
        throw new UnsupportedOperationException("Step 3");
    }

    public void setBlack() {
        throw new UnsupportedOperationException("Step 3");
    }

    public void reverse() {
        throw new UnsupportedOperationException("Step 3");
    }

    public String toString() {
        return String.valueOf(this.state);
    }

    public static Cell cellFromChar(char c) {
        return switch (c) {
            case 'w' -> white();
            case 'b' -> black();
            default -> empty();
        };
    }
}
