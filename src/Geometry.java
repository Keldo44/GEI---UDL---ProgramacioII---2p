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
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.numCols = numCols;
        this.numRows = numRows;
        this.boardPadding = boardPadding;
        this.cellPadding = cellPadding;
    }

    public int getRows() {
        return numRows;
    }

    public int getColumns() {
        return numCols;
    }

    public GDimension boardDimension() {
        double width = windowWidth * (1.0 - 2 * boardPadding);
        double height = windowHeight * (1.0 - 2 * boardPadding);
        return new GDimension(width, height);
    }

    public GPoint boardTopLeft() {
        double x = windowWidth * boardPadding;
        double y = windowHeight * boardPadding;
        return new GPoint(x, y);
    }

    public GDimension cellDimension() {
        GDimension board = boardDimension();
        double width = board.getWidth() / numCols;
        double height = board.getHeight() / numRows;
        return new GDimension(width, height);
    }

    public GPoint cellTopLeft(int x, int y) {
        GPoint boardTopLeft = boardTopLeft();
        GDimension cell = cellDimension();
        double cx = boardTopLeft.getX() + x * cell.getWidth();
        double cy = boardTopLeft.getY() + y * cell.getHeight();
        return new GPoint(cx, cy);
    }

    public GDimension tokenDimension() {
        GDimension cell = cellDimension();
        double width = cell.getWidth() * (1.0 - 2 * cellPadding);
        double height = cell.getHeight() * (1.0 - 2 * cellPadding);
        return new GDimension(width, height);
    }

    public GPoint tokenTopLeft(int x, int y) {
        GPoint cellTopLeft = cellTopLeft(x, y);
        GDimension cell = cellDimension();
        double offsetX = cell.getWidth() * cellPadding;
        double offsetY = cell.getHeight() * cellPadding;
        return new GPoint(cellTopLeft.getX() + offsetX, cellTopLeft.getY() + offsetY);
    }

    public GPoint centerAt(int x, int y) {
        GPoint cellTopLeft = cellTopLeft(x, y);
        GDimension cell = cellDimension();
        double centerX = cellTopLeft.getX() + cell.getWidth() / 2;
        double centerY = cellTopLeft.getY() + cell.getHeight() / 2;
        return new GPoint(centerX, centerY);
    }

    public Position xyToCell(double x, double y) {
        GPoint boardTopLeft = boardTopLeft();
        GDimension cellDimension = cellDimension();
        return new Position(
                (int) ((x - boardTopLeft.getX()) / cellDimension.getWidth()),
                (int) ((y - boardTopLeft.getY()) / cellDimension.getHeight()));
    }
}
