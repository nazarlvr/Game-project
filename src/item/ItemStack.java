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
        if (isEmpty(s))
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

    public ItemStack split()
    {
        if (isEmpty(this))
            return null;

        ItemStack half = this.copy();
        this.stack_size /= 2;
        half.stack_size -= this.stack_size;

        if (isEmpty(half))
            return null;

        return half;
    }

    @Override
    public String toString()
    {
        if (isEmpty(this))
            return "null";
        return "ItemStack(" + this.item.itemId + ", " + this.stack_size + " / " + this.item.getMaxStackSize() + ")";
    }

    public static boolean isEmpty(ItemStack s)
    {
        if (s == null || s.item == null || s.stack_size == 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof ItemStack)
        {
            if (isEmpty(this) != isEmpty((ItemStack) o))
                return false;

            if (isEmpty(this))
                return true;

            return ((ItemStack) o).item.equals(this.item) && ((ItemStack) o).stack_size == this.stack_size;
        }
        else
            return false;
    }

    /*public static ItemStack create(Item i, int s)
    {
        return new ItemStack(i, s);
    }

    public static ItemStack create(Item i)
    {
        return ItemStack.create(i, 1);
    }*/

    /*public Item copy()
    {
        Item b = new Item(this.itemId);
        b.itemData = this.itemData;
        return b;
    }*/
}
