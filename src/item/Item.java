package item;

import block.Block;

public class Item
{
    public final int itemId;
    public int itemData;

    public Item(int itemId, int itemData)
    {
        this.itemId = itemId;
    }

    public Item(int itemId)
    {
        this(itemId, 0);
    }

    public Item(Block block)
    {
        this(-block.blockId, block.blockData);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Item)
            return ((Item) o).itemId == this.itemId && ((Item) o).itemData == this.itemData;
        else
            return false;
    }

    public Item copy()
    {
        Item b = new Item(this.itemId);
        b.itemData = this.itemData;
        return b;
    }

    public int getMaxStackSize()
    {
        return 5;
    }
}
