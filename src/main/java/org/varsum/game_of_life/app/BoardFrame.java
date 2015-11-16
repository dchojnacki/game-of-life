package org.varsum.game_of_life.app;

import org.varsum.game_of_life.domain.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author The Watchmaker
 */
public class BoardFrame extends JFrame {
    private final int matrixSize = 75;

    private Image screenImage;
    private Graphics screenGraphics;
    private JButton startButton;
    private JButton resetButton;
    private JPanel panel;

    private boolean play;
    private Board board;


    public BoardFrame() {
        newBoard();
        initFrame();
    }

    private void newBoard() {
        board = new Board(matrixSize);
    }

    private void initFrame() {
        panel = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setBackground(new Color(102, 102, 102));
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                panelMouseDragged(evt);
            }
        });
        panel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                panelResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 293, Short.MAX_VALUE)
        );

        startButton.setText("Play");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startButtonClicked(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButtonClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(startButton)
                                        .addComponent(resetButton))
                                .addContainerGap()));

        Dimension frameDimension = new Dimension();
        frameDimension.setSize(800, 800);
        panel.setMinimumSize(frameDimension);

        pack();

        screenImage = createImage(panel.getWidth(), panel.getHeight());
        screenGraphics = screenImage.getGraphics();

        Timer time = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
                if (play) {
                    board = board.tickle();
                }
                repain();
            }
        };
        time.scheduleAtFixedRate(task, 0, 100);
        repain();
    }

    private void repain(){
        screenGraphics.setColor(panel.getBackground());
        screenGraphics.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        for(int i = 0 ; i < matrixSize; i++){
            for(int j = 0 ; j < matrixSize; j++){
                if(board.isCellActiveAt(i, j)){
                    screenGraphics.setColor(Color.YELLOW);
                    int x = j * panel.getWidth() / matrixSize;
                    int y = i * panel.getHeight() / matrixSize;
                    screenGraphics.fillRect(x, y, panel.getWidth() / matrixSize, panel.getHeight() / matrixSize);
                }
            }
        }

        screenGraphics.setColor(Color.BLACK);
        for(int i = 1; i < matrixSize; i++){
            int y = i * panel.getHeight() / matrixSize;
            screenGraphics.drawLine(0, y, panel.getWidth(), y);
        }

        for(int j = 1; j < matrixSize; j++){
            int x = j * panel.getWidth() / matrixSize;
            screenGraphics.drawLine(x, 0, x, panel.getHeight());
        }

        panel.getGraphics().drawImage(screenImage, 0, 0, panel);
    }

    private void panelResized(ComponentEvent event) {
        screenImage = createImage(panel.getWidth(), panel.getHeight());
        screenGraphics = screenImage.getGraphics();

        repain();
    }

    private void startButtonClicked(ActionEvent event) {
        play = !play;
        if (play) {
            startButton.setText("Pause");
        } else {
            startButton.setText("Play");
        }

        repain();
    }

    private void resetButtonClicked(ActionEvent event) {
        newBoard();
        repain();
    }

    private void panelMouseDragged(MouseEvent event) {
        int i = matrixSize * event.getY() / panel.getHeight();
        int j = matrixSize * event.getX() / panel.getWidth();

        if (SwingUtilities.isLeftMouseButton(event)) {
            board.setActiveCellAt(i, j);
        } else {
            board.setInactiveCellAt(i, j);
        }

        repain();
    }

    public static void main(String args[]) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BoardFrame().setVisible(true);
            }
        });
    }
}
