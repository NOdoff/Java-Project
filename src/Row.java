/*Yelyzaveta Systaliuk
  Period 6
  Game Project
*/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

class Row extends JPanel//modifiable obstacles class ( a template)
{

    private Obstacle ob;
    private int obstX, obstY, rowNumber, charX, charY;
    private boolean checkIfMovingLeft;
    private int width, height, speed, numOfObst, obstGap;
    private Color color;
    private boolean intersected;
    private Character sprite;
    protected static int ROW_GAP = 5;
    protected String obstacleName;
    protected java.util.Timer t;
    protected Death deathInst;
    int deaths = 0;

    int x;
    int oldX = -1;
    int y;
    int oldY = -1;

    public Row(int numRow, Obstacle obstacle, int s) //gets values from the other class and sets the initial values
    {
        charX = 0;
        charY = 0;
        intersected = false;
        setOpaque(false);
        setBorder((BorderFactory.createLineBorder(Color.BLACK)));
        ob = obstacle;
//        Obstacle o = new Obstacle(0,0,false, "");

        speed = s;
//        this.obstacleName = fileName;
        obstX = ob.getxCoor();
        obstY = ob.getyCoor();
        width = ob.getWidth();
        height = ob.getHeight();
        rowNumber = numRow;
        obstGap = 90;


        numOfObst = PlayingGame.PANEL_WIDTH / (width + obstGap);

        setBackground(Color.red);
        setPreferredSize(new Dimension(PlayingGame.PANEL_WIDTH, PlayingGame.SPRITE_HEIGHT));
//        System.out.println("height  "+PlayingGame.SPRITE_HEIGHT);
        setLocation(0, (rowNumber - 1) * 50);
//        System.out.println((rowNumber - 1) * 50);
        ob.createObstacle(this);
        t = new java.util.Timer();
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                movingObstacle();

            }
        }, 0, speed);
        checkIfMovingLeft = ob.movingLeft;


    }

    protected int getYCoordinate()
    {
        int y = (rowNumber - 1) * 50;
        return y;
    }

    protected void paintComponent(Graphics g)//paints the obstacle
    {
        super.paintComponent(g);
//        g.setColor(color);

//        g.fillRect(obstX, obstY, width, height);

        g.drawImage(ob.obstacleImage, obstX, obstY, width, height, null);

        if (deathInst != null) {

            intersected = false;
//            System.out.println("cadfv");
            g.drawImage(deathInst.deathImage, x, y, null);
//            System.out.println("x"+deathInst.getxCoor());
//            System.out.println("y"+deathInst.getyCoor());

        }

//        for (int i = 0; i < numOfObst; i++) {
//            g.fillRect(obstX + width * i +obstGap, obstY, width, height);
//        }

    }

    void resetDeath(){
        deathInst = null;
    }
    void stopTimer()
    {
        t.cancel();
    }


    public void updateCharPosition(int x, int y, Character character) //check if the frog intersects with the obstacles
    {

        int upperLimitX = obstX + width;
        int lowerLimitX = obstX;
        int xR = x + PlayingGame.SPRITE_WIDTH;
        if (isInside(y)) {

            if(SetUp.highestRow>rowNumber)
            {
                SetUp.highestRow = rowNumber;
                SetUp.playerScore+= 50;
                PlayingGame.scoreCount.setText("Score: " + SetUp.playerScore);

            }
//            System.out.println("y equal " + rowNumber*50);
            if ((x <= upperLimitX && x >= lowerLimitX) || (xR <= upperLimitX && xR >= lowerLimitX)) {
                this.x = x;
                intersected = true;
//                System.out.println("intercepted");
                character.passStatus(true);
                deathInst = new Death(x, 0);
                deathInst.createDeath(this);
                if (oldX != -1 && oldY != -1) {
//                        System.out.println("lololfv");
                    repaint(0, oldY, 50, 50);
                }
                oldX = x;
                oldY = y;
                x = deathInst.getxCoor();
                y = deathInst.getyCoor();

                repaint(x, y, 50, 50);

            }





        }

//        System.out.println("x "+x);
//        System.out.println("y "+(y));
//        System.out.println("up "+rowNumber*(PlayingGame.SPRITE_HEIGHT+ ROW_GAP));
//        System.out.println("low "+(rowNumber*(PlayingGame.SPRITE_HEIGHT+ROW_GAP)-PlayingGame.SPRITE_HEIGHT));
//        System.out.println("up "+obstX+width);
//        System.out.println("low "+obstX);
    }
    protected boolean isInside(int y)
    {

        int upperLimitY = rowNumber * (PlayingGame.SPRITE_HEIGHT + ROW_GAP);
        int lowerLimitY = rowNumber * (PlayingGame.SPRITE_HEIGHT + ROW_GAP) - PlayingGame.SPRITE_HEIGHT;

        if (y <= upperLimitY && y >= lowerLimitY) {

            return true;




        }
        else{
            return false;
        }

    }


    private void movingObstacle()  //moves the obstacle
    {

        if (checkIfMovingLeft) {
            obstX -= 10;
            if (obstX < 0) {
                obstX = PlayingGame.PANEL_WIDTH;
            }
            repaint();

        } else {
            obstX += 10;
            if (obstX > PlayingGame.PANEL_WIDTH) {
                obstX = 0;
            }

            repaint();
        }


    }


}
