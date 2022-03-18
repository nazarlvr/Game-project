package block;

import item.ItemStack;
import item.Items;

public class BlockOre extends Block
{
    public BlockOre(int id)
    {
        super(id);
    }

    @Override
    public Block copy()
    {
        BlockOre b = new BlockOre(this.blockId);
        b.blockData = this.blockData;
        return b;
    }

    @Override
    public ItemStack getDrop()
    {
        if (this.blockId == Blocks.coal_ore.blockId)
            return new ItemStack(Items.coal, 1);
        else
            return null;
    }
}
