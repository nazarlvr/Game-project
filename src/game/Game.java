package game;

import entity.EntityPlayer;
import gui.Gui;
import gui.GuiPlayer;
import render.RenderWorld;
import world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
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
    public Gui currentGui;

    public boolean W_pressed, A_pressed, D_pressed, E_pressed, R_pressed, ESC_pressed, SHIFT_pressed;
    public boolean W_clicked, A_clicked, D_clicked, E_clicked, R_clicked, ESC_clicked, SHIFT_clicked;
    public boolean mouse_left_pressed, mouse_right_pressed;
    public boolean mouse_left_clicked, mouse_right_clicked;
    public int mouseX, mouseY;
    public int mouseWheelRotaion;

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

        while (gameRunning)
        {
            panel.requestFocusInWindow();
            lastLoopTime = System.nanoTime();

            if (this.player != null && this.player.isDead)
                this.player = null;

            if (this.player == null)
                this.player = world.findPlayer();

            if (this.currentGui == null && this.player != null)
                this.currentGui = new GuiPlayer(this, this.player, RenderWorld.minX, RenderWorld.minY, RenderWorld.maxX, RenderWorld.maxY);

            world.tick();
            renderWorld.render();

            if (this.currentGui != null)
                this.currentGui.processKeyPresses();

            this.processKeyPresses();
            this.resetClicks();

            tickEnd += 1000000000 / Game.tick_frequency;
            while (System.nanoTime() < tickEnd);

        }

    }

    private void processKeyPresses()
    {
        if (player == null)
            player = world.findPlayer();

        if (player == null)
        {
            if (this.R_pressed)
            {
                this.world.spawnEntity(new EntityPlayer(5,5, this));
            }
        }
    }
    public void resetClicks()
    {
        this.W_clicked = false;
        this.A_clicked = false;
        this.D_clicked = false;
        this.E_clicked = false;
        this.R_clicked = false;
        this.ESC_clicked = false;
        this.SHIFT_clicked = false;
        this.mouse_left_clicked = false;
        this.mouse_right_clicked = false;
        this.mouseWheelRotaion = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W:
                if (!this.W_pressed)
                    this.W_clicked = true;
                this.W_pressed = true;
                break;
            case KeyEvent.VK_A:
                if (!this.A_pressed)
                    this.A_clicked = true;
                this.A_pressed = true;
                break;
            case KeyEvent.VK_D:
                if (!this.D_pressed)
                    this.D_clicked = true;
                this.D_pressed = true;
                break;
            case KeyEvent.VK_E:
                if (!this.E_pressed)
                    this.E_clicked = true;
                this.E_pressed = true;
                break;
            case KeyEvent.VK_R:
                if (!this.R_pressed)
                    this.R_clicked = true;
                this.R_pressed = true;
                break;
            case KeyEvent.VK_ESCAPE:
                if (!this.ESC_pressed)
                    this.ESC_clicked = true;
                this.ESC_pressed = true;
                break;
            case KeyEvent.VK_SHIFT:
                if (!this.SHIFT_pressed)
                    this.SHIFT_clicked = true;
                this.SHIFT_pressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W:
                this.W_pressed = false;
                break;
            case KeyEvent.VK_A:
                this.A_pressed = false;
                break;
            case KeyEvent.VK_D:
                this.D_pressed = false;
                break;
            case KeyEvent.VK_E:
                this.E_pressed = false;
                break;
            case KeyEvent.VK_R:
                this.R_pressed = false;
                break;
            case KeyEvent.VK_ESCAPE:
                this.ESC_pressed = false;
                break;
            case KeyEvent.VK_SHIFT:
                this.SHIFT_pressed = false;
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            this.mouse_left_clicked = !this.mouse_left_pressed;
            this.mouse_left_pressed = true;
        }
        else if (e.getButton() == MouseEvent.BUTTON3)
        {
            this.mouse_right_clicked = !this.mouse_right_pressed;
            this.mouse_right_pressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            this.mouse_left_clicked = false;
            this.mouse_left_pressed = false;
        }
        else if (e.getButton() == MouseEvent.BUTTON3)
        {
            this.mouse_right_clicked = false;
            this.mouse_right_pressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        this.mouseX = e.getXOnScreen();
        this.mouseY = e.getYOnScreen();
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        this.mouseX = e.getXOnScreen();
        this.mouseY = e.getYOnScreen();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        this.mouseWheelRotaion += e.getWheelRotation();
    }
}
