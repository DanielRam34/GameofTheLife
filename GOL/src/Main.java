import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int width = 20;
        int height = 10;
        int generations = 0;
        int sleepTime = 500;
        String pattern = "";

        // Analizar los argumentos de la línea de comandos
        for (String arg : args) {
            if (arg.contains("=")) {
                String[] parts = arg.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    // Validación de las claves y sus valores
                    switch (key) {
                        case "w":
                            try {
                                width = Integer.parseInt(value);
                            } catch (NumberFormatException e) {
                                System.out.println("width = [Invalid]");
                            }
                            break;
                        case "h":
                            try {
                                height = Integer.parseInt(value);
                            } catch (NumberFormatException e) {
                                System.out.println("height = [Invalid]");
                            }
                            break;
                        case "g":
                            try {
                                generations = Integer.parseInt(value);
                            } catch (NumberFormatException e) {
                                System.out.println("generations = [Invalid]");
                            }
                            break;
                        case "s":
                            try {
                                sleepTime = Integer.parseInt(value);
                            } catch (NumberFormatException e) {
                                System.out.println("sleepTime = [Invalid]");
                            }
                            break;
                        case "p":
                            if (value.equalsIgnoreCase("rnd")) {
                                pattern = generateRandomPattern(width, height);
                            } else {
                                pattern = value;
                            }
                            break;
                        default:
                            System.out.println(key + " = [Not recognized]");
                            break;
                    }
                } else {
                    System.out.println("Argumento inválido: " + arg);
                }
            } else {
                System.out.println("Argumento inválido: " + arg);
            }
        }

        // Crear una instancia del juego de la vida
        GameOfLife juego = new GameOfLife(width, height);

        // Iniciar el juego con el patrón inicial específico
        boolean[][] initialPattern = parsePattern(pattern, width, height);
        juego.setBoard(initialPattern);

        // Ejecutar el juego durante el número de generaciones especificado
        juego.execute(generations, sleepTime);
    }
        // Toma un patrón representado como una cadena de caracteres, junto con el ancho y el alto del tablero,
        // y devuelve una matriz booleana que representa el patrón inicial del juego de la vida
    private static boolean[][] parsePattern(String pattern, int width, int height) {
        boolean[][] initialPattern = new boolean[height][width];
        int row = 0;
        int col = 0;
        // Validación del patrón inicial
        for (int i = 0; i < pattern.length(); i++) {
            char currentChar = pattern.charAt(i);

            if (currentChar == '0' || currentChar == '1') {
                // Asignar el valor de la celda en la matriz initialPattern
                initialPattern[row][col] = (currentChar == '1');
                // Mover a la siguiente columna
                col++;

                // Verificar si se llegó al final de la fila
                if (col == width) {
                    // Reiniciar la columna y avanzar a la siguiente fila
                    col = 0;
                    row++;
                }

                // Verificar si se llegó al final de la matriz
                if (row == height) {
                    break; // Se completó la matriz, salir del bucle
                }
            } else if (currentChar == '#') {
                // Se encontró un separador, continuar con la siguiente fila
                if (row < height - 1) {
                    row++;
                } else {
                    break; // Se ha llegado al final del tablero, salir del bucle
                }
                // Reiniciar la columna
                col = 0;
            }
        }

        return initialPattern;
    }

    private static String generateRandomPattern(int width, int height) {
        StringBuilder patternBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boolean alive = random.nextBoolean();
                patternBuilder.append(alive ? '1' : '0');
            }
            // Agregar salto de línea después de cada fila
            patternBuilder.append("#");
        }
        // Eliminar el último salto de línea adicional
        patternBuilder.deleteCharAt(patternBuilder.length() - 1);
        return patternBuilder.toString();
    }
}
