package block;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Blocks
{
    public static final Block dirt = new Block(1);
    public static final Block stone = new Block(2);
    public static final Block coal = new Block(3);

    public static final BufferedImage dirt_texture = loadTexture("D:/dirt.png");

    public static BufferedImage loadTexture(String filepath)
    {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
