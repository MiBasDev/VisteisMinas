/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visteisminas;

import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

/**
 * Clase que crea la ventana del juego del Busca Minas.
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos.
 */
public class VisTeisMinasMenuWindow extends javax.swing.JFrame implements VisTeisMinasMenu {
    
    private Game game;
    private JToggleButton[][] cellButtons;
    private int levelRawsAndColumns;
    private int levelMines;

    /**
     * Devuelve la partida actual del juego.
     *
     * @return Partida actual del juego.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Cambia la partida actual del juego.
     *
     * @param game Partida actual del juego.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Devuelve el array de celdas con botones.
     *
     * @return Array de celdas con botones.
     */
    public JToggleButton[][] getCellButtons() {
        return cellButtons;
    }

    /**
     * Cambia el array de celdas con botones.
     *
     * @param cellButtons Array de celdas con botones.
     */
    public void setCellButtons(JToggleButton[][] cellButtons) {
        this.cellButtons = cellButtons;
    }

    /**
     * Devuelve las filas y columnas del panel.
     *
     * @return Filas y columnas del panel.
     */
    public int getLevelRawsAndColumns() {
        return levelRawsAndColumns;
    }

    /**
     * Cambia las filas y columnas del panel.
     *
     * @param levelRawsAndColumns Filas y columnas del panel.
     */
    public void setLevelRawsAndColumns(int levelRawsAndColumns) {
        this.levelRawsAndColumns = levelRawsAndColumns;
    }

    /**
     * Devuelve el número de minas del panel.
     *
     * @return Número de minas del panel.
     */
    public int getLevelMines() {
        return levelMines;
    }

    /**
     * Cambia el número de minas del panel.
     *
     * @param levelMines Número de minas del panel.
     */
    public void setLevelMines(int levelMines) {
        this.levelMines = levelMines;
    }

    /**
     * Creates new form VisTeisMenuWindow
     */
    public VisTeisMinasMenuWindow() {
        initComponents();
        jDialogLevel.setLocationRelativeTo(null);
        this.setLocationRelativeTo(null);
    }

    /**
     * Mostra ao usuario un diálogo para que seleccione o nivel de xogo, crea un
     * novo obxecto Game e rexenera o panel do xogo co unha nova matriz de
     * JToogleButtons. Para que os botóns se mostren nunha rexilla que se adapte
     * ao tamaño da ventá, o máis cómodo é establecer para o panel do xogo un
     * layout de tipo GridLayout co número de filas e columas que ten a matriz
     * de celas do xogo. Recoméndase tamén poñer a cada botón un nome do tipo
     * "cell-i-j", poñendo en i e j as coordenadas da cela; desta maneira cando
     * se faga clic no botón poderemos saber polo nome con que cela está
     * relacionada ese botón. Engadiremos a cada botón listeners para capturar
     * os eventos de premer e facer clic co botón dereito sobre o botón. Por
     * último, engadiremos tamén os botóns na posición i,j do array
     * bidimensional "cellButtons", para logo poder acceder facilmente ao botón
     * que se corresponde cunha cela do xogo.
     */
    @Override
    public void startNewGame() {
        jDialogLevel.setVisible(true);
        game = new Game(levelRawsAndColumns, levelRawsAndColumns, levelMines);
        jPanelWindow.removeAll();
        jPanelWindow.setLayout(new GridLayout(levelRawsAndColumns, levelRawsAndColumns));
        cellButtons = new JToggleButton[levelRawsAndColumns][levelRawsAndColumns];
        for (int i = 0; i < cellButtons.length; i++) {
            for (int j = 0; j < cellButtons.length; j++) {
                cellButtons[i][j] = new JToggleButton();
                cellButtons[i][j].setName("cell-" + i + "-" + j);
                cellButtons[i][j].addActionListener((java.awt.event.ActionEvent evt) -> {
                    cellButtonActionPerformed(evt);
                    updatePanel();
                });
                cellButtons[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        cellButtonMouseClicked(evt);
                    }
                });
                jPanelWindow.add(cellButtons[i][j]);
            }
        }
        this.setVisible(true);
    }

    /**
     * Actualiza no panel do xogo os JToogleButtons das celas que están
     * destapadas e mostra neles unha mina (unha imaxe que se almacena na mesma
     * carpeta que as clases), ou o número de minas adxacentes.
     */
    private void updatePanel() {
        for (int i = 0; i < cellButtons.length; i++) {
            for (int j = 0; j < cellButtons.length; j++) {
                if (cellButtons[i][j].isSelected()) {
                    if (game.getCells()[i][j].isMined()) {
                        cellButtons[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/visteisminas/Imagenes/mine.png")));
                    } else {
                        cellButtons[i][j].setText("" + game.getAdjacentMines(game.getCell(i, j)));
                    }
                }
            }
        }
    }

    /**
     * Finaliza a partida, mostrando unha mensaxe ao usuario e dando a opción de
     * xogar outra partida ou pechar a aplicación.
     *
     * @param message Mensaje a enseñar al usuario para ver si quiere acabar o
     * no la partida.
     */
    private void finishGame(String message) {
        int answer = JOptionPane.showOptionDialog(this, message, "FIN DEL JUEGO", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/visteisminas/Imagenes/eltoni.png")), null, null);
        if (answer == 0) {
            startNewGame();
        } else if (answer == 1) {
            this.dispose();
        }
    }

    /**
     * Abre unha cela. Se a a cela ten unha mina, destapa todas as minas e
     * finaliza a partida (chamando ao método anterior). Se non, se chama ao
     * xogo para abrir a cela (o que pode supoñer abrir celas adxacentes de a
     * cela tivese cero minas adxacentes), actualízase o panel do xogo e se non
     * quedan celas sen minas que destapar se remata a partida (de novo chamando
     * ao método anterior, pero agora indicando ao usuario que gañou a partida).
     *
     * @param cell Celda a abrir.
     */
    private void openCell(Cell cell) {
        if (cell.isMined() && cellButtons[cell.getRaw()][cell.getColumn()].isSelected()) {
            for (int i = 0; i < cellButtons.length; i++) {
                for (int j = 0; j < cellButtons.length; j++) {
                    if (game.getCells()[i][j].isMined()) {
                        cellButtons[i][j].setSelected(true);
                    }
                }
            }
            updatePanel();
            finishGame("!Has perdido! ¿Quieres volver a jugar?");
        } else if (cell.getState() == Cell.COVERED) {
            game.openCell(cell);
            for (int i = 0; i < cellButtons.length; i++) {
                for (int j = 0; j < cellButtons.length; j++) {
                    if (game.getCells()[i][j].getState() == Cell.UNCOVERED) {
                        cellButtons[i][j].setSelected(true);
                        cellButtons[i][j].setEnabled(false);
                    }
                }
            }
            updatePanel();
        } else if (cellButtons[cell.getRaw()][cell.getColumn()].getIcon() == null) {
            game.openCell(cell);
            for (int i = 0; i < cellButtons.length; i++) {
                for (int j = 0; j < cellButtons.length; j++) {
                    if (game.getCells()[i][j].getState() == Cell.UNCOVERED) {
                        cellButtons[i][j].setSelected(true);
                        cellButtons[i][j].setEnabled(false);
                    }
                }
            }
            updatePanel();
        }
        if (game.checkCellsToOpen()) {
            for (int i = 0; i < cellButtons.length; i++) {
                for (int j = 0; j < cellButtons.length; j++) {
                    if (game.getCells()[i][j].isMined()) {
                        cellButtons[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/visteisminas/Imagenes/flag.png")));
                    }
                }
            }
            finishGame("¡ENHORABUENA, HAS GANADO! ¿Quieres seguir jugando?");
        }
    }

    /**
     * Método que captura o clic sobre un JToogleButton do panel do xogo,
     * abrindo a cela que se corresponde con este botón. Para saber con que cela
     * se corresponde o botón que causou o evento obteremos o "source" do evento
     * (convertíndoo a un JToogleButton), e no atributo "name" poderemos atopar
     * as coordenadas i e j da cela.
     *
     * @param evt Evento de click de un botón.
     */
    private void cellButtonActionPerformed(java.awt.event.ActionEvent evt) {
        for (int i = 0; i < cellButtons.length; i++) {
            for (int j = 0; j < cellButtons.length; j++) {
                if (evt.getSource() == cellButtons[i][j]) {
                    cellButtons[i][j].setIcon(null);
                    openCell(game.getCell(i, j));
                }
            }
        }
    }

    /**
     * Método que captura o clic co botón dereito (o botón do evento non debe
     * ser o botón 1) sobre un JToogleButton do panel do xogo, marcando ou
     * desmarcando o botón (o que supón poñer ou quitar no botón a icona de
     * aviso), e modificando o estado da cela correspondente (obtendo a cela
     * como no método anterior).
     *
     * @param evt Evento de click del botón derecho.
     */
    private void cellButtonMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getButton() != 1) {
            for (int i = 0; i < cellButtons.length; i++) {
                for (int j = 0; j < cellButtons.length; j++) {
                    if (evt.getSource() == cellButtons[i][j]) {
                        if (game.getCell(i, j).getState() == Cell.COVERED && !cellButtons[i][j].isSelected()) {
                            game.getCell(i, j).setState(Cell.MARKED);
                            cellButtons[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/visteisminas/Imagenes/flag.png")));
                            openCell(game.getCell(i, j));
                        } else if (game.getCell(i, j).getState() == Cell.MARKED && !cellButtons[i][j].isSelected()) {
                            game.getCell(i, j).setState(Cell.COVERED);
                            cellButtons[i][j].setIcon(null);
                        }
                    }
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogLevel = new javax.swing.JDialog();
        jLblSelect = new javax.swing.JLabel();
        jBtnMedio = new javax.swing.JButton();
        jBtnBajo = new javax.swing.JButton();
        jBtnAlto = new javax.swing.JButton();
        jPanelWindow = new javax.swing.JPanel();

        jDialogLevel.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialogLevel.setTitle("Elección de nivel del juego");
        jDialogLevel.setMinimumSize(new java.awt.Dimension(400, 300));
        jDialogLevel.setModal(true);
        jDialogLevel.setPreferredSize(new java.awt.Dimension(400, 300));

        jLblSelect.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLblSelect.setText("SELECCIONE EL NIVEL DE JUEGO");

        jBtnMedio.setText("Medio");
        jBtnMedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnMedioActionPerformed(evt);
            }
        });

        jBtnBajo.setText("Bajo");
        jBtnBajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBajoActionPerformed(evt);
            }
        });

        jBtnAlto.setText("Alto");
        jBtnAlto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAltoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogLevelLayout = new javax.swing.GroupLayout(jDialogLevel.getContentPane());
        jDialogLevel.getContentPane().setLayout(jDialogLevelLayout);
        jDialogLevelLayout.setHorizontalGroup(
            jDialogLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogLevelLayout.createSequentialGroup()
                .addGroup(jDialogLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogLevelLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jBtnBajo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnMedio, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnAlto, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialogLevelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLblSelect)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jDialogLevelLayout.setVerticalGroup(
            jDialogLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogLevelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLblSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialogLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnMedio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnBajo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnAlto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(145, Short.MAX_VALUE))
        );

        jDialogLevel.getAccessibleContext().setAccessibleParent(jPanelWindow);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VisTeis Minas");
        setMinimumSize(new java.awt.Dimension(800, 600));

        jPanelWindow.setMinimumSize(new java.awt.Dimension(700, 700));
        jPanelWindow.setPreferredSize(new java.awt.Dimension(700, 700));

        javax.swing.GroupLayout jPanelWindowLayout = new javax.swing.GroupLayout(jPanelWindow);
        jPanelWindow.setLayout(jPanelWindowLayout);
        jPanelWindowLayout.setHorizontalGroup(
            jPanelWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanelWindowLayout.setVerticalGroup(
            jPanelWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnBajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBajoActionPerformed
        jDialogLevel.setVisible(false);
        levelRawsAndColumns = 6;
        levelMines = 10;
    }//GEN-LAST:event_jBtnBajoActionPerformed

    private void jBtnMedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnMedioActionPerformed
        jDialogLevel.setVisible(false);
        levelRawsAndColumns = 8;
        levelMines = 20;
    }//GEN-LAST:event_jBtnMedioActionPerformed

    private void jBtnAltoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAltoActionPerformed
        jDialogLevel.setVisible(false);
        levelRawsAndColumns = 10;
        levelMines = 40;
    }//GEN-LAST:event_jBtnAltoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisTeisMinasMenuWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAlto;
    private javax.swing.JButton jBtnBajo;
    private javax.swing.JButton jBtnMedio;
    private javax.swing.JDialog jDialogLevel;
    private javax.swing.JLabel jLblSelect;
    private javax.swing.JPanel jPanelWindow;
    // End of variables declaration//GEN-END:variables
}
