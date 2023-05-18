/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visteisminas;

import java.util.Scanner;

/**
 * Clase que implementa la interfaz del juego del buscaminas en modo texto (por
 * ahora).
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos.
 */
public class VisTeisMinasMenuText implements VisTeisMinasMenu {

    /**
     * Referencia al número máximo de filas del tablero del juego.
     */
    public static final int RAWS = 6;

    /**
     * Referencia al número máximo de columnas del tablero del juego.
     */
    public static final int COLUMNS = 6;

    /**
     * Referencia al número máximo de minas del juego.
     */
    public static final int MINES = 8;

    /**
     * Método que mostra o panel de minas.
     *
     * @param game Juego actual.
     */
    public void showPanel(Game game) {

        System.out.println("--- Estás jugando al VisTeis Minas ---");
        System.out.println("---           PANEL STATE          ---");
        System.out.println("");
        for (int i = 0; i < game.getCells().length; i++) {
            if (i == 0) {
                System.out.print("  ");
                for (int k = 0; k < game.getCells().length; k++) {
                    System.out.print(" " + k);
                }
                System.out.println("");
                System.out.print("  -");
                for (int k = 0; k < game.getCells().length; k++) {
                    System.out.print("--");
                }
                System.out.println("");
            }
            System.out.print(i + " ");
            for (int j = 0; j < game.getCells().length; j++) {
                switch (game.getCells()[i][j].getState()) {
                    case Cell.UNCOVERED:
                        System.out.print("|" + game.getAdjacentMines(game.getCells()[i][j]));
                        break;
                    case Cell.MARKED:
                        System.out.print("|" + "!");
                        break;
                    default:
                        System.out.print("|" + " ");
                        break;
                }
            }
            System.out.print("|");
            System.out.println();
            System.out.print("  -");
            for (int k = 0; k < game.getCells().length; k++) {
                System.out.print("--");
            }
            if (i < game.getCells().length - 1) {
                System.out.println();
            }
        }
    }

    /**
     * Método que inicia unha partida, creando un obxecto da clase Game,
     * mostrará o panel (chamando o método anterior) e pedirá ao usuario e
     * executará a acción a realizar. Volverase a mostrar o panel e pedir unha
     * nova acción de forma repetitiva mentres non remate a partida, ben porque
     * o usuario destapa unha cela minada ou ben porque destapa todas as celas
     * que non teñen minas. Cando se remate a partida, este método preguntará ao
     * xogador se quere xogar outra partida, repetindo todo o proceso se
     * responde afirmativamente.
     */
    public void startNewGame() {
        Scanner scan = new Scanner(System.in);
        Game game = new Game(RAWS, COLUMNS, MINES);
        boolean gameOver = false;
        do {
            showPanel(game);
            System.out.println("");
            System.out.println("¿Qué acción quieres llevar a cabo?");
            System.out.println("(s: Salir // m: Marcar una celda // d: Desmarcar una celda // a: Abrir una celda)");
            switch (scan.nextLine().charAt(0)) {
                case 'a':
                case 'A':
                    System.out.println("¿Qué celda quieres abrir?");
                    System.out.println("Línea: ");
                    int rawToOpen = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Columna: ");
                    int columnToOpen = scan.nextInt();
                    scan.nextLine();
                    Cell cellToOpen = game.getCell(rawToOpen, columnToOpen);
                    if (cellToOpen.isMined()) {
                        gameOver = true;
                    } else {
                        game.openCell(cellToOpen);
                    }
                    break;
                case 's':
                case 'S':
                    gameOver = true;
                    break;
                case 'm':
                case 'M':
                    System.out.println("¿Qué celda quieres marcar?");
                    System.out.println("Línea: ");
                    int rawToMark = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Columna: ");
                    int columnToMark = scan.nextInt();
                    scan.nextLine();
                    Cell cellToMark = game.getCell(rawToMark, columnToMark);
                    cellToMark.setState(Cell.MARKED);
                    break;
                case 'd':
                case 'D':
                    System.out.println("¿Qué celda quieres desmarcar?");
                    System.out.println("Línea: ");
                    int rawToDesmark = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Columna: ");
                    int columnToDesmark = scan.nextInt();
                    scan.nextLine();
                    Cell cellToDesmark = game.getCell(rawToDesmark, columnToDesmark);
                    cellToDesmark.setState(Cell.COVERED);
                    break;
                default:
                    System.out.println("");
                    System.out.println("Opción incorrecta, escoja otra opción.");
                    System.out.println("");
            }
            if (gameOver) {
                System.out.println();
                System.out.println("¡GAME OVER!");
                System.out.println("");
                System.out.println("¿Quieres seguir jugando? (si/no)");
                if (scan.nextLine().charAt(0) == 's') {
                    startNewGame();
                } else {
                    gameOver = true;
                }
            } else if (game.checkCellsToOpen()) {
                System.out.println("");
                System.out.println("¡ENHORABUENA!");
                System.out.println("");
                System.out.println("¿Quieres seguir jugando? (si/no)");
                if (scan.nextLine().charAt(0) == 's') {
                    startNewGame();
                } else {
                    gameOver = true;
                }
            }
        } while (!game.checkCellsToOpen() && !gameOver);
    }
}
