public class GameOfLife {
    private Board board; // El juego tiene un tablero que contiene las celdas

    // Constructor para inicializar el juego con un tablero de cierto tamaño
    public GameOfLife(int width, int height) {
        board = new Board(width, height); // Se crea un nuevo tablero con las dimensiones especificadas
    }



    // Método para establecer un nuevo tablero para el juego
    public void setBoard(boolean[][] initialPattern) {
        int height = initialPattern.length;
        int width = initialPattern[0].length;
        board = new Board(width, height);
        // Establecer el estado inicial del tablero según el patrón proporcionado
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                board.setCell(i, j, initialPattern[i][j]);
            }
        }
    }

    // Método para calcular la siguiente generación del juego
    public void execute(int generations, int sleepTime) {
        for (int generation = 1; generation <= generations; generation++) {
            // Mostrar el estado actual del tablero
            System.out.println("Generación " + generation + ":");
            renderBoard.render(board);

            // Calcular y aplicar la siguiente generación
            nextGeneration();

            // Esperar un tiempo antes de la siguiente generación
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para calcular la siguiente generación del juego
    public void nextGeneration() {
        int width = board.getWidth(); // Ancho del tablero
        int height = board.getHeight(); // Alto del tablero
        Board nextBoard = new Board(width, height); // Se crea un nuevo tablero para la siguiente generación

        // Iterar sobre cada celda en el tablero actual
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int aliveNeighbors = countAliveNeighbors(i, j); // Contar vecinos vivos de la celda actual
                boolean isCellAlive = board.getCell(i, j).isAlive(); // Obtener estado actual de la celda

                // Aplicar reglas del Juego de la Vida de Conway para determinar el estado de la celda en la siguiente generación
                if (isCellAlive) {
                    if (aliveNeighbors < 2) {
                        // Subpoblación: muere
                        nextBoard.setCell(i, j, false);
                    } else if (aliveNeighbors > 3) {
                        // Superpoblación: muere
                        nextBoard.setCell(i, j, false);
                    } else {
                        // Supervivencia: vive
                        nextBoard.setCell(i, j, true);
                    }
                } else {
                    if (aliveNeighbors == 3) {
                        // Nacimiento: cobra vida
                        nextBoard.setCell(i, j, true);
                    } else {
                        // Permanece muerta
                        nextBoard.setCell(i, j, false);
                    }
                }
            }
        }

        board = nextBoard; // Actualizar el tablero con la nueva generación
    }

    // Método para contar el número de vecinos vivos de una celda dada
    private int countAliveNeighbors(int row, int col) {
        int count = 0;
        int width = board.getWidth(); // Ancho del tablero
        int height = board.getHeight(); // Alto del tablero

        // Iterar sobre los vecinos de la celda actual
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                // Verificar que el vecino esté dentro de los límites del tablero y no sea la celda actual
                if (i >= 0 && i < height && j >= 0 && j < width && !(i == row && j == col)) {
                    // Si el vecino está vivo, incrementar el contador
                    if (board.getCell(i, j).isAlive()) {
                        count++;
                    }
                }
            }
        }

        return count; // Devolver el número total de vecinos vivos
    }
}