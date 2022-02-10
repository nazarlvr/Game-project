package item;

import block.Block;

public class Item {
    public final int itemId;
    public int itemData;

    public Item(int itemId) {
        this.itemId = itemId;
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
}
