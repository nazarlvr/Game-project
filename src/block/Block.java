package block;

import entity.EntityItem;
import item.Item;
import item.ItemStack;
import util.AABB;
import world.World;

public class Block
{
    public final int blockId;
    public int blockData;
    public AABB aabb;

    public Block(int id, int data)
    {
        blockId = id;
        this.blockData = data;
    }

    public Block(int id)
    {
        this(id, 0);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Block)
            return ((Block) o).blockId == this.blockId && ((Block) o).blockData == this.blockData;
        else
            return false;
    }

    public Block copy()
    {
        Block b = new Block(this.blockId);
        b.blockData = this.blockData;
        return b;
    }

    public boolean isCollidable()
    {
        return true;
    }

    public void initAABB(int x, int y)
    {
        this.aabb = new AABB(x, y, 1, 1);
    }

    public void breakBlock(World world, int x, int y)
    {
        ItemStack d = this.getDrop();

        if (d != null)
            world.spawnEntity(new EntityItem(x + 0.5, y + 0.5, d));
    }

    public ItemStack getDrop()
    {
        return null;
    }

    public AABB getAABB()
    {
        return this.aabb;
    }

    public void tick(World world, int x, int y) {}
}
