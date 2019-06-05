/*Yelyzaveta Systaliuk
  Period 6
  Game Project
*/

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


class Obstacle//modifiable obstacles class ( a template)
{

    private int xCoor, yCoor, width, height;
    private String fileName;
    protected File obstFile;
    protected BufferedImage inside, obstacleImage;
    protected boolean movingLeft;

    public Obstacle(int x, int y, boolean movingLeft, String fName ) //gets values from the other class and sets the initial values
    {
//        super(x,y,w,h);
        this.movingLeft = movingLeft;
        if(movingLeft)
        {
            fileName = fName+"_left.png";
        }
        else
        {
            fileName = fName+"_right.png";
        }
        loadImage();
//        obstacleImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        width = obstacleImage.getWidth();
        height = obstacleImage.getHeight();

        xCoor  = x;
//        System.out.println(xCoor);
        yCoor = y;
//        System.out.println(yCoor);

    }
    protected void loadImage()
    {
        obstFile = new File(fileName);
        try
        {
            obstacleImage = ImageIO.read(obstFile);
        } catch (IOException e)
        {
            System.out.println("Cannot read the file "+fileName);
        }

    }

    protected void createObstacle(Row r)//create the sprite and initially draw it
    {

        Graphics2D g2 = obstacleImage.createGraphics();
        g2.drawImage(obstacleImage, xCoor,yCoor,r);
        g2.dispose();

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

