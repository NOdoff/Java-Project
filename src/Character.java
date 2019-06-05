/*Yelyzaveta Systaliuk
  Period 6
  Game Project
*/
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


class Character extends Rectangle//creates the character and determines its behavior
{
    protected boolean dead;//if the object is dead
    protected static int numLives;//numLives
    private int xValue, yValue;
    protected int[] keys;
    protected File charFile, iconFile, deathFile;
    protected BufferedImage spriteImage, in, in2, iconBufferedImage, deathImage;
    private int width, height;
    protected JOptionPane inputPane;
    static String playerName;
    protected Image image;
    protected ImageIcon icon;
    protected String filePath;





    public Character(int x, int y, String filePath)//sets up initial setting for the object
    {
//
//        createNameIcon();
        this.filePath = filePath;
        xValue = x;
        yValue = y;
        keys = new int[]{KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT};



    }
//    protected void createNameIcon()
//    {
//        iconFile = new File("icon.png");
//        try
//        {
//            iconBufferedImage = ImageIO.read(iconFile);
//        }
//        catch (IOException e)
//        {
//            System.out.println("Cannot read the file iconFile");
//        }
//        iconBufferedImage = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
//        icon = new ImageIcon(iconBufferedImage,"icon for user");
//
//
//    }

    protected void setupKeyBinding(PlayingGame game) //sets up the key bindings to control the frog
    {
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inMap = game.getInputMap(condition);
        ActionMap actMap = game.getActionMap();

        // this uses an array of keys that holds ints for the arrow keys
        for (int key : keys)
        {
            String name = Integer.toString(key);

            // add the key bindings for arrow key and shift-arrow key
            inMap.put(KeyStroke.getKeyStroke(key, 0), name);
            inMap.put(KeyStroke.getKeyStroke(key, InputEvent.SHIFT_DOWN_MASK), name);
            actMap.put(name, new MyKeyAction(game, key));
        }
    }


    protected void createSprite(PlayingGame game)//create the sprite and initially draw it
    {
        charFile = new File(filePath+".png");
        try
        {
            in = ImageIO.read(charFile);
        } catch (IOException e)
        {
            System.out.println("Cannot read the file charfile");
        }
        spriteImage = new BufferedImage(PlayingGame.SPRITE_WIDTH, PlayingGame.SPRITE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = spriteImage.createGraphics();
        g2.drawImage(in, 0,0,game);
        g2.dispose();

    }

    public boolean isDead()//checks if the frog is dead
    {
        return dead;
    }
    public void passStatus(boolean intersected)//changes the frog's status dead or not
    {
        dead = intersected;
        countLives();
    }
    public void countLives()//counts the numLives
    {


        if(dead)
        {
            Character.numLives--;
            xValue = PlayingGame.START_SPRITE_X;
            yValue = PlayingGame.START_SPRITE_Y;
            Menu.startedGame = false;
            PlayingGame.numberLives.setText("Lives: "+Character.numLives);
            //                SetUp.inform.setText("You have lost a life! \nYou have "+Character.numLives+ " lives left...");
            //                SetUp.cl.show(SetUp.cards, SetUp.DEAD);
            SetUp.highestRow =10;




//            System.exit(0)

        }
    }


    public void updateX(int x)//updates x coordinate
    {
        xValue =x;

//        System.out.println("xV -"+xValue);
    }
    public void updateY(int y)//updates y coordinate
    {
        yValue = y;
//        System.out.println("yV -"+yValue);
    }
    public int getxValue()// returns x Coordinate
    {
        return xValue;
    }
    public  int getyValue()//returns y coordinate
    {
        return yValue;
    }



}


