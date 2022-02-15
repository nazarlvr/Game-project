package block;

import item.ItemStack;
import item.Items;
import world.World;

public class BlockOakLog extends Block{

    public BlockOakLog(int id)
    {
        super(id);
    }

    @Override
    public void tick(World world, int x, int y) {
        if (world.getBlock(x, y - 1 ) == null)
        {
            world.breakBlock(x, y);
            return;
        }
        super.tick(world, x, y);
    }

    @Override
    public Block copy()
    {
        BlockOakLog b = new BlockOakLog(this.blockId);
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
