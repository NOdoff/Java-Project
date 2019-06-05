/*Yelyzaveta Systaliuk
  Period 6
  Game Project
*/

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


class Death// create death image
{

    private int xCoor, yCoor, width, height;
    protected File deathFile;
    protected BufferedImage deathImage;

    public Death(int x, int y) //gets values from the other class and sets the initial values
    {
//        super(x,y,w,h);
        xCoor = x;
        yCoor = y;
        loadDeath();

    }
    protected void loadDeath()
    {
        deathFile = new File("death_sign.png");
        try
        {
            deathImage = ImageIO.read(deathFile);
        } catch (IOException e)
        {
            System.out.println("Cannot read the file deathfile");
        }
//        deathImage = new BufferedImage(PlayingGame.SPRITE_WIDTH, PlayingGame.SPRITE_HEIGHT, BufferedImage.TYPE_INT_ARGB);

    }
    protected void createDeath(Row r)
    {
        Graphics2D g3 = deathImage.createGraphics();
        g3.drawImage(deathImage, xCoor, yCoor,r);
        g3.dispose();
    }




    public int getxCoor()// gets x coondinate of the obstacle
    {

        return xCoor;
    }
    public int getyCoor()//gets y coordinate of the obstacle
    {

        return yCoor;
    }
    public int getHeight()
    {
        return height;
    }
    public int getWidth()
    {
        return width;
    }

}
