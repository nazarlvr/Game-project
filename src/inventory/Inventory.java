package inventory;

import item.Item;
import item.ItemStack;

public class Inventory
{
    public ItemStack[] items;
    public int size;

    public Inventory(int s)
    {
        this.size = s;
        items = new ItemStack[this.size];
    }

    public ItemStack add(ItemStack st)
    {
        if (st == null)
            return null;

        ItemStack s = st.copy();

        for (int k = 0; k < this.size; ++ k)
        {
            if (this.items[k] != null && this.items[k].item.equals(s.item) && this.items[k].stack_size < this.items[k].item.getMaxStackSize())
                s = this.items[k].add(s);

            if (s == null)
                return null;
        }


        for (int k = 0; k < this.size; ++ k)
        {
            if (this.items[k] == null)
            {
                this.items[k] = s;
                return null;
            }
        }

        return s;
    }
}
