package TicTacToe;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Single extends JPanel {

    //variABLE TI STORE current user
    private boolean user = false;
    private int gridArray[][];
    private boolean end = false;

    // store the scores of players
    private int p1Scores = 0;
    private int ComScores = 0;

    // final constants
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private final int SIZE_OF_CELL = 95;
    private final int SIZE_OF_GRID = 3;
    private int gridX = (int) ((dim.getWidth() / 2) - (SIZE_OF_CELL * (SIZE_OF_GRID / 2.0)));
    private int gridY = (int) ((dim.getHeight() / 2) - (SIZE_OF_CELL * (SIZE_OF_GRID / 2.0)));
    private final int[] GRID_POSITION = {gridX, gridY};

    //   private Image imgEmptyCell;
    private Graphics2D graphic;
    private Image images[][];
    private Image scoreBoad;
    private Image p1Score;
    private Image p2Score;
    private Image bigScreen;
    private String text;
    
    private Support sup;
    private DB db;

    public void paintComponent(Graphics g) {
        addMouseListener(new Clicked());
        super.paintComponent(g);
        InitDraw(g);
    }

    public Single(Support sup) {

        this.sup = sup;
        this.db = sup.getDb();
        
        gridArray = new int[SIZE_OF_GRID][SIZE_OF_GRID];
        for (int i = 0; i < SIZE_OF_GRID; i++) {
            for (int j = 0; j < SIZE_OF_GRID; j++) {
                gridArray[i][j] = 0;
            }
        }
        images = new Image[SIZE_OF_GRID][SIZE_OF_GRID];
        for (int i = 0; i < SIZE_OF_GRID; i++) {
            for (int j = 0; j < SIZE_OF_GRID; j++) {
                images[i][j] = new ImageIcon("img1.jpg").getImage();
            }
        }
        scoreBoad = new ImageIcon("singleScore.jpg").getImage();
        p1Score = new ImageIcon("0.jpg").getImage();
        p2Score = new ImageIcon("0.jpg").getImage();

    }

     public void close(){
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
               frame.dispose();
       
    }
    
    public void InitDraw(Graphics g) {
        Image background = new ImageIcon("back2.jpg").getImage();
        graphic = (Graphics2D) g;
        graphic.drawImage(background, 0, 0, null);

        int scoreboadPositionY = GRID_POSITION[1] + (SIZE_OF_CELL * SIZE_OF_GRID - 300) / 2;
        int scoreboadPositionX = (GRID_POSITION[0] - 300) / 2;

        int p1ScoreY = scoreboadPositionY + 129;
        int p2ScoreY = scoreboadPositionY + 129;
        int p1ScoreX = scoreboadPositionX;
        int p2ScoreX = scoreboadPositionX + 157;
        graphic.drawImage(scoreBoad, scoreboadPositionX, scoreboadPositionY, this);
        graphic.drawImage(p1Score, p1ScoreX, p1ScoreY, this);
        graphic.drawImage(p2Score, p2ScoreX, p2ScoreY, this);
        graphic.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                  0.7f));
        int limitx = GRID_POSITION[0] + SIZE_OF_CELL * SIZE_OF_GRID;
        int limity = GRID_POSITION[1] + SIZE_OF_CELL * SIZE_OF_GRID;
        for (int i = 0; i < SIZE_OF_GRID; i++) {
            for (int j = 0; j < SIZE_OF_GRID; j++) {
                graphic.drawImage(images[i][j], GRID_POSITION[0] + SIZE_OF_CELL * i, GRID_POSITION[1] + SIZE_OF_CELL * j, this);
            }
        }

    }

    class Clicked extends MouseAdapter implements Runnable {

        private int x;
        private int y;

        
        private void checkForEnd(){
            boolean end = true;
            for(int i=0;i<SIZE_OF_GRID;i++){
            for(int j=0;j<SIZE_OF_GRID;j++){
                if(gridArray[i][j] ==0) 
                {
                    end = false;
                    break;
                }
            }
            }
            
            if(end){
                String result;
               if(p1Scores>ComScores){
                   result = sup.getName1() + " Wins";
               }else if (p1Scores<ComScores){
                   result = sup.getName2() + " Wins";
               }else{
                   result = "Game tie";
               }
               WinnerWindow win = new WinnerWindow();
               win.setText(result);
               win.setSup(sup);
               win.show();
               close();
               //this.
               
                
// JOptionPane.showMessageDialog(null,"End");
            }
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            Thread thread = new Thread(this);
            x = e.getX();
            y = e.getY();

            x = getCodex(x);
            y = getCodey(y);
            //  System.out.println(" x = " + e.getX() + " y= " + e.getY());
            // System.out.println("####");
            thread.run();

        }

         private void updateDatabase(){
            String user;
            
                user = sup.getName1();
          
            ArrayList<String> marksList = db.selectLocal("users", "marks", "name = '" + user + "'" );
            int marks = Integer.parseInt(marksList.get(0));
            marks++;
            String statement = "update users set marks = " + marks + " where name = '" + user +"'";
            db.executeLocal(statement);
            
        }
        
        public void run() {

            //   text = "tharaka";
            //  repaint();
            if (x < SIZE_OF_GRID && y < SIZE_OF_GRID && x >= 0 && y >= 0) {
                if (gridArray[x][y] == 0) {
                    userClick(x, y);
                    //  gridArray[x][y] =1;
                    //   checkForWin(x, y);
                }

            } else {
                System.out.println("invallid click");
            }
            repaint();

        }

        private void checkForWin(int x, int y) {

            int user = 0;
            boolean results[] = new boolean[12];
            int count = -1;
            int key = gridArray[x][y];
            for (int i = 0; i < 12; i++) {
                results[i] = false;
            }
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    count++;
                    try {
                        if ((gridArray[i + x][j + y] == gridArray[x + i + i][y + j + j]) && (gridArray[i + x][j + y] == key)) {
                            results[count] = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }

                }
            }
            try {
                if ((gridArray[x - 1][y - 1] == gridArray[x + 1][y + 1]) && (gridArray[x + 1][y + 1] == key)) {
                    results[9] = true;
                }
                if ((gridArray[x][y - 1] == gridArray[x][y + 1]) && (gridArray[x][y + 1] == key)) {
                    results[10] = true;
                }
                if ((gridArray[x + 1][y - 1] == gridArray[x - 1][y + 1]) && (gridArray[x - 1][y + 1] == key)) {
                    results[11] = true;
                }
                if ((gridArray[x - 1][y] == gridArray[x + 1][y]) && (gridArray[x + 1][y] == key)) {
                    results[12] = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {

            }

            results[4] = false;
            for (int i = 0; i < 12; i++) {
                if (results[i]) {
                    if (key == 2) {
                        p1Scores++;
                        p1Score = new ImageIcon(String.valueOf(p1Scores) + ".jpg").getImage();
                        repaint();
                        updateDatabase();
//System.out.println("1111");
                    } else {
                        ComScores++;
                        p2Score = new ImageIcon(String.valueOf(ComScores) + ".jpg").getImage();
                        repaint();
                    }
                }
                //    System.out.println(key);
            }
            //System.out.println("########################");

        }

        private void userClick(int x, int y) {

            images[x][y] = new ImageIcon("imgx.jpg").getImage();
            gridArray[x][y] = 2;
            repaint();
            checkForWin(x, y);
            checkForEnd();
            end = true;
            for (int i = 0; i < SIZE_OF_GRID; i++) {
                for (int j = 0; j < SIZE_OF_GRID; j++) {
                    if (gridArray[i][j] == 0) {
                        end = false;
                    }
                }
            }
            if (!end) {
                try {
                    sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Single.class.getName()).log(Level.SEVERE, null, ex);
                }
                Random rand = new Random();
                while (true) {
                    int xx = rand.nextInt(3);
                    int yy = rand.nextInt(3);
                    if (gridArray[xx][yy] == 0) {
                        gridArray[xx][yy] = 1;
                        images[xx][yy] = new ImageIcon("imgo.jpg").getImage();
                        repaint();
                        repaint();
                        checkForWin(xx, yy);
                        break;
                    }
                }
            }
        }

        private int getCodex(int x) {

            return (x - GRID_POSITION[0]) / SIZE_OF_CELL;
        }

        private int getCodey(int y) {

            return (y - GRID_POSITION[1]) / SIZE_OF_CELL;
        }

    }

}
