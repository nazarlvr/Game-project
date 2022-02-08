package game;

import entity.Entity;
import entity.EntityPlayer;
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
import java.util.concurrent.TimeUnit;

public class Game implements KeyListener, MouseListener
{
    /** True if the game is currently "running", i.e. the game loop is looping */
    public boolean gameRunning = true;
    public RenderWorld renderWorld;
    public World world;
    public static final int tick_frequency = 20;
    public static final double collisionPrecision = 1e-12;
    public static final double velMax = collisionPrecision * Game.tick_frequency;
    public JPanel panel;

    private boolean W_pressed, A_pressed, D_pressed, ESC_pressed;

    public Game()
    {
        //System.out.println(velMax);
        world = new World("World 1");
        world.timeStart = System.currentTimeMillis();
        renderWorld = new RenderWorld(world);
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        renderWorld.setBounds(0,0, ss.width, ss.height);
        renderWorld.setIgnoreRepaint(true);
    }

    public void loop()
    {
        long lastLoopTime = System.currentTimeMillis();

        // keep looping round til the game ends
        while (gameRunning) {
            panel.requestFocusInWindow();
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            //long delta = System.currentTimeMillis() - lastLoopTime;
            lastLoopTime = System.nanoTime();

            // Get hold of a graphics context for the accelerated
            // surface and blank it out

            //if ()

            world.tick();
            renderWorld.render();
            this.processKeyPresses();

            // cycle round asking each entity to move itself
            /*if (!waitingForKeyPress) {
                for (int i=0;i<entities.size();i++) {
                    Entity entity = (Entity) entities.get(i);

                    entity.move(delta);
                }
            }

            // cycle round drawing all the entities we have in the game
            for (int i=0;i<entities.size();i++) {
                Entity entity = (Entity) entities.get(i);

                entity.draw(g);
            }

            // brute force collisions, compare every entity against
            // every other entity. If any of them collide notify
            // both entities that the collision has occured
            for (int p=0;p<entities.size();p++) {
                for (int s=p+1;s<entities.size();s++) {
                    Entity me = (Entity) entities.get(p);
                    Entity him = (Entity) entities.get(s);

                    if (me.collidesWith(him)) {
                        me.collidedWith(him);
                        him.collidedWith(me);
                    }
                }
            }

            // remove any entity that has been marked for clear up
            entities.removeAll(removeList);
            removeList.clear();

            // if a game event has indicated that game logic should
            // be resolved, cycle round every entity requesting that
            // their personal logic should be considered.
            if (logicRequiredThisLoop) {
                for (int i=0;i<entities.size();i++) {
                    Entity entity = (Entity) entities.get(i);
                    entity.doLogic();
                }

                logicRequiredThisLoop = false;
            }

            // if we're waiting for an "any key" press then draw the
            // current message
            if (waitingForKeyPress) {
                g.setColor(Color.white);
                g.drawString(message,(800-g.getFontMetrics().stringWidth(message))/2,250);
                g.drawString("Press any key",(800-g.getFontMetrics().stringWidth("Press any key"))/2,300);
            }*/

            // finally, we've completed drawing so clear up the graphics
            // and flip the buffer over


            // resolve the movement of the ship. First assume the ship
            // isn't moving. If either cursor key is pressed then
            // update the movement appropraitely
            /*ship.setHorizontalMovement(0);

            if ((leftPressed) && (!rightPressed)) {
                ship.setHorizontalMovement(-moveSpeed);
            } else if ((rightPressed) && (!leftPressed)) {
                ship.setHorizontalMovement(moveSpeed);
            }

            // if we're pressing fire, attempt to fire
            if (firePressed) {
                tryToFire();
            }*/

            // finally pause for a bit. Note: this should run us at about
            // 100 fps but on windows this might vary each loop due to
            // a bad implementation of timer

            long tickEnd = lastLoopTime + 1000000000 / Game.tick_frequency;
            while (System.nanoTime() < tickEnd);

            /*long tickDuration = System.currentTimeMillis() - lastLoopTime;

            try
            {
                TimeUnit.MILLISECONDS.sleep(1000 / Game.tick_frequency - tickDuration);
            }
            catch (Exception e) {}*/

            //System.out.println(System.currentTimeMillis() - lastLoopTime);
            //System.out.println(1000 / Game.tick_frequency);


            //try { Thread.sleep(1000 / Game.tick_frequency - tickDuration); } catch (Exception e) {}
        }
    }

    private void processKeyPresses()
    {
        if (this.ESC_pressed)
        {
            gameRunning = false;
            return;
        }

        EntityPlayer player =(EntityPlayer)world.getEntities().toArray()[0];

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
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
