package block;

import entity.EntityPlayer;
import gui.GuiFurnace;
import inventory.Inventory;
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
        this.inventory = new Inventory(27);
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
