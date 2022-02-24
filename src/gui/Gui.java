package gui;

import game.Game;
import render.RenderWorld;

import java.awt.*;

public class Gui
{
    public Game game;
    protected final int minX, minY, maxX, maxY;

    public Gui(Game g, int bx, int by, int ex, int ey)
    {
        this.game = g;
        this.minX = bx;
        this.minY = by;
        this.maxX = ex;
        this.maxY = ey;
    }

    public Gui(Game g)
    {
        this(g, RenderWorld.minX, RenderWorld.minY, RenderWorld.maxX, RenderWorld.maxY);
    }

    /*public Gui(Game g)
    {
        this(g, 0, 0, 0, 0);
    }*/

    public void open()
    {
        if (this.game.currentGui != null)
            this.game.currentGui.close();

        this.game.currentGui = this;
    }

    public void close()
    {
        this.game.currentGui = null;
    }

    public void render(Graphics g)
    {

    }

    public void processKeyPresses()
    {

    }
}
