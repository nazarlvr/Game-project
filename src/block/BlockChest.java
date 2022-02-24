package block;

import entity.EntityPlayer;
import gui.GuiChest;
import inventory.Inventory;
import item.Item;
import item.ItemStack;
import item.Items;
import world.World;

public class BlockChest extends Block
{
    public Inventory inventory;

    public BlockChest(int id)
    {
        super(id);
    }

    @Override
    public void init(int x, int y)
    {
        super.init(x, y);
        this.inventory = new Inventory(27);
    }

    @Override
    public void interact(World w, EntityPlayer p, int x, int y)
    {
        super.interact(w, p, x, y);
        GuiChest guiChest = new GuiChest(p.game, p, this);
        guiChest.open();
    }

    @Override
    public Block copy() {
        BlockChest b = new BlockChest(this.blockId);
        b.blockData = this.blockData;
        return b;
    }
}
