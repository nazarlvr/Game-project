package render;

import block.Block;
import block.BlockGrass;
import block.BlockSlab;
import entity.*;
import entity.particle.EntityParticle;
import render.block.RenderBlock;
import render.block.RenderGrass;
import render.block.RenderSlab;
import render.entity.RenderEntity;
import render.entity.RenderParticle;
import render.entity.RenderPlayer;
import render.entity.RenderSlime;
import render.entity.RenderItem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RenderManager
{
    public static final String texture_path_entity = "textures/entities/";
    public static final String texture_path_block = "textures/blocks/";
    public static final String texture_path_item = "textures/items/";

    public static RenderBlock getRender(Block b)
    {
        if (b == null)
            return null;

        if (b instanceof BlockGrass)
            return new RenderGrass(b);
        else if (b instanceof BlockSlab)
            return new RenderSlab(b);
        else
            return new RenderBlock(b);
    }

    public static RenderEntity getRender(Entity e)
    {
        if (e == null)
            return null;

        if (e instanceof EntitySlime)
            return new RenderSlime(e);
        else if (e instanceof EntityPlayer)
            return new RenderPlayer(e);
        else if (e instanceof EntityParticle)
            return new RenderParticle(e);
        else if (e instanceof EntityItem)
            return new RenderItem((EntityItem) e);
        else
            return new RenderEntity(e);
    }

    public static BufferedImage loadTexture(String filepath)
    {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public static BufferedImage loadBlockTexture(String filepath)
    {
        return loadTexture(texture_path_block + filepath);
    }

    public static BufferedImage loadEntityTexture(String filepath)
    {
        return loadTexture(texture_path_entity + filepath);
    }

    public static BufferedImage loadItemTexture(String filepath)
    {
        return loadTexture(texture_path_item + filepath);
    }


    public static final BufferedImage texture_item_slot = loadTexture("textures/gui/slot.png");
    public static final BufferedImage texture_gui_hotbar = loadTexture("textures/gui/Bar.png");
    public static final BufferedImage texture_active_slot = loadTexture("textures/gui/ActiveSlot.png");
    public static final BufferedImage texture_gui_chest = loadTexture("textures/gui/inventory_9x8.png");
    public static final BufferedImage texture_gui_inventory_player = loadTexture("textures/gui/inventory_9x4.png");
    public static final BufferedImage texture_icon_heart_empty = loadTexture("textures/gui/icon/heart_empty.png");
    public static final BufferedImage texture_icon_heart_full = loadTexture("textures/gui/icon/heart_full.png");
    public static final Map<String, BufferedImage[]> texture_particle = Map.ofEntries(
            Map.entry("poof", new BufferedImage[]{
                    loadEntityTexture("particle/poof/poof_0.png"),
                    loadEntityTexture("particle/poof/poof_1.png"),
                    loadEntityTexture("particle/poof/poof_2.png"),
                    loadEntityTexture("particle/poof/poof_3.png"),
                    loadEntityTexture("particle/poof/poof_4.png"),
                    loadEntityTexture("particle/poof/poof_5.png"),
                    loadEntityTexture("particle/poof/poof_6.png"),
                    loadEntityTexture("particle/poof/poof_7.png")
            })
    );
}
