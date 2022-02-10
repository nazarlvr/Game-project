package item;

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
    public static final Item beef = new Item(7);

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
//            Map.entry(dirt.itemId, loadTexture("dirt.png")),
//            Map.entry(stone.itemId, loadTexture("stone.png")),
//            Map.entry(coal.itemId, loadTexture("coal.png")),
//            Map.entry(grass.itemId, loadTexture("grass_0.png")),
//            Map.entry(bedrock.itemId, loadTexture("bedrock.png")),
//            Map.entry(stone_slab.itemId, loadTexture("stone.png"))
              Map.entry(beef.itemId, loadTexture("beef.png"))
    );
}
