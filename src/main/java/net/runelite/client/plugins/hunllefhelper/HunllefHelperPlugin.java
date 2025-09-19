package net.runelite.client.plugins.hunllefhelper;

import net.runelite.api.Client;
import net.runelite.api.HeadIcon;
import net.runelite.api.NPC;
import net.runelite.api.Prayer;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@PluginDescriptor(
    name = "Hunllef Helper",
    description = "Draws an outline over Hunllef based on its attack style and flashes red if the player is not protected correctly"
)
public class HunllefHelperPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private HunllefOutlineOverlay hunllefOutlineOverlay;

    @Inject
    private PrayerFlashOverlay prayerFlashOverlay;

    private NPC hunllef;

    private static final int HUNLLEF_ID = 9023;           // Regular Hunllef
    private static final int CORRUPTED_HUNLLEF_ID = 9035; // Corrupted Hunllef

    @Override
    protected void startUp()
    {
        overlayManager.add(hunllefOutlineOverlay);
        overlayManager.add(prayerFlashOverlay);
        System.out.println("✅ Hunllef Helper loaded");
    }

    @Override
    protected void shutDown()
    {
        overlayManager.remove(hunllefOutlineOverlay);
        overlayManager.remove(prayerFlashOverlay);
        System.out.println("🛑 Hunllef Helper unloaded");
    }

    public NPC getHunllef()
    {
        if (hunllef == null || !isHunllef(hunllef))
        {
            for (NPC npc : client.getNpcs())
            {
                if (isHunllef(npc))
                {
                    hunllef = npc;
                    break;
                }
            }
        }

        return hunllef;
    }

    public HeadIcon getHunllefProtection()
    {
        NPC currentHunllef = getHunllef();
        HeadIcon protection = detectProtection(currentHunllef);

        if (protection != null && !isPlayerProtected(protection))
        {
            prayerFlashOverlay.triggerFlash();
        }

        return protection;
    }

    private boolean isHunllef(NPC npc)
    {
        if (npc == null)
        {
            return false;
        }

        int id = npc.getId();
        return id == HUNLLEF_ID || id == CORRUPTED_HUNLLEF_ID;
    }

    private HeadIcon detectProtection(NPC npc)
    {
        if (npc == null)
        {
            return null;
        }

        int animationId = npc.getAnimation();
        switch (animationId)
        {
            case 8419: return HeadIcon.MAGIC;
            case 8418: return HeadIcon.RANGED;
            case 8417: return HeadIcon.MELEE;
            default: return null;
        }
    }

    private boolean isPlayerProtected(HeadIcon protection)
    {
        switch (protection)
        {
            case MAGIC:
                return client.isPrayerActive(Prayer.PROTECT_FROM_MAGIC);
            case RANGED:
                return client.isPrayerActive(Prayer.PROTECT_FROM_MISSILES);
            case MELEE:
                return client.isPrayerActive(Prayer.PROTECT_FROM_MELEE);
            default:
                return false;
        }
    }
}
