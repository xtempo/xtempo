package snakegame;

import javax.swing.*; // for jpanel, we import swing package
import java.awt.*; // this is for coloring the background color of board
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {
    // this all are global varible(glov)
//    private Image animal1;
//    private Image animal2;

    private Image dot;
    private Image apple;
    private Image head;

    private final int ALL_DOTS = 1600; // here total area of board 
    private final int DOT_SIZE = 10; // size of the dot

    // it is our snake coordinate 
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    private final int RANDOM_POSITION = 29; // x axis random positon

    private int apple_x; // it is our apple x coordinate
    private int apple_y; // it is our apple y coordinate

    private int dots;

    private Timer timer;

    // glov for moving the object from left to right
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    // glov for moving the object from up and down
    private boolean upDirection = false;
    private boolean downDirection = false;

    private boolean inGame = true;

    // creating the board for the game 
    Board() {
        addKeyListener(new TAdapter());
//        setSize(400, 400);
        setBackground(Color.BLACK); //for coloring the background
        setFocusable(true); //for focusing the game
        setPreferredSize(new Dimension(400, 400)); // it is like 30 X 30 = 900
        loadImages(); // here we are creating the loadImages() method for loding the image of the game 
        initGame();  // here we are creating the initgame() method for initialising our game

    }

    // her we are loding image for the game 
    public void loadImages() {
        ImageIcon d = new ImageIcon(ClassLoader.getSystemResource("snakegame/icon/dot.png"));
        dot = d.getImage();

//      ImageIcon n1 = new ImageIcon(ClassLoader.getSystemResource("snake_game/icon/animal1.webp"));
//      animal1 = n1.getImage(); // here we are storing the image in animal1 by using getImage() method and we get the image from the icon folder
//
//      ImageIcon n2 = new ImageIcon(ClassLoader.getSystemResource("snake_game/icon/animal2.webp"));
//      animal2 = n2.getImage();
//      ImageIcon n3 = new ImageIcon(ClassLoader.getSystemResource("snake_game/icon/animal3.webp"));
//      ImageIcon n4 = new ImageIcon(ClassLoader.getSystemResource("snake_game/icon/animal4.webp"));
//      ImageIcon n5 = new ImageIcon(ClassLoader.getSystemResource("snake_game/icon/special.webp"));
        ImageIcon n6 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icon/apple.png"));
        apple = n6.getImage();

        ImageIcon n7 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icon/head.png"));
        head = n7.getImage();

    }

    // here  we are initializing the game
    public void initGame() {
        // initially we need three dots //here oure dots is global variable
        dots = 3;

        for (int i = 0; i < dots; i++) {
            y[i] = 50;
            x[i] = 50 - i * DOT_SIZE;

        }
        locateApple();

        Timer timer = new Timer(140, this);
        timer.start();

    }

    // creating random apple location
    public void locateApple() {
        int r = (int) (Math.random() * RANDOM_POSITION); // random positon for x axis
        apple_x = r * DOT_SIZE; // for x-axis

        r = (int) (Math.random() * RANDOM_POSITION);
        apple_y = r * DOT_SIZE; //for y-axis

    }

    /// creating paint component
    public void paintComponent(Graphics g) { // it is fixed class method 
        super.paintComponent(g); // super help parent component are calling 

        draw(g);
    }

    //creating how our design of the snake will form
    public void draw(Graphics g) {
        // to show game over we need to put if statement in it
        //drawing apple for the snake
        if(inGame) {
            g.drawImage(apple, apple_x, apple_y, this);

            // drawing of dot of the snake
            for (int i = 0; i < dots; i++) {
                if (i == 0) {
                    g.drawImage(head, x[i], y[i], this); // her this is observer
                } else {
                    g.drawImage(dot, x[i], y[i], this); // her this is observer
                }
            }
            
            Toolkit.getDefaultToolkit().sync();
        } 
        else{
            gameOver(g);
        }

    }

    public void gameOver(Graphics g) {
        String msg = "Game Over!";
        // font style and sizes
        Font font = new Font("SAN_SERIF", Font.BOLD, 14);

        FontMetrics metrices = getFontMetrics(font);
        g.setColor(Color.WHITE);
        g.setFont(font);
        // here we are using matrices bcs it 
        g.drawString(msg, (400 - metrices.stringWidth(msg)) / 2, 400 / 2);// here metrices is object in the Font class and width and hight respectively

        
       
    }

    // creting the method for move() function
    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];

        }

        // now here to move the game we need to put the check 
        if (leftDirection) {
            x[0] = x[0] - DOT_SIZE;

        }
        if (rightDirection) {
            x[0] = x[0] + DOT_SIZE;

        }
        if (upDirection) {
            y[0] = y[0] - DOT_SIZE;

        }
        if (downDirection) {
            y[0] = y[0] + DOT_SIZE;

        }

        x[0] += DOT_SIZE;
        y[0] += DOT_SIZE;
    }

    // it is the collision of the apple and the snake
    public void checkApple() {
        // here if cordinate of the apple and the snake is same then it collide and snake become larger
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();
        }

    }

    // here it is the collision of the snake and the snake
    public void checkCollision() {

        //here we are using reverse looping 
        for (int i = dots; i > 0; i--) {
            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) { // x[0] is the head of the snake
                // x[i] is the rest body of the snake
                inGame = false;
            }

        }
        if (y[0] >= 400) {
            inGame = false;
        }
        if (y[0] <= 0) {
            inGame = false;
        }

        if (x[0] >= 400) {
            inGame = false;
        }
        if (x[0] <= 0) {
            inGame = false;
        }

        // if snake is still moving then wwe are going to stp the timer of the game
        if (!inGame) {
            timer.stop();
        }

    }

    // ActionPerformed methode for perform our action in out game
    public void actionPerformed(ActionEvent ae) {
        if (inGame) {
            move();
            checkApple();
            checkCollision();

        }
        repaint();
    }

    // to caputre our key when we press so there is a class that is TAdapter and it use the super class of the keyAdapter 
    public class TAdapter extends KeyAdapter {

        // her we are invoking the method of the keyAdapter called keyPressed(KeyEvent e) method
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            // now we are inputing our check on this key variable

            if (key == KeyEvent.VK_LEFT && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;

            }
            if (key == KeyEvent.VK_RIGHT && (!leftDirection)) {

                upDirection = false;
                downDirection = false;
                rightDirection = true;

            }
            if (key == KeyEvent.VK_UP && (!downDirection)) {
                leftDirection = false;
                upDirection = true;
                rightDirection = false;

            }
            if (key == KeyEvent.VK_DOWN && (!upDirection)) {
                leftDirection = false;
                downDirection = true;
                rightDirection = false;

            }

        }
    }
}
