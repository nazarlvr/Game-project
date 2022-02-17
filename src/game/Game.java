package game;

import entity.Entity;
import entity.EntityItem;
import entity.EntityPlayer;
import gui.Gui;
import item.ItemStack;
import item.Items;
import render.RenderWorld;
import world.World;

import javax.net.ssl.KeyManager;
import javax.swing.*;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.util.concurrent.TimeUnit;

public class Game implements KeyListener, MouseListener
{
    /** True if the game is currently "running", i.e. the game loop is looping */
    public boolean gameRunning = true;
    public RenderWorld renderWorld;
    public World world;
    public EntityPlayer player;
    public static final int tick_frequency = 20;
    public static final double collisionPrecision = 1e-12;
    public static final double velMax = collisionPrecision * Game.tick_frequency;
    public JPanel panel;

    public boolean W_pressed, A_pressed, D_pressed, R_pressed, ESC_pressed, E_pressed;

    public Game()
    {
        //System.out.println(velMax);
        world = new World("World 1");
        world.timeStart = System.currentTimeMillis();
        renderWorld = new RenderWorld(world);
        renderWorld.game = this;
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        renderWorld.setBounds(0,0, ss.width, ss.height);
        renderWorld.setIgnoreRepaint(true);
    }

    public void loop()
    {
        long lastLoopTime = System.currentTimeMillis();
        long tickEnd = System.nanoTime();
        while (gameRunning) {
            panel.requestFocusInWindow();
            lastLoopTime = System.nanoTime();

            world.tick();
            renderWorld.render();
            this.processKeyPresses();

            tickEnd += 1000000000 / Game.tick_frequency;
            while (System.nanoTime() < tickEnd);

        }

    }

    private void processKeyPresses()
    {
        if (this.ESC_pressed)
        {
            gameRunning = false;
            return;
        }

        if (player == null)
            player = world.findPlayer();

        if (player != null)
        {
            if (this.W_pressed)
            {
                if (!player.isAirborne)
                {
                    player.launchY(0.42);
                }
            }

            if (this.A_pressed)
            {
                player.launchX(-0.05);
            }

            if (this.D_pressed)
            {
                player.launchX(0.05);
            }

            if (player.isDead)
                player = null;
        }
        else
        {
            if (this.R_pressed)
            {
                this.world.spawnEntity(new EntityPlayer(5,5));
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            this.ESC_pressed = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_W)
        {
            this.W_pressed = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_A)
        {
            this.A_pressed = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_D)
        {
            this.D_pressed = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_R)
        {
            this.R_pressed = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_E)
        {
            this.E_pressed = !E_pressed;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            this.ESC_pressed = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_W)
        {
            this.W_pressed = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_A)
        {
            this.A_pressed = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_D)
        {
            this.D_pressed = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_R)
        {
            this.R_pressed = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        renderWorld.positionmX = e.getX();
        renderWorld.positionmY = e.getPoint().y;

        world.hit(renderWorld.blockcoordinatesX(e.getX()), renderWorld.blockcoordinatesY(e.getY()), 1);

        int x = (int)renderWorld.blockcoordinatesX(e.getX());
        int y = (int)renderWorld.blockcoordinatesY(e.getY());

        if (world.getBlock(x, y) != null)
        {
            world.breakBlock(x, y);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
