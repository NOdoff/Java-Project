/*Yelyzaveta Systaliuk
  Period 6
  Game Project
*/
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class SetUp// sets up the game and its functions
{

    protected static Menu menu;



    protected static JPanel cards, loserPanel, winnerPanel;
    protected static CardLayout cl;
    protected static Color bloody = new Color(168, 17, 17);
    protected final static String PLAY = "Card with the game";
    protected final static String HOW_TO = "Card with Instructions";
    protected final static String LEADERBOARD = "Card with Shows Scores";
    protected final static String MENU = "Card with Menu";
    protected final static String LOSER = "Card with Loser";
    protected final static String WINNER = "Card with Winner";
    protected final static String DEAD = "Card with DEAD PANEL";
    protected final static String NEXT_LEVEL = "Card with Next button";
    protected final static String SAVE = "Card with Saved Name";
    protected static PlayingGame playingGame;
    protected int lives;
    //    protected Level2 l2;
    protected static JTextArea inform;
    protected static int playerScore;
    protected static int highestRow;
    protected Font font;
    protected static JTextArea scoresInput;
    protected static int rank;
    JLayeredPane lp;
    static JFrame frame;
    JTextField playerNameField;




    private void create()//creates the frame and adds every element to it as well as cardLayout
    {
        int qOne = (int)(Math.random()*5+8);
        int qTwo = 100/3+51%10;
        SetUp.highestRow =10;
        SetUp.rank = 1;
        Character.numLives = 3;
        font = new Font("Serif", Font.BOLD, 100);
        frame = new JFrame("Game");


        frame.setSize(PlayingGame.PANEL_WIDTH, PlayingGame.PANEL_HEIGHT);
        frame.setMinimumSize(new Dimension(PlayingGame.PANEL_WIDTH, PlayingGame.PANEL_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cards = new JPanel(new CardLayout());
        cards.setOpaque(false);
        cl = (CardLayout)(cards.getLayout());
        menu = new Menu();

        JPanel instr = new JPanel();
        instr.setLayout(new BoxLayout(instr,BoxLayout.Y_AXIS));
        instr.setBackground(new Color(57, 200, 100));
        JLabel title = new JLabel("How To Play", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 80));
        title.setForeground(new Color(90, 34, 200));
        JTextArea text = new JTextArea();
        text.setFont(new Font("Serif", Font.PLAIN, 40));
        text.setForeground(new Color(90, 100, 250));
        text.setOpaque(false);
//        text.append("Use arrows to move the frog and avoid running into the obstacles to get to the end.");
        text.append("The game's objective is to move a frog from the bottom to \n");
        text.append("top and overcome the obstacles that come in your way.\n");
        text.append("The game is features a road full of traffic.\n");
        text.append(" When you are moving your frog through the traffic, you have to face \n");
        text.append("speeding cars, buses and many other obstacles on your \n");
        text.append("way which must be avoided to safely transport your frog to safe zone.\n");
        text.append("In the second half, your frog will become a turtle and you will dodge logs as obstacles  \n");
        text.append(" to safely cross the river.");
        text.append("The game has a time limit of 10 seconds  on the timer and must be finished within this time period.\n");
        text.append("The player will earn points by progressing throughout the game and at the end can choose \n");
        text.append("to add his name to the scoreboard.");
//        text.append();
        JButton backToMenuI = new JButton("Back");
        ButtonListener buttonListener1 = new ButtonListener();
        backToMenuI.setActionCommand("from i");
        backToMenuI.addActionListener(buttonListener1);
        instr.add(title);
        instr.add(text);
        instr.add(backToMenuI);

        JPanel scores = new JPanel();

        scores.setLayout(new BoxLayout(scores, BoxLayout.PAGE_AXIS));
        scores.setBackground(new Color(54, 80, 80));
        JButton backToMenuS = new JButton("Back");
        JLabel nameScore = new JLabel("LEADERBOARD");
        nameScore.setBounds(0,0, 950, 100);
        nameScore.setFont(font);
        nameScore.setForeground(Color.WHITE);

//        JTextArea headers = new JTextArea("Rank\t\tName\t\tScore");
//        headers.setEditable(false);
//        headers.setOpaque(false);
//        headers.setMaximumSize(new Dimension(900, 100));
//        headers.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));

        scoresInput = new JTextArea();
        scoresInput.setRows(0);

        scoresInput.setText("Rank\t\tName\t\tScore");
        repeatGetScores();
        scoresInput.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        scoresInput.setForeground(Color.WHITE);
        scoresInput.setOpaque(false);
        scoresInput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(scoresInput);
        scrollPane.getViewport().setBackground(new Color(54, 80, 80));


//        scoresInput.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        backToMenuS.setActionCommand("from s");
        ButtonListener buttonListener2 = new ButtonListener();
        backToMenuS.addActionListener(buttonListener2);
        scores.add(nameScore);
//        scores.add(headers);
        scores.add(scrollPane);
        scores.add(backToMenuS);

        loserPanel = new JPanel();
        loserPanel.setBackground(Color.BLACK);
        loserPanel.setLayout(null);

        JLabel loser = new JLabel("");
        loser.setForeground(bloody);
        loser.setText("YOU LOST!");
        loser.setFont(font);
        loser.setAlignmentX(Component.CENTER_ALIGNMENT);
        loser.setBounds(0,0,950,100);
        JLabel fieldTitle = new JLabel("Enter Name:");
        fieldTitle.setForeground(bloody);
        fieldTitle.setFont(new Font("Serif", Font.BOLD, 50));
        fieldTitle.setBounds(300,200, 300, 50);
        playerNameField = new JTextField(7);
        playerNameField.setDocument(new JTextFieldLimit(7));
        playerNameField.setBackground(new Color(184, 173, 162));
        playerNameField.setBounds(200,300, 400, 50);

        loserPanel.requestFocus();

        JButton ok = new JButton("OK");
        ok.setForeground(bloody);
        ok.setBounds(300,400, 300, 50);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.equals("OK"))
                {

                    Character.playerName = playerNameField.getText();
                    repeatSaveScores();
                    SetUp.scoresInput.setText("");
                    scoresInput.append("Rank\t\tName\t\tScore");
                    repeatGetScores();
//                    SetUp.scoresInput.append(SetUp.rank + "\t\t" +  Character.playerName + "\t\t"+ SetUp.playerScore+"\n");
                    SetUp.cl.show(SetUp.cards, SetUp.LEADERBOARD);
                    playerNameField.setText("");
                }
            }
        });



//        JButton backToMenuL = new JButton("Back");
//        backToMenuL.setActionCommand("from l");
//        ButtonListener buttonListenerL = new ButtonListener();
//        backToMenuL.addActionListener(buttonListenerL);
//        backToMenuL.setForeground(bloody);
////        backToMenuI.setPreferredSize(new Dimension(950, 100));
//        backToMenuL.setBounds(425,500, 100, 50);
//        backToMenuL.setAlignmentX(Component.CENTER_ALIGNMENT);
        loserPanel.add(fieldTitle);
        loserPanel.add(playerNameField);
        loserPanel.add(ok);
        loserPanel.add(loser);

//        loserPanel.add(nameField);
//        loserPanel.add(backToMenuL);

        JLabel winfieldTitle = new JLabel("Enter Name:");
        winfieldTitle.setForeground(Color.white);
        winfieldTitle.setFont(new Font("Serif", Font.BOLD, 50));
        winfieldTitle.setBounds(300,200, 300, 50);

        JTextField  winplayerNameField = new JTextField(7);
        winplayerNameField.setDocument(new JTextFieldLimit(7));
        winplayerNameField.setBackground(new Color(184, 173, 162));
        winplayerNameField.setBounds(200,300, 400, 50);


        JButton winok = new JButton("OK");
        winok.setForeground(bloody);
        winok.setBounds(300,400, 300, 50);
        winok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.equals("OK"))
                {

                    Character.playerName = winplayerNameField.getText();
                    repeatSaveScores();
                    SetUp.scoresInput.setText("");
                    scoresInput.append("Rank\t\tName\t\tScore");
                    repeatGetScores();
                    SetUp.cl.show(SetUp.cards, SetUp.LEADERBOARD);
                    winplayerNameField.setText("");
                }
            }
        });

        winnerPanel = new JPanel();
        JLabel winner = new JLabel("", JLabel.CENTER);
        winnerPanel.setLayout(new BoxLayout(winnerPanel, BoxLayout.PAGE_AXIS));
        winnerPanel.setBackground(Color.CYAN);
        winner.setForeground(Color.GREEN);
        winner.setText("YOU WIN!");
        winner.setFont(font);
        winner.setAlignmentX(Component.CENTER_ALIGNMENT);
        ButtonListener buttonListenerW = new ButtonListener();
//        nextLevel.setPreferredSize(new Dimension(950, 100));
        winnerPanel.add(Box.createVerticalStrut(200));
        winnerPanel.add(winner);
        winnerPanel.add(fieldTitle);
        winnerPanel.add(winplayerNameField);
        winnerPanel.add(winok);
        winnerPanel.add(Box.createVerticalStrut(200));

        JPanel interPanel = new JPanel();
        inform = new JTextArea();
        JButton continueGame = new JButton("Continue to Game");
        interPanel.setLayout(new BoxLayout(interPanel, BoxLayout.PAGE_AXIS));
        inform.setOpaque(false);
        interPanel.setBackground(new Color(100, 0, 100));
        inform.setForeground(new Color(200, 39, 29));
        lives = Character.numLives;
//        System.out.println(lives);
        inform.setText("You have lost a life! \nYou have "+lives+ " lives left...");
        inform.setFont(font);
//        inform.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueGame.setActionCommand("cont");
        ButtonListener buttonListenerC = new ButtonListener();
        continueGame.addActionListener(buttonListenerC);
//        continueGame.setPreferredSize(new Dimension(950, 100));
        continueGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueGame.add(Box.createVerticalStrut(200));
        interPanel.add(inform);
        continueGame.add(Box.createVerticalStrut(100));
        interPanel.add(continueGame);



        cards.add(menu, MENU);

        cards.add(instr, HOW_TO);
        cards.add(scores, LEADERBOARD);
        cards.add(loserPanel, LOSER);
        cards.add(winnerPanel, WINNER);


        playingGame = new PlayingGame(); // the drawing JPanel

//        lp = new JLayeredPane();
//        lp.add(playingGame, JLayeredPane.DEFAULT_LAYER, 1);
//        lp.add(saveName, JLayeredPane.DEFAULT_LAYER, 0);




        cards.add(playingGame, PLAY);
        cards.add(interPanel, DEAD);

//        l2 = new Level2();
//        cards.add(l2, NEXT_LEVEL);

        frame.add(cards);
        frame.setResizable(false);
        frame.setVisible(true);


    }
    protected void repeatSaveScores()
    {
        PrintWriter edit = saveScores();
        String format = Character.playerName + "\t\t"+ SetUp.playerScore;
        SetUp.rank++;
        edit.println(format);

        edit.close();

    }
    protected void repeatGetScores()
    {
        Scanner read = getScores();

        int countLines = 0;
        while(read.hasNextLine())
        {
            read.nextLine();
            countLines++;

        }
        String[] lines = new String[countLines];
        String[] names = new String[countLines];
        int[] rankArray = new int[countLines];
        for(int j = 0; j<countLines; j++){
            rankArray[j] = j+1;
        }
        int[] scores = new int[countLines];
        read = getScores();
        for(int i =0; i<countLines; i++)
        {
            lines[i] = read.nextLine();
            String[] splitLines = lines[i].split("\\s+");
            names[i] = splitLines[0];
            scores[i] = Integer.valueOf(splitLines[1]);

        }
        sortArrays(names,scores);
        for(int i =0; i<countLines; i++)
        {
//            System.out.println(names[i]+"\t\t"+scores[i]);
            scoresInput.append("\n" +rankArray[i] +"\t\t"+names[i]+"\t\t"+scores[i]);
        }
        read.close();
    }
    protected void sortArrays(String[] names, int[] nums)
    {
        int[] indexArray = new int[nums.length];

        for(int j = 0; j<nums.length; j++){
            indexArray[j] = j;
        }

        int temp;
        int index;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] > nums[j - 1]) {
                    temp = nums[j];
                    nums[j] = nums[j - 1];
                    index = indexArray[j];
                    indexArray[j] = indexArray[j-1];
                    nums[j - 1] = temp;
                    indexArray[j-1] = index;

                }

            }
        }
        for(int j = 0; j<nums.length; j++){
            names[j] = names[indexArray[j]];
        }

//        for(int j = 0; j<nums.length; j++){
//            mergedArray[0][j] = names[j];
//            System.out.println(names[j]);
//            mergedArray[1][j] = Integer.toString(nums[j]);
//            System.out.println(nums[j]);
//        }


    }





    protected Scanner getScores()
    {
        File inFile = new File("saved_scores.txt");
        String fileName = "saved_scores.txt";
//        String line = "";
        Scanner input =null;
        try {
            input = new Scanner(inFile);
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Cannot find " +fileName+ " file!");
        }
//        while (input.hasNextLine())
//        {
//            line = input.nextLine();
//            System.out.println(line);
//        }
        return input;

    }
    protected PrintWriter saveScores()
    {
        File outFile = new File("saved_scores.txt");
        String fileName = "saved_scores.txt";//not necessary just a string name
        PrintWriter printWriter = null;//instantiating printwriter
        try
        {
            FileWriter file = new FileWriter(outFile, true);//create a file writer to append
            printWriter = new PrintWriter(file);// create printWriter
        }
        catch (IOException e)
        {
            System.err.println("Cannot create "+fileName+" file. Try again.");//if cannot create
        }
        return printWriter;

    }


    public class JTextFieldLimit extends PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }


    class ButtonListener implements ActionListener//controls back buttons
    {
        public void actionPerformed(ActionEvent e)//reacts when buttons are pressed
        {

            String command = e.getActionCommand();
            if (command.equals("from i") || command.equals("from s"))
            {
                cl.show(cards, MENU);
            }
            else if(command.equals("from l"))
            {

                cl.show(cards, MENU);
            }
            else if(command.equals("cont"))
            {
                cl.show(cards, PLAY);
                PlayingGame.controlTimer1.restart();
            }
            else if(command.equals("next"))
            {
//                cl.show(cards, NEXT_LEVEL);
//                Level2.controlTimer2.restart();
            }
        }

    }
    public static void main(String[] args)//runs the method in setup (starts the whole game)
    {
        SetUp su = new SetUp();
        su.create();
    }
}



