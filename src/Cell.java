public class Cell {

    private static final char WHITE = 'w';
    private static final char BLACK = 'b';
    private static final char EMPTY = '·';

    private char state;

    // Constructor privado para restringir valores inválidos
    private Cell(char state) {
        this.state = state;
    }

    // Fábricas estáticas de creación segura
    public static Cell empty() {
        return new Cell(EMPTY);
    }

    public static Cell white() {
        return new Cell(WHITE);
    }

    public static Cell black() {
        return new Cell(BLACK);
    }

    // Métodos de consulta
    public boolean isEmpty() {
        return this.state == EMPTY;
    }

    public boolean isWhite() {
        return this.state == WHITE;
    }

    public boolean isBlack() {
        return this.state == BLACK;
    }

    // Métodos modificadores (no se puede volver a EMPTY)
    public void setWhite() {
        this.state = WHITE;
    }

    public void setBlack() {
        this.state = BLACK;
    }

    // Cambia de color si no está vacía
    public void reverse() {
        if (isWhite()) {
            setBlack();
        } else if (isBlack()) {
            setWhite();
        }
        // Si está vacía, no se hace nada
    }

    public String toString() {
        return String.valueOf(this.state);
    }

    // Crea una celda desde un carácter
    public static Cell cellFromChar(char c) {
        return switch (c) {
            case 'w' -> white();
            case 'b' -> black();
            default -> empty();
        };
    }
}
