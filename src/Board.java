import java.util.StringTokenizer;

public class Board {
    private final Cell[][] cells;
    private final Display display;
    private final int order;

    private int black;
    private int white;

    public Board(int order, Display display) {
        this.order = order;
        this.cells = new Cell[2 * order][2 * order];
        this.black = 0;
        this.white = 0;
        this.display = display;
        initBoard();
    }

    public Board(int order) {
        this(order, null);
    }

    private void initBoard() {
        int size = size();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                cells[row][col] = Cell.empty(); // celda vacía por defecto
            }
        }

        // Posiciones centrales
        int mid1 = size / 2 - 1;
        int mid2 = size / 2;

        setWhite(new Position(mid1, mid1));
        setWhite(new Position(mid2, mid2));
        setBlack(new Position(mid1, mid2));
        setBlack(new Position(mid2, mid1));
    }

    public int size() {
        return 2 * order;
    }

    public boolean contains(Position position) {
        int row = position.getRow();
        int col = position.getColumn();
        return row >= 0 && row < size() && col >= 0 && col < size();
    }

    public boolean isEmpty(Position position) {
        return contains(position) && cells[position.getRow()][position.getColumn()].isEmpty();
    }

    public boolean isWhite(Position position) {
        return contains(position) && cells[position.getRow()][position.getColumn()].isWhite();
    }

    public boolean isBlack(Position position) {
        return contains(position) && cells[position.getRow()][position.getColumn()].isBlack();
    }

    public void setWhite(Position position) {
        if (isEmpty(position)) {
            cells[position.getRow()][position.getColumn()].setWhite();
            white++;
        }
        if (display != null) display.setWhite(position);
    }

    public void setBlack(Position position) {
        if (isEmpty(position)) {
            cells[position.getRow()][position.getColumn()].setBlack();
            black++;
        }
        if (display != null) display.setBlack(position);
    }

    public void reverse(Position position) {
        if(!isEmpty(position) && contains(position)){
            cells[position.getRow()][position.getColumn()].reverse();
            if(cells[position.getRow()][position.getColumn()].isBlack()){
                black++;
                white--;
            }else{
                black--;
                white++;
            }
        }
        if (display != null) display.reverse(position);
    }

    public void loadBoard(String str) {
        StringTokenizer st = new StringTokenizer(str, "\n");
        int row = 0;
        this.white = 0;
        this.black = 0;
        while (st.hasMoreTokens()) {
            String rowChars = st.nextToken();
            loadRow(this.cells[row], rowChars);
            row += 1;
        }
    }

    private void loadRow(Cell[] cellRow, String rowChars) {
        for (int column = 0; column < cellRow.length; column++) {
            Cell cell = Cell.cellFromChar(rowChars.charAt(column));
            cellRow[column] = cell;
            if (cell.isWhite()) white += 1;
            else if (cell.isBlack()) black += 1;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        for (Cell[] cell : this.cells) {
            for (Cell value : cell) {
                sb.append(value.toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getStatus() {
        return String.format("B:%3d W:%3d", this.black, this.white);
    }
}
