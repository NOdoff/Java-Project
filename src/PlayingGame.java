/*Yelyzaveta Systaliuk
  Period 6
  Game Project
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.text.DecimalFormat;


@SuppressWarnings("serial")
class PlayingGame extends JPanel//creates the game in the JPanel with the character and the player
{
    protected static final int SPRITE_WIDTH = 50;
    protected static final int SPRITE_HEIGHT = 50;
    protected static final int PANEL_WIDTH = 950;
    protected static final int PANEL_HEIGHT = 650;
    protected static final int START_SPRITE_X = (PANEL_WIDTH - SPRITE_WIDTH) / 2;
    protected static final int START_SPRITE_Y = PANEL_HEIGHT-2*SPRITE_WIDTH;
    protected static final int TIMER_PERIOD = 16;
    protected static Timer controlTimer1;
    private int mX = (PANEL_WIDTH - SPRITE_WIDTH) / 2;
    private int mY = PANEL_HEIGHT-2*SPRITE_WIDTH;
    private int oldMX = mX;
    private int oldMY = mY;
    protected JLabel timeCount;
    protected static JLabel numberLives, scoreCount;
    private boolean moved = false;
    private int charSpeed = 50;
    private Obstacle obstacle2, obstacle3, obstacle4, obstacle5, obstacle6, obstacle7, obstacle8, obstacle9;
    private Row row2, row3, row4, row5, row6, row7, row8, row9;

    protected static Character character;

    protected JPanel stats;





    public PlayingGame()//sets up all the rows with obstacles within them and safezone
    {
        // create and start the main animation timer
        controlTimer1 = new Timer(TIMER_PERIOD, new TimerListener());
        controlTimer1.start();
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.GRAY);


        character = new Character(mX, mY, "frog");

        JPanel safeZone = new JPanel();
        safeZone.setBackground(Color.GREEN);
        JLabel finish = new JLabel("FINISH");
        finish.setFont(new Font("Serif", Font.BOLD, 50));
        safeZone.setPreferredSize(new Dimension(PANEL_WIDTH, SPRITE_HEIGHT));
        safeZone.setLocation(0,0);
//        obstacle1 =  new Obstacle(0,0, false,"blue_car");
//        row1 = new Row(1, obstacle1, 100); // the drawing JPanel
        obstacle2 = new Obstacle(0,0,true, "big_truck");
        row2 = new Row(2, obstacle2, 90); // the drawing JPanel
        obstacle3 = new Obstacle(0,0, false,"blue_car");
        row3 = new Row(3, obstacle3, 10); // the drawing JPanel
        obstacle4 = new Obstacle(0,0, true,"big_truck");
        row4 = new Row(4, obstacle4, 30); // the drawing JPanel
        obstacle5 = new Obstacle(0,0, false,"blue_car");
        row5 = new Row(5,obstacle5, 50); // the drawing JPanel
        obstacle6 = new Obstacle(0,0, true,"fire_truck");
        row6 = new Row(6, obstacle6, 20); // the drawing JPanel
        obstacle7 = new Obstacle(0,0, false,"blue_car");
        row7 = new Row(7, obstacle7,20); // the drawing JPanel
        obstacle8 = new Obstacle(0,0, true,"big_truck");
        row8 = new Row(8, obstacle8, 50); // the drawing JPanel
        obstacle9 = new Obstacle(0,0, false,"blue_car");
        row9 = new Row(9, obstacle9, 50); // the drawing JPanel
        stats = new JPanel();
        stats.setBackground(Color.GREEN);
        stats.setLayout(new BorderLayout(250,0));
        numberLives = new JLabel("Lives: 3");
        timeCount = new JLabel("Time: 10.00");
        scoreCount = new JLabel("Score: 0");
        numberLives.setFont(new Font("Serif", Font.BOLD, 30));
        numberLives.setForeground(Color.red);
        timeCount.setFont(new Font("Serif", Font.BOLD, 30));
        timeCount.setForeground(Color.BLUE);
        scoreCount.setFont(new Font("Serif", Font.BOLD, 30));
        scoreCount.setForeground(Color.DARK_GRAY);
        stats.setPreferredSize(new Dimension(PANEL_WIDTH, SPRITE_HEIGHT));
        stats.setLocation(0, PANEL_HEIGHT-350);
        stats.add(numberLives, BorderLayout.WEST);
        stats.add(timeCount, BorderLayout.CENTER);
        stats.add(scoreCount, BorderLayout.EAST);

        add(stats);
        add(safeZone);
        add(row2);
        add(row3);
        add(row4);
        add(row5);
        add(row6);
        add(row7);
        add(row8);
        add(row9);
        add(Box.createVerticalStrut(50));
        character.createSprite(this);
        character.setupKeyBinding(this);




//        add(lp, BorderLayout.CENTER);


    }


    @Override
    protected void paintComponent(Graphics g) //draws the moving character
    {
        super.paintComponent(g);
//
//        for(int i = 0; i<PANEL_HEIGHT;i+=40)
//        {
//            g.setColor(Color.BLACK);    //lines will be black
//            g.drawLine(i, 0, i, PANEL_HEIGHT);   //draws vertical lines
//
//        }
//        for(int i = 0; i<PANEL_WIDTH;i+=40)
//        {
//            g.setColor(Color.BLACK);    //lines will be black
//
//            g.drawLine(0, i, PANEL_WIDTH, i);   //draws horizontal lines
//
//        }

        if(character.isDead())
        {
//            System.out.println("------ char y "+mY);
//            System.out.println("--2 "+row2.getYCoordinate());
//            System.out.println("--3 "+row3.getYCoordinate());
//            System.out.println("--4 "+row4.getYCoordinate());
//            System.out.println("--5 "+row5.getYCoordinate());
//            System.out.println("--6 "+row6.getYCoordinate());
//            System.out.println("--7 "+row7.getYCoordinate());
//            System.out.println("--8 "+row8.getYCoordinate());
//            System.out.println("--9 "+row9.getYCoordinate());




            if(row2.isInside(mY))
            {
                System.out.println();
                row3.resetDeath();
                row4.resetDeath();
                row5.resetDeath();
                row6.resetDeath();
                row7.resetDeath();
                row8.resetDeath();
                row9.resetDeath();
            }


            else if(row3.isInside(mY))
            {
                row2.resetDeath();

                row4.resetDeath();
                row5.resetDeath();
                row6.resetDeath();
                row7.resetDeath();
                row8.resetDeath();
                row9.resetDeath();
            }
            else if(row4.isInside(mY))
            {
                row2.resetDeath();
                row3.resetDeath();

                row5.resetDeath();
                row6.resetDeath();
                row7.resetDeath();
                row8.resetDeath();
                row9.resetDeath();
            }
            else if(row5.isInside(mY))
            {
                row2.resetDeath();
                row3.resetDeath();
                row4.resetDeath();

                row6.resetDeath();
                row7.resetDeath();
                row8.resetDeath();
                row9.resetDeath();
            }
            else if(row6.isInside(mY))
            {
                row2.resetDeath();
                row3.resetDeath();
                row4.resetDeath();
                row5.resetDeath();

                row7.resetDeath();
                row8.resetDeath();
                row9.resetDeath();
            }
            else if(row7.isInside(mY))
            {
                row2.resetDeath();
                row3.resetDeath();
                row4.resetDeath();
                row5.resetDeath();
                row6.resetDeath();

                row8.resetDeath();
                row9.resetDeath();
            }
            else if(row8.isInside(mY))
            {
                row2.resetDeath();
                row3.resetDeath();
                row4.resetDeath();
                row5.resetDeath();
                row6.resetDeath();
                row7.resetDeath();

                row9.resetDeath();
            }
            else if(row9.isInside(mY))
            {
                row2.resetDeath();
                row3.resetDeath();
                row4.resetDeath();
                row5.resetDeath();
                row6.resetDeath();
                row7.resetDeath();
                row8.resetDeath();

            }


            if (Character.numLives == 0)
            {
//                row2.stopTimer();
//                row3.stopTimer();
//                row4.stopTimer();
//                row5.stopTimer();
//                row6.stopTimer();
//                row7.stopTimer();
//                row8.stopTimer();
//                row9.stopTimer();
//                controlTimer1.stop();

                SetUp.cl.show(SetUp.cards, SetUp.LOSER);
//                showDialog();
//                SetUp.cl.show(SetUp.cards, SetUp.LEADERBOARD);
                Menu.startedGame=false;
//                Character.numLives = 3;
//                SetUp.playerScore=0;
                row2.resetDeath();
                row3.resetDeath();
                row4.resetDeath();
                row5.resetDeath();
                row6.resetDeath();
                row7.resetDeath();
                row8.resetDeath();
                row9.resetDeath();








            }
//            character.createDeath(this);
            g.drawImage(character.deathImage, mX, mY, this);
//            System.out.println("x "+mX);
//            System.out.println("y "+mY);
            setToStartPosition();
        }

        g.drawImage(character.spriteImage, mX, mY, this);

        if(mY<=(PlayingGame.SPRITE_HEIGHT+ Row.ROW_GAP)&& mY>= (PlayingGame.SPRITE_HEIGHT+Row.ROW_GAP-PlayingGame.SPRITE_HEIGHT))
        {

            SetUp.cl.show(SetUp.cards, SetUp.WINNER);
        }


        row2.updateCharPosition(mX, mY, character);
        row3.updateCharPosition(mX, mY, character);
        row4.updateCharPosition(mX, mY, character);
        row5.updateCharPosition(mX, mY, character);
        row6.updateCharPosition(mX, mY, character);
        row7.updateCharPosition(mX, mY, character);
        row8.updateCharPosition(mX, mY, character);
        row9.updateCharPosition(mX, mY, character);


    }
    public void incrementX(boolean right) //moves frog vertically
    {
        oldMX = mX;
        if(mX==0 && !right)
        {
            //            mX = 0;
            moved = false;
        }
        else if(mX ==PANEL_WIDTH-SPRITE_WIDTH &&right)
        {
//            mX= PANEL_WIDTH-SPRITE_WIDTH;
//            System.out.println("PANEL_WIDTH --"+PANEL_WIDTH);
//            System.out.println("SPRITE_WIDTH --"+SPRITE_WIDTH);
//
//            System.out.println("mx --"+mX);
            moved = false;
        }
        else if (right)
        {
            mX += charSpeed;
            moved = true;
//            System.out.println("PANEL_WIDTH --"+PANEL_WIDTH);
//            System.out.println("SPRITE_WIDTH --"+SPRITE_WIDTH);
//
//            System.out.println("mx --"+mX);
        }

        else
        {
            mX -=charSpeed;
            moved = true;
//            System.out.println("PANEL_WIDTH --"+PANEL_WIDTH);
//            System.out.println("SPRITE_WIDTH --"+SPRITE_WIDTH);
//
//            System.out.println("mx --"+mX);
        }
        character.updateX(mX);
//        System.out.println("mx --"+mX);
//        System.out.println("oldx --"+oldMX);

    }

    public void incrementY(boolean down) //moves frog horizontally
    {
        oldMY = mY;
        if(mY==0&&!down)
        {
            //            mY = 0;
            moved = false;
        }
        else if(mY ==START_SPRITE_Y&&down)
        {
            //            mY= PANEL_HEIGHT-SPRITE_HEIGHT;
//            System.out.println("mY --"+mY);
            moved = false;
        }
        else if (down)
        {
            mY +=charSpeed+Row.ROW_GAP;
            moved = true;
        }
        else
        {

            mY -= (charSpeed+Row.ROW_GAP);
            moved = true;
        }
        character.updateY(mY);
//        System.out.println("mY --"+mY);
//        System.out.println("oldY --"+oldMY);

    }
    protected void setToStartPosition()
    {
        mX = START_SPRITE_X;
        oldMX = START_SPRITE_X;
        mY = START_SPRITE_Y;
        oldMY = START_SPRITE_Y;
        Menu.startedGame = true;
//            cl.show(cards, SetUp.LOSER);
//            cl.show(cards, SetUp.MENU);
        character.passStatus(false);
    }


    private class TimerListener implements ActionListener//moving smoothness
    {
        double time= 1000;
        //        double timeMin = (double)(time/100);
        @Override
        public void actionPerformed(ActionEvent e) //deleting the old image and drawing the new image
        {

            if(Menu.startedGame)
            {

                if(time == 0.0)//does not move when time is 0
                {
                    PlayingGame.controlTimer1.stop();
                    Menu.startedGame = false;
                    character.passStatus(true);
                    setToStartPosition();
                    time = 1000;
                    PlayingGame.controlTimer1.start();


                }
                else if(time>0.0)
                {
                    timeCount.setText("Time: "+new DecimalFormat("#0.00").format(time/100)+" sec");
                    time--;
                }
//                System.out.println(new DecimalFormat("#0.00").format(time/100)+" sec");


            }
            else
            {
//                character.passStatus(true);
//                setToStartPosition();
//                time = 1000;
//                PlayingGame.controlTimer.start();

            }

            if(Character.numLives !=0)
            {
                int width = SPRITE_WIDTH;
                int height = SPRITE_HEIGHT;

                // erases the old image
                if (moved) {
                    int x = oldMX;
                    int y = oldMY;
                    repaint(x, y, width, height);
                }

                int x = mX;
                int y = mY;

                // draws the new image
                repaint(x, y, width, height);
                moved = false;
            }
            else
            {
//                System.out.println("checj");
            }


        }
    }


    protected void showDialog()//asks for name//try to find another way to do the dialog
    {
        String message = "Please enter your name to save your score!";
        Character.playerName = JOptionPane.showInputDialog(SetUp.frame, message, "Enter Name", JOptionPane.PLAIN_MESSAGE);
        if(Character.playerName != null)
        {
//            System.out.println("User selected Enter");
            SetUp.scoresInput.append(SetUp.rank + "\t\t" +  Character.playerName + "\t\t"+ SetUp.playerScore+"\n");
            SetUp.cl.show(SetUp.cards, SetUp.LEADERBOARD);
            SetUp.rank++;
        }
        else
        {
            SetUp.cl.show(SetUp.cards, SetUp.LOSER);
        }

//

////        JTextField textField = new JTextField();
//        String buttonString1 = "Enter";
//        String buttonString2 = "Cancel";
////        Object[] objects = {message,textField};
//////        Object[] options = {buttonString1, buttonString2};
//////        inputPane = new JOptionPane(
//////                objects,
//////                JOptionPane.PLAIN_MESSAGE,
//////                JOptionPane.OK_CANCEL_OPTION,
//////                icon,
//////                options);
//        Object[] options = {buttonString1};
//
//        UIManager.put("OptionPane.okButtonText", "Enter");
////        UIManager.put("OptionPane.cancelButtonText", "Cancel");
////        playerName = (String)JOptionPane.showInputDialog(
////                null,
////                "Please enter your name to save your score!",
////                "Enter Your name",
////                JOptionPane.PLAIN_MESSAGE,
////                icon, null, null);
////        if(playerName!= null )
////        {
////            System.out.println(playerName);
////        }
//        int style = JOptionPane.PLAIN_MESSAGE;
//        JOptionPane pane = new JOptionPane(
//                "Please enter your name to save your score!",
//                style,
//                JOptionPane.DEFAULT_OPTION,
//                null, options,
//                "Name");
//
//        pane.setWantsInput(true);
//
//        JDialog dialog = pane.createDialog(SetUp.loserPanel, "Enter Your Name");
//
//
//        dialog.setVisible(true);
//
//
//
//
//
//
//
//
////        dialog.setVisible(true);
//        dialog.dispose();
////        Object value = pane.getValue();
////        String vlueTs = (String) value;
//        Object inputValue = pane.getInputValue();
//        Character.playerName = (String) (inputValue);
//
////        if(pane.getValue() == null) {
////            System.out.println("User closed dialog");
////
////            SetUp.cl.show(SetUp.cards, SetUp.MENU);
////        }
//        if(pane.getValue().equals(buttonString1))
//        {
//            System.out.println("User selected Enter");
//            SetUp.scoresInput.append(SetUp.rank + "\t\t" +  Character.playerName + "\t\t"+ SetUp.playerScore+"\n");
//            SetUp.cl.show(SetUp.cards, SetUp.LEADERBOARD);
//        }
////        else if(pane.getValue().equals(buttonString2))
////        {
////            System.out.println("User selected Cancel");
////            SetUp.cl.show(SetUp.cards, SetUp.MENU);
////        }
//        else
//        {
//            System.out.println(pane.getValue());
//        }

    }

}




//@SuppressWarnings("serial")
class MyKeyAction extends AbstractAction // Actions for the key binding
{
    private PlayingGame draw;
    private int key;

    public MyKeyAction(PlayingGame draw, int key) //initializing playing panel and the controls over moving
    {
        this.draw = draw;
        this.key = key;
    }

    @Override
    public void actionPerformed(ActionEvent e) //controls frog movement
    {
        switch (key)
        {
            case KeyEvent.VK_UP:
                draw.incrementY(false);
                break;
            case KeyEvent.VK_DOWN:
                draw.incrementY(true);
                break;
            case KeyEvent.VK_LEFT:
                draw.incrementX(false);
                break;
            case KeyEvent.VK_RIGHT:
                draw.incrementX(true);
                break;

            default:
                break;
        }
    }
}