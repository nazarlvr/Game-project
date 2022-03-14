package main;

import game.Game;
import org.w3c.dom.events.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class Main
{
    public static int parshit = 0;
    public static Game game;
    public static void main(String[] args)
    {
        Game game = new Game();

        // create a frame to contain our game
        JFrame container = new JFrame("Space Invaders 101");
        container.setExtendedState(JFrame.MAXIMIZED_BOTH);
        container.setUndecorated(true);

        JPanel panel = (JPanel) container.getContentPane();
        game.panel = panel;
        panel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setLayout(null);
        //game.renderWorld.addKeyListener(game);
        game.renderWorld.addMouseListener(game);
        game.renderWorld.addMouseMotionListener(game);
        game.renderWorld.addMouseWheelListener(game);


        panel.addKeyListener(game);
        //panel.addMouseListener(game);
        //panel.addMouseMotionListener(game);
        panel.add(game.renderWorld);

        panel.setFocusable(true);
        container.pack();
        container.setResizable(false);
        container.setLocationRelativeTo(null);
        container.setVisible(true);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game.renderWorld.BS();
        game.loop();

        container.dispatchEvent(new WindowEvent(container, WindowEvent.WINDOW_CLOSING));

        // get hold the content of the frame and set up the resolution of the game


        // setup our canvas size and put it into the content of the frame




        // Tell AWT not to bother repainting our canvas since we're
        // going to do that our self in accelerated mode
        //game.renderGame.setIgnoreRepaint(true);

        // finally make the window visible


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


        // initialise the entities in our game so there's something
        // to see at startup
        //initEntities();
    }
}