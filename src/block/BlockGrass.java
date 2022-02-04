package block;

import render.RenderGrass;

public class BlockGrass extends Block
{
    public BlockGrass(int id)
    {
        super(id);
        this.blockData = 200;
    }

    @Override
    public void tick() {
        if (this.blockData > 0)
            --this.blockData;

        super.tick();
    }

    @Override
    public Block copy()
    {
        BlockGrass b = new BlockGrass(this.blockId);
        b.blockData = this.blockData;
        return b;
    }

    @Override
    public boolean isCollideable()
    {
        return false;
    }
}
