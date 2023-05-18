/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visteisminas;

/**
 * Clase que contiene el método main e inicializa el juego.
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos.
 */
public class VisTeisMinas {

    private VisTeisMinasMenu visTeisMinasMenu;
    private boolean textMode;

    /**
     * Devuelve el objeto del menu del juego.
     *
     * @return Objeto del menu del juego.
     */
    public VisTeisMinasMenu getVisTeisMinasMenu() {
        return visTeisMinasMenu;
    }

    /**
     * Cambia el objeto del menu del juego.
     *
     * @param visTeisMinasMenu Objeto del menu del juego.
     */
    public void setVisTeisMinasMenu(VisTeisMinasMenu visTeisMinasMenu) {
        this.visTeisMinasMenu = visTeisMinasMenu;
    }

    /**
     * Devuelve el modo texto del juego.
     *
     * @return True si modo texto, false si no.
     */
    public boolean isTextMode() {
        return textMode;
    }

    /**
     * Cambia el modo texto del juego.
     *
     * @param textMode True si modo texto, false si no.
     */
    public void setTextMode(boolean textMode) {
        this.textMode = textMode;
    }

    /**
     * Constructor de la clase VisTeisMinas.
     *
     * @param textMode True si modo texto, false si no.
     */
    public VisTeisMinas(boolean textMode) {
        this.textMode = textMode;
        if (this.textMode) {
            visTeisMinasMenu = new VisTeisMinasMenuText();

        } else {
            visTeisMinasMenu = new VisTeisMinasMenuWindow();
        }
        visTeisMinasMenu.startNewGame();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Look and Feel épico
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisTeisMinasMenuWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisTeisMinasMenuWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisTeisMinasMenuWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisTeisMinasMenuWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        VisTeisMinas game;
        if ((args.length == 1) && (args[0].equals("text"))) {
            game = new VisTeisMinas(true);
        } else {
            game = new VisTeisMinas(false);
        }
    }

}
