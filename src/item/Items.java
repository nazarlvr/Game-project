package item;

import block.Block;
import item.Item;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Items {
      public static final String textures_path = "textures/items/";
//    public static final Item dirt = new Item(1);
//    public static final Item stone = new Item(2);
//    public static final Item coal = new Item(3);
//    //public static final Item grass = new ItemGrass(4);
//    public static final Item bedrock = new Item(5);
//    //public static final Item stone_slab = new ItemSlab(6);
    public static final Item beef = new Item(1);
    public static final Item slime = new Item(2);

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

    public static Item getItemFromBlock(Block b)
    {
        if (b == null || b.blockId <= 0)
            return null;

        return new Item(b);
    }

    public static final Map<Integer, BufferedImage> textures_map = Map.ofEntries(
            Map.entry(beef.itemId, loadTexture("beef.png")),
            Map.entry(slime.itemId, loadTexture("slime.png"))
    );
}
