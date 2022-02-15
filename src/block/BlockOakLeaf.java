package block;

import item.ItemStack;
import item.Items;

public class BlockOakLeaf extends Block{
    public BlockOakLeaf(int id)
    {
        super(id);
        this.blockData = 200;
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
        return new ItemStack(Items.beef, this.blockData == 0 ? 5 : 1);
    }

    @Override
    public boolean isCollidable()
    {
        return false;
    }
}
