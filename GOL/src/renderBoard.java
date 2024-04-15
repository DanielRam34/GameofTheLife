public class renderBoard {
    // Método para mostrar el estado actual del tablero en la consola
    // Método estático para renderizar el tablero en la consola
    public static void render(Board board) {
        int width = board.getWidth();
        int height = board.getHeight();

        // Iterar sobre cada celda en el tablero
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Obtener el estado de la celda (viva o muerta)
                boolean alive = board.getCell(i, j).isAlive();
                // Imprimir un carácter en función del estado de la celda (viva o muerta)
                System.out.print(alive ? "[X] " : "[0] ");
            }
            // Imprimir un salto de línea después de imprimir una fila completa del tablero
            System.out.println();
        }
        // Imprimir un salto de línea adicional para separar las generaciones del juego
        System.out.println();
    }
}