package render;

import block.Blocks;
import main.Main;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class RenderGame extends Canvas
{
    public BufferStrategy strategy;

    /*public void paint(Graphics g) {
        g.drawString("Hello",40,40);
        //g.drawString("Idy nahuy",40,40);

        setBackground(Color.WHITE);
        g.fillRect(130, 30,100, 80);
        g.drawOval(30,130,50, 60);
        setForeground(Color.RED);
        g.fillOval(130,130,50, 60);
        g.drawArc(30, 200, 40,50,90,60);
        g.fillArc(30, 130, 40,50,180,40);
    }*/

    public void BS()
    {
        createBufferStrategy(2);
        strategy = getBufferStrategy();
    }

    public void render()
    {
        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        g.clearRect(0,0, getWidth(), getHeight());

        g.setFont(new Font("TimesRoman", Font.PLAIN, 26));
        g.drawString(Integer.toString(Main.parshit),40,40);
        Main.parshit++;

        setBackground(Color.WHITE);
        g.fillRect(130, 30,100, 80);
        g.drawOval(30,130,50, 60);
        setForeground(Color.RED);
        g.fillOval(130,130,50, 60);
        g.drawArc(30, 200, 40,50,90,60);
        g.fillArc(30, 130, 40,50,180,40);

        RenderBlock rb = new RenderBlock(Blocks.dirt);
        rb.render(g, 500,500, 50, 50);

        g.dispose();
        strategy.show();
    }
}
