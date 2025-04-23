
public class Game {

    private final Board board;
    private State state;

    public Game(Board board) {
        this.board = board;
        // Suponemos que el jugador con las fichas negras puede hacer la primera jugada
        // (es decir, el tablero es de orden como mínimo 2)
        this.state = State.BLACK;
    }

    public boolean isFinished() {
        return this.state == State.FINISHED;
    }

    public boolean isSame(State player, Position position) {
        if (player == State.BLACK) {
            return board.isBlack(position);
        } else if (player == State.WHITE) {
            return board.isWhite(position);
        }
        return false; // No debería llegar aquí si FINISHED no se pasa nunca
    }

    public boolean isOther(State player, Position position) {
        if (player == State.BLACK) {
            return board.isWhite(position);
        } else if (player == State.WHITE) {
            return board.isBlack(position);
        }
        return false; // No debería llegar aquí si FINISHED no se pasa nunca
    }


    public boolean someSame(State player, Position position, Direction direction) {
        while (board.contains(position) && !board.isEmpty(position)) {
            if (isSame(player, position)) {
                return true;
            }
            // Seguimos avanzando
            position = direction.move(position); // Avanzamos desde la posición original
        }
        return false; // Nos salimos del tablero sin encontrar una del mismo color
    }



    public boolean isReverseDirection(State player, Position position, Direction direction) {
        Position next = direction.move(position);
        return isOther(player, next) && someSame(player, next, direction);
    }


    public boolean[] directionsOfReverse(State player, Position position) {
        boolean[] result = new boolean[Direction.ALL.length];

        for (int i = 0; i < Direction.ALL.length; i++) {
            Direction dir = Direction.ALL[i];
            result[i] = isReverseDirection(player, position, dir);
        }
        return result;
    }

    private static boolean allFalse(boolean[] bools) {
        for (boolean b : bools) {
            if (b) return false;
        }
        return true;
    }


    public boolean canPlayPosition(State player, Position position) {
        if (!board.isEmpty(position)) return false;
        boolean[] directions = directionsOfReverse(player, position);
        return !allFalse(directions);
    }


    public boolean canPlay(State player) {
        int size = board.size();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Position p = new Position(row, col);
                if (canPlayPosition(player, p)) return true;
            }
        }

        return false;
    }


    private void disk(Position position) {
        if (state == State.BLACK) {
            board.setBlack(position);
        } else if (state == State.WHITE) {
            board.setWhite(position);
        }
    }


    private void reverse(Position position, Direction direction) {
        Position p = direction.move(position);
        while (!isSame(state, p)) {
            board.reverse(p);
            p = direction.move(p);
        }
    }


    private void reverse(Position position, boolean[] directions) {
        for (int i = 0; i < directions.length; i++) {
            if (directions[i]) {
                reverse(position, Direction.ALL[i]);
            }
        }
    }


    private void changeTurn() {
        State opponent = (state == State.BLACK) ? State.WHITE : State.BLACK;
        if (canPlay(opponent)) {
            state = opponent;
        } else if (!canPlay(state)) {
            state = State.FINISHED;
        }
    }


    public void move(Position position) {
        if (!this.board.isEmpty(position)) return;
        boolean[] directions = this.directionsOfReverse(this.state, position);
        if (allFalse(directions)) return;
        this.disk(position);
        this.reverse(position, directions);
        this.changeTurn();
    }

    public String getStatus() {
        String move;
        if (this.state == State.FINISHED) {
            move = "FINISHED";
        } else {
            move = (this.state == State.BLACK ? "BLACK" : "WHITE") + " moves";
        }
        return String.format("%s - %s", this.board.getStatus(), move);
    }
}

