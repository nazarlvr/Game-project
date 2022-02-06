package block;

import util.AABB;

public class BlockSlab extends Block
{
    public BlockSlab(int id)
    {
        super(id);
        //this.blockData = 200;
    }

    @Override
    public boolean isCollideable()
    {
        return true;
    }

    @Override
    public Block copy()
    {
        BlockSlab b = new BlockSlab(this.blockId);
        b.blockData = this.blockData;
        return b;
    }

    @Override
    public void initAABB(int x, int y) {
        this.aabb = new AABB(x, y, 1, 0.5);
    }
}
