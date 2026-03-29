package analysis;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.LinkLeaveEvent;
import org.matsim.api.core.v01.events.handler.LinkLeaveEventHandler;
import org.matsim.api.core.v01.network.Link;

public class HourlyLinkVolumeHandler implements LinkLeaveEventHandler {

    private final Id<Link> targetLinkId;
    private final int[] hourlyVolumes = new int[24];

    public HourlyLinkVolumeHandler(Id<Link> targetLinkId) {
        this.targetLinkId = targetLinkId;
    }

    @Override
    public void handleEvent(LinkLeaveEvent event) {
        if (event.getLinkId().equals(targetLinkId)) {
            int hour = (int) event.getTime() / 3600;

            if (hour >= 0 && hour < 24) {
                hourlyVolumes[hour]++;
            }
        }
    }

    public int[] getHourlyVolumes() {
        return hourlyVolumes;
    }
}