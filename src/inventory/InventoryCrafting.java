package inventory;

import block.Blocks;
import item.Item;
import item.ItemStack;
import item.Items;

public class InventoryCrafting extends Inventory {
    public int[] quantities;
    public int[] recipes_id;

    public InventoryCrafting(int s)
    {
        super(s);
        this.quantities = new int[this.size];
        this.recipes_id = new int[this.size];
    }

    public void update_input(Inventory i)
    {
        for (int j = 0; j < this.size; ++j)
        {
            if (this.quantities[j] > 0)
            {
                if (ItemStack.isEmpty(this.items[j]))
                {
                    for (ItemStack s : recipes[this.recipes_id[j]].input)
                    {
                        ItemStack is = s.copy();
                        is.stack_size *= this.quantities[j];
                        i.remove(is);
                    }
                }
                else
                {
                    for (ItemStack s : recipes[this.recipes_id[j]].input)
                    {
                        ItemStack is = s.copy();
                        is.stack_size *= this.quantities[j] - this.items[j].stack_size;
                        i.remove(is);
                    }
                }
            }
        }
    }

    public void update_output(Inventory i)
    {
        for (int j = 0; j < this.size; ++j)
        {
            this.items[j] = null;
            this.quantities[j] = 0;
            this.recipes_id[j] = 0;
        }

        int s = 0;

        for (int j = 0; j < recipes.length; ++j)
        {
            int c = recipes[j].count(i);

            if (c > 0)
            {
                this.quantities[s] = c;
                this.items[s] = new ItemStack(recipes[j].output);
                this.items[s].stack_size *= c;
                this.recipes_id[s] = j;
                ++s;
            }
        }
    }

    public static final Recipe[] recipes = {
            new Recipe(new ItemStack[]{new ItemStack(Items.beef, 1), new ItemStack(Items.getItemFromBlock(Blocks.bedrock), 1)}, Items.slime),
            new Recipe(new ItemStack[]{new ItemStack(Items.slime, 1), new ItemStack(Items.getItemFromBlock(Blocks.dirt), 3)}, Items.getItemFromBlock(Blocks.coal_ore)),
    };

    private static class Recipe
    {
        public ItemStack[] input;
        public Item output;

        public Recipe(ItemStack[] in, Item out)
        {
            this.input = in;
            this.output = out;
        }

        public int count(Inventory i)
        {
            int c = output.getMaxStackSize();

            for (ItemStack is : input)
            {
                int q = i.countItem(is.item) / is.stack_size;

                if (c > q)
                    c = q;
            }

            return c;
        }
    }
}
