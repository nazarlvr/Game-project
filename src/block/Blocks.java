package block;

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
    public static final Block bedrock = new Block(5);
    public static final Block stone_slab = new BlockSlab(6);

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

    public static final Map<Integer, BufferedImage> textures_map = Map.ofEntries(
            Map.entry(dirt.blockId, loadTexture("dirt.png")),
            Map.entry(stone.blockId, loadTexture("stone.png")),
            Map.entry(coal.blockId, loadTexture("coal.png")),
            Map.entry(grass.blockId, loadTexture("grass_0.png")),
            Map.entry(bedrock.blockId, loadTexture("bedrock.png")),
            Map.entry(stone_slab.blockId, loadTexture("stone.png"))
    );
}
