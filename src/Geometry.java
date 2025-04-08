import acm.graphics.GDimension;
import acm.graphics.GPoint;

public class Geometry {

    private final int windowWidth;
    private final int windowHeight;
    private final int numCols;
    private final int numRows;
    private final double boardPadding;
    private final double cellPadding;

    public Geometry(int windowWidth, int windowHeight, int numCols, int numRows, double boardPadding, double cellPadding) {
        throw new UnsupportedOperationException("Step 4");
    }

    public int getRows() {
        throw new UnsupportedOperationException("Step 4");
    }

    public int getColumns() {
        throw new UnsupportedOperationException("Step 4");
    }

    public GDimension boardDimension() {
        throw new UnsupportedOperationException("Step 4");
    }

    public GPoint boardTopLeft() {
        throw new UnsupportedOperationException("Step 4");
    }

    public GDimension cellDimension() {
        throw new UnsupportedOperationException("Step 4");
    }

    public GPoint cellTopLeft(int x, int y) {
        throw new UnsupportedOperationException("Step 4");
    }

    public GDimension tokenDimension() {
        throw new UnsupportedOperationException("Step 4");
    }

    public GPoint tokenTopLeft(int x, int y) {
        throw new UnsupportedOperationException("Step 4");
    }

    public GPoint centerAt(int x, int y) {
        throw new UnsupportedOperationException("Step 4");
    }

    //Implemented
    public Position xyToCell(double x, double y) {
        GPoint boardTopLeft = boardTopLeft();
        GDimension cellDimension = cellDimension();
        return new Position(
                (int) ((x - boardTopLeft.getX()) / cellDimension.getWidth()),
                (int) ((y - boardTopLeft.getY()) / cellDimension.getHeight()));
    }
}
