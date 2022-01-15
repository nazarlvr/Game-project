package world;

import block.Block;
import block.Blocks;

public class World
{
    private String name;
    public static final int width = 20;
    public static final int height = 10;
    private Block[][] blocks;
    private long time;

    public World(String n)
    {
        name = n;
        time = 0;
        blocks = new Block[width][height];

        blocks[0][0] = Blocks.dirt;
        blocks[0][1] = Blocks.dirt;
        blocks[1][0] = Blocks.stone;
        blocks[1][1] = Blocks.dirt;
        blocks[1][2] = Blocks.grass;
    }

    public void tick()
    {
        for (int x = 0; x < width; ++x)
            for (int y = 0; y < height; ++y)
            {
                Block b = blocks[x][y];

                if (b != null)
                    b.tick();
            }

        ++time;
    }

    public Block getBlock(int x, int y)
    {
        return blocks[x][y];
    }

    public long getTime()
    {
        return time;
    }
}
