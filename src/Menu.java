/*Yelyzaveta Systaliuk
  Period 6
  Game Project
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;


class Menu extends JPanel implements ActionListener// the front page with buttons that will appear when the game runs
{
    private JButton start;
    private JButton scoreBoard;
    private JButton quit;
    private JButton help;
    private JPanel gamePage;
    private Color darkRed, mist;
    protected static boolean startedGame, startedNewLevel;

    public Menu()// set up the front page and buttons
    {
        darkRed = new Color(100, 10, 20);
        mist = new Color(36,12,30);

//        setBackground(Color.ORANGE);
        setLayout(null);


//        JPanel = new JPanel(new GridLayout(3,1));
//        setBackground(Color.BLACK);
//        JPanel cell1 = new JPanel();// cell for a picture
//        ImageIcon picture = new ImageIcon("creepy_frog.jpg");
//        Graphics2D graphics2D = this.createGraphics();
//        picture.paintIcon(this, Graphics g,);
//        cell1.setBorder((BorderFactory.createLineBorder(Color.WHITE)));


//        JPanel cell2 = new JPanel();// cell for a name
        Font f = new Font("Serif",Font.BOLD,100);
        JLabel title = new JLabel("",JLabel.CENTER);
        title.setFont(f);
        title.setForeground(Color.GREEN);
        title.setText("Frogger");
        title.setBounds(0,0,950,150);
        add(title);



//        cell2.setBorder((BorderFactory.createLineBorder(Color.BLACK)));
//
//        JPanel cell3 = new JPanel(new GridLayout(1,3));// cell for buttons and blank spaces
//        cell1.setOpaque(false);
//        cell2.setOpaque(false);
////
//        JPanel blank1 = new JPanel();//left blank
//        blank1.setBackground(Color.RED);
//        blank1.setBorder((BorderFactory.createLineBorder(Color.BLACK)));
//        JPanel blank2 = new JPanel();//right blank
//        blank2.setBackground(Color.RED);
//        blank2.setBorder((BorderFactory.createLineBorder(Color.BLACK)));
        JPanel buttons = new JPanel(new GridLayout(4,1, 20, 20));// buttons
        buttons.setOpaque(false);

        //Initializing buttons
        start = new JButton("Start Game");
        start.setActionCommand("sg");
        start.setBackground(mist);
        scoreBoard = new JButton("Scores");
        scoreBoard.setActionCommand("s");
        scoreBoard.setBackground(mist);
        help = new JButton("Help");
        help.setActionCommand("h");
        help.setBackground(mist);
        quit = new JButton("Quit");
        quit.setActionCommand("q");
        help.setBackground(mist);
        start.setFont(new Font("Serif", Font.BOLD, 50));
        scoreBoard.setFont(new Font("Serif", Font.BOLD, 50));
        help.setFont(new Font("Serif", Font.BOLD, 50));
        quit.setFont(new Font("Serif", Font.BOLD, 50));
        start.setOpaque(true);
        help.setOpaque(true);
        quit.setOpaque(true);
        scoreBoard.setOpaque(true);
        start.setBackground(darkRed);
        scoreBoard.setBackground(darkRed);
        help.setBackground(darkRed);
        quit.setBackground(darkRed);




        //Adding actionListener
        start.addActionListener(this);
        scoreBoard.addActionListener(this);
        help.addActionListener(this);
        quit.addActionListener(this);

        //Adding buttons to their panel
        buttons.add(start);
        buttons.add(scoreBoard);
        buttons.add(help);
        buttons.add(quit);
        buttons.setBounds(100,300,750,300);

        //Adding blanks and buttons to cell3
//        cell3.add(blank1);
        add(buttons);
//        cell3.add(blank2);

        //adding all cells to the panel
//        add(cell1);
//        add(cell2);
//        add(cell3);



    }
//    class MyComponent extends JComponent {
//        public void paint(Graphics g) {
//
//            ImageIcon icon = new ImageIcon("creepy_frog.jpg");
//            int x = 0;
//            int y = 0;
//            icon.paintIcon(this, g, x, y);
//        }
//
//    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Image bg = new ImageIcon("creepy_frog.jpg").getImage();
//
        g.drawImage(bg, 0,0, 950, 650, null);

    }

    public void actionPerformed(ActionEvent e)// going to the different page after clicking buttons
    {

        //if pressed start
        if(e.getActionCommand().equals("sg"))
        {
//            start.setForeground(darkRed);



            PlayingGame.controlTimer1.restart();
            Character.numLives = 3;
            SetUp.playerScore =0;
            PlayingGame.numberLives.setText("Lives: "+ Character.numLives);//do the same for scores
            PlayingGame.scoreCount.setText("Score: " + SetUp.playerScore);
            SetUp.cl.show(SetUp.cards, SetUp.PLAY);

            startedGame =true;





        }
        else if(e.getActionCommand().equals("h"))
        {
//            help.setForeground(darkRed);
            SetUp.cl.show(SetUp.cards, SetUp.HOW_TO);




        }
        //scores
        else if(e.getActionCommand().equals("s"))
        {
//            scoreBoard.setForeground(darkRed);


            SetUp.cl.show(SetUp.cards, SetUp.LEADERBOARD);



        }
        //quit
        else if(e.getActionCommand().equals("q"))
        {
            System.exit(0);
        }

    }
}


