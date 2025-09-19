package net.runelite.client.plugins.hunllefhelper;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;

public class PrayerFlashOverlay extends Overlay
{
    private final Client client;
    private boolean flashActive = false;

    @Inject
    public PrayerFlashOverlay(Client client)
    {
        this.client = client;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    public void triggerFlash()
    {
        flashActive = true;
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (flashActive)
        {
            graphics.setColor(new Color(255, 0, 0, 128));
            graphics.fillRect(0, 0, client.getCanvas().getWidth(), client.getCanvas().getHeight());
            flashActive = false;
        }

        return null;
    }
}