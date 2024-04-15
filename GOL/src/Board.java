public class Board {
    private Cell[][] cells;
    private int width;
    private int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[height][width]; // Corregir el orden de las dimensiones
        // Inicializar todas las celdas como muertas
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(false);
            }
        }
    }

    // Método para obtener el ancho del tablero
    public int getWidth() {
        return width;
    }

    // Método para obtener el alto del tablero
    public int getHeight() {
        return height;
    }

    // Método para obtener la celda en la posición especificada
    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    // Método para establecer el estado de la celda
    public void setCell(int row, int col, boolean alive) {

        cells[row][col].setAlive(alive);
    }
}