package block;

import util.AABB;

public class Block
{
    public final int blockId;
    public int blockData;
    public AABB aabb;

    public Block(int id)
    {
        blockId = id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Block)
            return ((Block) o).blockId == this.blockId;
        else
            return false;
    }

    public Block copy()
    {
        Block b = new Block(this.blockId);
        b.blockData = this.blockData;
        return b;
    }

    public boolean isCollideable()
    {
        return true;
    }

    public void initAABB(int x, int y)
    {
        this.aabb = new AABB(x, y, 1, 1);
    }

    public AABB getAABB()
    {
        return this.aabb;
    }

    public void tick() {}
}
