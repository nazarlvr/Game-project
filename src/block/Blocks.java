package block;

import render.RenderBlock;
import render.RenderGrass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Blocks
{
    public static final String textures_path = "textures/blocks/";
    public static final Block dirt = new Block(1);
    public static final Block stone = new Block(2);
    public static final Block coal = new Block(3);
    public static final Block grass = new BlockGrass(4);

    public static BufferedImage loadTexture(String filepath)
    {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(textures_path + filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public static final Map<Block, BufferedImage> textures_map = Map.ofEntries(
            Map.entry(dirt, loadTexture("dirt.png")),
            Map.entry(stone, loadTexture("stone.png")),
            Map.entry(coal, loadTexture("coal.png")),
            Map.entry(grass, loadTexture("grass_0.png"))
    );
}
