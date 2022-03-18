package block;

import entity.EntityPlayer;
import gui.GuiFurnace;
import inventory.Inventory;
import item.ItemStack;
import item.Items;
import world.World;

public class BlockFurnace extends Block
{
    public Inventory inventory;

    public BlockFurnace(int id)
    {
        super(id);
    }

    @Override
    public void init(int x, int y)
    {
        super.init(x, y);
        this.inventory = new Inventory(3);
        this.blockData = 0;
    }

    @Override
    public void tick(World w, int x, int y) {
        super.tick(w, x, y);

        if (!ItemStack.isEmpty(this.inventory.items[2]) && this.blockData % 1000 == 0 && !ItemStack.isEmpty(this.inventory.items[0]))
        {
            this.blockData += 20 * 3;
            --this.inventory.items[2].stack_size;
        }

        if (this.blockData % 1000 != 0)
        {
            if (ItemStack.isEmpty(this.inventory.items[0]))
            {
                this.blockData %= 1000;
            }
            else
            {
                this.blockData += 1000;
            }
        }

        if (!ItemStack.isEmpty(this.inventory.items[0]) && this.blockData / 1000 >= 5 * 20)
        {
            this.blockData -= 5 * 20 * 1000;
            --this.inventory.items[0].stack_size;

            if (ItemStack.isEmpty(this.inventory.items[1]))
                this.inventory.items[1] = new ItemStack(Items.beef, 1);
            else
                this.inventory.items[1].add(new ItemStack(Items.beef, 1));
        }

        if (this.blockData % 1000 > 0)
            --this.blockData;
    }

    @Override
    public void interact(World w, EntityPlayer p, int x, int y)
    {
        super.interact(w, p, x, y);
        GuiFurnace guiFurnace = new GuiFurnace(p.game, p, this);
        guiFurnace.open();
    }

    @Override
    public Block copy() {
        block.BlockFurnace b = new block.BlockFurnace(this.blockId);
        b.blockData = this.blockData;
        return b;
    }

    @Override
    public void breakBlock(World w, int x, int y) {
        super.breakBlock(w, x, y);
        this.inventory.dropItems(w, x + 0.5,y + 0.5);
    }
}
