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
        return this.numRows;
    }

    public int getColumns() {
        return this.numCols;
    }

    public GDimension boardDimension() {
        double width = this.windowWidth - (2 * this.boardPadding * this.windowWidth);
        double height = this.windowHeight - (2 * this.boardPadding * this.windowHeight);
        return new GDimension(width, height);
    }

    public GPoint boardTopLeft() {
        return new GPoint(boardPadding*this.windowWidth, boardPadding*this.windowHeight);
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
        double width = cell.getWidth() - (2 * this.cellPadding*cell.getWidth());
        double height = cell.getHeight() - (2 * this.cellPadding*cell.getHeight());
        return new GDimension(width, height);
    }

    public GPoint tokenTopLeft(int x, int y) {
        GPoint cellTopLeft = cellTopLeft(x, y);
        GDimension cell = cellDimension();
        return new GPoint(cellTopLeft.getX()+this.cellPadding * cell.getWidth(), cellTopLeft.getY()+this.cellPadding * cell.getHeight());
    }

    public GPoint centerAt(int x, int y) {
        GPoint cellTopLeft = cellTopLeft(x, y);
        GDimension cell = cellDimension();
        double centerX = cellTopLeft.getX() + cell.getWidth() / 2;
        double centerY = cellTopLeft.getY() + cell.getHeight() / 2;
        return new GPoint(centerX, centerY);
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
