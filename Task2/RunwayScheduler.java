import java.util.*;
public interface RunwayScheduler {
    Aircraft scheduleRunway(LinkedList<Aircraft> landingQueue, LinkedList<Aircraft> takeoffQueue);
    //pick next aircraft
}