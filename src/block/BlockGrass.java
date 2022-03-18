package block;

import item.ItemStack;
import item.Items;
import world.World;

import java.util.Random;

public class BlockGrass extends Block
{
    public BlockGrass(int id)
    {
        super(id);
        this.blockData = 200;
    }

    @Override
    public void tick(World world, int x, int y) {
        if (this.blockData > 0)
            --this.blockData;

        super.tick(world, x, y);
    }

    @Override
    public Block copy()
    {
        BlockGrass b = new BlockGrass(this.blockId);
        b.blockData = this.blockData;
        return b;
    }

    @Override
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
