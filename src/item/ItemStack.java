package item;

import java.util.Iterator;

public class ItemStack
{
    public int stack_size;
    public Item item;

    public ItemStack(Item i, int s)
    {
        this.item = i.copy();
        this.stack_size = s;
    }

    public ItemStack(Item i)
    {
        this(i ,1);
    }

    public ItemStack copy()
    {
        return new ItemStack(this.item, this.stack_size);
    }

    public ItemStack add(ItemStack s)
    {
        if (s == null || s.item == null || s.stack_size == 0)
            return null;

        if (this.item.equals(s.item))
        {
            int d = this.item.getMaxStackSize() - this.stack_size;

            if (d > s.stack_size)
                d = s.stack_size;

            this.stack_size += d;

            if (d == s.stack_size)
                return null;
            else
                return new ItemStack(s.item, s.stack_size - d);
        }
        else
            return s;
    }

    @Override
    public String toString()
    {
        if (this.item == null || this.stack_size == 0)
            return "null";
        return "ItemStack(" + this.item.itemId + ", " + this.stack_size + " / " + this.item.getMaxStackSize() + ")";
    }

    /*public static ItemStack create(Item i, int s)
    {
        return new ItemStack(i, s);
    }

    public static ItemStack create(Item i)
    {
        return ItemStack.create(i, 1);
    }*/

    /*@Override
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
    }*/
}
