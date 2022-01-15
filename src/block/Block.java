package block;

import render.RenderBlock;

public class Block
{
    public final int blockId;
    public int blockData;

    public Block(int id)
    {
        blockId = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Block)
            return ((Block) o).blockId == this.blockId;
        else
            return false;
    }

    public void tick() {}
}
