package main;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class Main
{
    public static int parshit = 0;
    public static Game game;
    public static void main(String[] args) throws InterruptedException {
        // create a frame to contain our game
        JFrame container = new JFrame("Space Invaders 101");
        container.setExtendedState(JFrame.MAXIMIZED_BOTH);
        container.setUndecorated(true);

        // get hold the content of the frame and set up the resolution of the game
        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setLayout(null);

        // setup our canvas size and put it into the content of the frame
        game = new Game();
        container.addKeyListener(game);

        panel.add(game.renderWorld);

        // Tell AWT not to bother repainting our canvas since we're
        // going to do that our self in accelerated mode
        //game.renderGame.setIgnoreRepaint(true);

        // finally make the window visible
        container.pack();
        container.setResizable(false);
        container.setVisible(true);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add a listener to respond to the user closing the window. If they
        // do we'd like to exit the game
        /*container.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });*/

        // add a key input system (defined below) to our canvas
        // so we can respond to key pressed
        //addKeyListener(new KeyInputHandler());

        // request the focus so key events come to us
        //requestFocus();

        // create the buffering strategy which will allow AWT
        // to manage our accelerated graphics
        game.renderWorld.BS();

        // initialise the entities in our game so there's something
        // to see at startup
        //initEntities();

        game.loop();
    }
}