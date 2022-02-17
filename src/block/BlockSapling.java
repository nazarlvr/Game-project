package block;

import item.ItemStack;
import item.Items;
import world.World;

public class BlockSapling extends Block
{
    public BlockSapling(int id)
    {
        super(id);
        this.blockData = 200;
    }

    @Override
    public void tick(World world, int x, int y) {
        if (world.getBlock(x, y - 1 ) == null)
        {
            world.breakBlock(x, y);
            return;
        }
        if (this.blockData > 0)
            --this.blockData;
        if (this.blockData == 0)
        {
            Block leaves = Blocks.oak_leaves.copy();
            leaves.blockData = 3;

            world.setBlock(x, y, Blocks.oak_log);
            world.setBlock(x, y + 1, Blocks.oak_log);
            world.setBlock(x, y + 2, Blocks.oak_log);
            world.setBlock(x, y + 3, leaves);
            world.setBlock(x, y + 4, leaves);
            world.setBlock(x, y + 5, leaves);
            world.setBlock(x + 1, y + 3, leaves);
            world.setBlock(x - 2, y + 3, leaves);
            world.setBlock(x + 2, y + 3, leaves);
            world.setBlock(x + 1, y + 4, leaves);
            world.setBlock(x - 1, y + 3, leaves);
            world.setBlock(x - 1, y + 4, leaves);
            world.setBlock(x + 1, y + 2, leaves);
            world.setBlock(x - 1, y + 2, leaves);
        }

        super.tick(world, x, y);
    }

    @Override
    public Block copy()
    {
        BlockSapling b = new BlockSapling(this.blockId);
        b.blockData = this.blockData;
        return b;
    }

    public ItemStack getDrop()
    {
        return new ItemStack(Items.beef, this.blockData == 0 ? 5 : 1);
    }

    @Override
    public boolean isCollidable()
    {
        return false;
    }
}
