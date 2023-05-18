/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visteisminas;

/**
 * Clase que implementa una celda del panel.
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos.
 */
public class Cell {

    private boolean mined = false;
    private int raw;
    private int column;
    private int state = COVERED; // Tapada (1), marcada (2) e destapada (3)

    /**
     * Referencia al estado tapado de la celda.
     */
    public static final int COVERED = 1;

    /**
     * Referencia al estado marcado de la celda.
     */
    public static final int MARKED = 2;

    /**
     * Referencia al estado destapado de la celda.
     */
    public static final int UNCOVERED = 3;

    /**
     * Constructor de la clase Cell.
     *
     * @param raw Línea de la celda.
     * @param column Columna de la celda.
     */
    public Cell(int raw, int column) {
        this.raw = raw;
        this.column = column;
    }

    /**
     * Devuelve la línea de la celda.
     *
     * @return Línea de la celda.
     */
    public int getRaw() {
        return raw;
    }

    /**
     * Cambia la línea de la celda.
     *
     * @param raw Línea de la celda.
     */
    public void setRaw(int raw) {
        this.raw = raw;
    }

    /**
     * Devuelve la columna de la celda.
     *
     * @return Columna de la celda.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Cambia la columna de la celda.
     *
     * @param column Columna de la celda.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Cambia una celda a minada o no.
     *
     * @return True si está minada, false si no.
     */
    public boolean isMined() {
        return mined;
    }

    /**
     * Devuelve si la mina está minada o no.
     *
     * @param mined True si está minada, false si no.
     */
    public void setMined(boolean mined) {
        this.mined = mined;
    }

    /**
     * Cambia el estado de la celda.
     *
     * @return Estado de la celda.
     */
    public int getState() {
        return state;
    }

    /**
     * Devuelve el estado de la celda.
     *
     * @param state Estado de la celda.
     */
    public void setState(int state) {
        this.state = state;
    }
}
