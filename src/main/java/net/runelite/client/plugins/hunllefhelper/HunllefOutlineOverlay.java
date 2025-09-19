package net.runelite.client.plugins.hunllefhelper;

import net.runelite.api.Client;
import net.runelite.api.HeadIcon;
import net.runelite.api.NPC;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;

import javax.inject.Inject;
import java.awt.*;

public class HunllefOutlineOverlay extends Overlay
{
    private final Client client;
    private final HunllefHelperPlugin plugin;
    private final ModelOutlineRenderer modelOutlineRenderer;

    @Inject
    public HunllefOutlineOverlay(Client client, HunllefHelperPlugin plugin, ModelOutlineRenderer modelOutlineRenderer)
    {
        this.client = client;
        this.plugin = plugin;
        this.modelOutlineRenderer = modelOutlineRenderer;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        NPC hunllef = plugin.getHunllef();
        HeadIcon protection = plugin.getHunllefProtection();

        if (hunllef != null && protection != null)
        {
            Color outlineColor;

            switch (protection)
            {
                case MAGIC: outlineColor = Color.BLUE; break;
                case RANGED: outlineColor = Color.GREEN; break;
                case MELEE: outlineColor = Color.RED; break;
                default: outlineColor = Color.WHITE;
            }

            modelOutlineRenderer.drawOutline(hunllef, 2, outlineColor, 100);
        }

        return null;
    }
}