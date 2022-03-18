package block;

import item.ItemStack;
import item.Items;
import world.World;

public class BlockOakLeaf extends Block{
    public BlockOakLeaf(int id)
    {
        super(id);
    }

    @Override
    public void tick(World world, int x, int y)
    {
        this.blockData = 0;

        if (world.getBlockId(x - 1, y) == Blocks.oak_log.blockId || world.getBlockId(x + 1, y) == Blocks.oak_log.blockId || world.getBlockId(x, y - 1) == Blocks.oak_log.blockId)
            this.blockData = 3;

        if (world.getBlockId(x - 1, y) == Blocks.oak_leaves.blockId && world.getBlockData(x - 1, y) > this.blockData)
            this.blockData = world.getBlockData(x - 1, y) - 1;

        if (world.getBlockId(x + 1, y) == Blocks.oak_leaves.blockId && world.getBlockData(x + 1, y) > this.blockData)
            this.blockData = world.getBlockData(x + 1, y) - 1;

        if (world.getBlockId(x, y - 1) == Blocks.oak_leaves.blockId && world.getBlockData(x, y - 1) > this.blockData)
            this.blockData = world.getBlockData(x, y - 1) - 1;

        if (this.blockData == 0)
        {
            world.breakBlock(x, y);
            return;
        }

        super.tick(world, x, y);
    }

    @Override
    public Block copy()
    {
        BlockOakLeaf b = new BlockOakLeaf(this.blockId);
        b.blockData = this.blockData;
        return b;
    }

    public ItemStack getDrop()
    {
        return new ItemStack(Items.beef, 5);
    }

    @Override
    public boolean isCollidable()
    {
        return false;
    }
}
