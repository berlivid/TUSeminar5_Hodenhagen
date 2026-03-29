package analysis;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.core.events.EventsUtils;

public class StartAnalysis {

    public static void main(String[] args) {

        Id<Link> targetLinkId = Id.createLinkId("1325764750000f");

        var handler = new HourlyLinkVolumeHandler(targetLinkId);
        var manager = EventsUtils.createEventsManager();

        manager.addHandler(handler);

        EventsUtils.readEvents(
                manager,
                "/Users/david/Desktop/TUM/Masterarbeit/WP2/TU Seminar 5/scenarios/serengeti-park-v1.0/output/output-serengeti-park-v1.0-run1/serengeti-park-v1.0-run1.output_events.xml.gz"
        );

        int[] volumes = handler.getHourlyVolumes();

        System.out.println("Hourly link volumes for link " + targetLinkId + ":");
        for (int hour = 0; hour < volumes.length; hour++) {
            System.out.println("Hour " + hour + ": " + volumes[hour]);
        }
    }
}