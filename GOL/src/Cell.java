public class Cell {
    // Atributo para almacenar el estado de la celda
    private boolean alive;

    // Constructor para crear una nueva instancia de Cell con un estado inicial dado
    public Cell(boolean alive) {
        this.alive = alive; // Se inicializa el atributo 'alive' con el valor pasado como parámetro
    }

    // Método para obtener el estado actual de la celda
    public boolean isAlive() {
        return alive;
        // Se devuelve el valor del atributo 'alive'
    }

    // Método para establecer el estado de la celda
    public void setAlive(boolean alive) {
        this.alive = alive; // Se actualiza el valor del atributo 'alive' con el valor pasado como parámetro
    }
}