package block;

import item.Item;

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
    public static final Block coal_ore = new BlockOre(3);
    public static final Block grass = new BlockGrass(4);
    public static final Block bedrock = new Block(5);
    public static final Block stone_slab = new BlockSlab(6);
    public static final Block oak_sapling = new BlockSapling(7);
    public static final Block oak_log = new BlockOakLog(8);
    public static final Block oak_leaves = new BlockOakLeaf(9);
    public static final Block chest = new BlockChest(10);
    public static final Block furnace = new BlockFurnace(11);

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

    public static Block getBlockFromItem(Item i)
    {
        if (i.itemId >= 0)
            return null;

        Block b = blocks_map.get(-i.itemId).copy();
        b.blockData = i.itemData;
        return b;
    }

    public static final Map<Integer, Block> blocks_map = Map.ofEntries(
            Map.entry(dirt.blockId, dirt),
            Map.entry(stone.blockId, stone),
            Map.entry(coal_ore.blockId, coal_ore),
            Map.entry(grass.blockId, grass),
            Map.entry(bedrock.blockId, bedrock),
            Map.entry(stone_slab.blockId, stone_slab),
            Map.entry(oak_sapling.blockId, oak_sapling),
            Map.entry(oak_log.blockId, oak_log),
            Map.entry(oak_leaves.blockId, oak_leaves),
            Map.entry(chest.blockId, chest),
            Map.entry(furnace.blockId, furnace)
    );

    public static final Map<Integer, BufferedImage> textures_map = Map.ofEntries(
            Map.entry(dirt.blockId, loadTexture("dirt.png")),
            Map.entry(stone.blockId, loadTexture("stone.png")),
            Map.entry(coal_ore.blockId, loadTexture("coal.png")),
            Map.entry(grass.blockId, loadTexture("grass_0.png")),
            Map.entry(bedrock.blockId, loadTexture("bedrock.png")),
            Map.entry(stone_slab.blockId, loadTexture("stone.png")),
            Map.entry(oak_sapling.blockId, loadTexture("oak_sapling.png")),
            Map.entry(oak_log.blockId, loadTexture("oak_log.png")),
            Map.entry(oak_leaves.blockId, loadTexture("oak_leaves.png")),
            Map.entry(chest.blockId, loadTexture("chest.png")),
            Map.entry(furnace.blockId, loadTexture("furnace_front.png"))
    );
}
