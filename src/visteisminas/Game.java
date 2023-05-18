/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package visteisminas;

import java.util.ArrayList;

/**
 * Clase que controla el juego del buscaminas.
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos.
 */
public class Game {

    private Cell[][] cells;
    private int raws;
    private int columns;

    /**
     * Devuelve los valores del Array de celdas.
     *
     * @return Array de celdas.
     */
    public Cell[][] getCells() {
        return cells;
    }

    /**
     * Cambia valores del Array de celdas.
     *
     * @param cells Array de celdas.
     */
    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    /**
     * Devuelve la línea de la celda.
     *
     * @return Línea de la celda.
     */
    public int getRaw() {
        return raws;
    }

    /**
     * Devuelve la columna de la celda.
     *
     * @return Columna de la celda.
     */
    public int getColumn() {
        return columns;
    }

    /**
     * Método que devolve a cela que está nunha fila e columna determinadas.
     *
     * @param raw Fila de la celda.
     * @param column Columna e la celda.
     * @return Celda con la fila y columnas pasadas como parmámetro.
     */
    public Cell getCell(int raw, int column) {
        return cells[raw][column];
    }

    /**
     * Método que obtén a lista de celas adxacentes da cela que se recibe como
     * parámetro.
     *
     * @param cell Celda a la que comprobar sus adyacentes.
     * @return Arraylist de celdas adyacentes.
     */
    private ArrayList<Cell> getAdjacentCells(Cell cell) {
        ArrayList<Cell> adjacentCells = new ArrayList();
        // Guardamos las coordenadas de la celda
        int raw = cell.getRaw();
        int column = cell.getColumn();
        // Buscamos las celdas en las coordenadas almacenadas -1 hasta +1 (en 
        // ambas), mirando que el su valor sea válido
        for (int i = raw - 1; i <= raw + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if ((i != raw || j != column) && (validIndexes(i, j))) {
                    adjacentCells.add(cells[i][j]);
                }
            }
        }
        return adjacentCells;
    }

    /**
     * Comprueba si la posición de i y j son válidas.
     *
     * @param i Posición i.
     * @param j Posición j.
     * @return Si la posición es o no válida.
     */
    private boolean validIndexes(int i, int j) {
        return i >= 0 && j >= 0 && i < raws && j < columns;
    }

    /**
     * Método que obtén a suma das minas das celas adxacentes á cela que se
     * recibe como parámetro. Fará uso do método anterior.
     *
     * @param cell Celda elegida por el jugador.
     * @return Arraylist de celdas adyacentes a la celda pasada como parámetro.
     */
    public int getAdjacentMines(Cell cell) {
        int countMines = 0;
        ArrayList<Cell> adjacent = getAdjacentCells(cell);
        for (int i = 0; i < adjacent.size(); i++) {
            if (adjacent.get(i).isMined()) {
                countMines++;
            }
        }
        return countMines;
    }

    /**
     * Método que destapa unha cela, e no caso de que o número de minas
     * adxacentes sexa cero, destapa todas as celas adxacentes que non estean
     * destapadas. Tamén fará uso do método getAdjacentCells() e é importante
     * destacar que, pola súa propia definición, é un método recursivo, xa que
     * se chama a si mesmo no seu código.
     *
     * @param cell Celda elegida por el jugador.
     */
    public void openCell(Cell cell) {
        // Abrimos la celda y si no tiene minas adyacentes, cogemos las celdas 
        // adyacentes y las abrimos, checkeando de nuevo
        cell.setState(Cell.UNCOVERED);
        if (getAdjacentMines(cell) == 0) {
            for (Cell c : getAdjacentCells(cell)) {
                if (c.getState() != Cell.UNCOVERED) {
                    openCell(c);
                }
            }
        }
    }

    /**
     * Método que destapa todas as celas que teñan minas. Usarémolo cando o
     * xogador perda para mostrarlle en que celas estaban as minas.
     */
    public void openAllMines() {
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < cells.length; j++) {
                if (cells[i][j].isMined()) {
                    cells[i][j].setState(Cell.UNCOVERED);
                }
            }
        }
    }

    /**
     * Método que comproba se quedan celas sen minas por destapar. Usarémolo
     * para saber se o xogador gañou a partida.
     *
     * @return True si se pueden destapar, false si no.
     */
    public boolean checkCellsToOpen() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if (!cells[i][j].isMined() && cells[i][j].getState() != Cell.UNCOVERED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Método que coloca no array de celas o número de minas indicado,
     * repartíndoas de forma aleatoria.
     *
     * @param mines Minas a colocar en las celdas.
     */
    private void fillMines(int mines) {
        while (mines > 0) {
            int raw = new java.util.Random().nextInt(raws);
            int column = new java.util.Random().nextInt(columns);
            if (!cells[raw][column].isMined()) {
                cells[raw][column].setMined(true);
                mines--;
            }
        }
    }

    /**
     * Constructor que crea unha nova partida. Inicializará a matriz de celas e
     * usará o método anterior para repartir as minas.
     *
     * @param raws Filas del juego.
     * @param columns Columnas del juego.
     * @param mines Minas del juego.
     */
    public Game(int raws, int columns, int mines) {
        this.raws = raws;
        this.columns = columns;
        cells = new Cell[this.raws][this.columns];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        fillMines(mines);
    }
}
