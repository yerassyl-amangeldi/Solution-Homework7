import java.util.LinkedList;

public class FifoScheduler implements RunwayScheduler {
    @Override
    public Aircraft scheduleRunway(LinkedList<Aircraft> landingQueue, LinkedList<Aircraft> takeoffQueue) {
        if (!landingQueue.isEmpty()) return landingQueue.poll();
        if (!takeoffQueue.isEmpty()) return takeoffQueue.poll();
        return null;
        //fifo order
    }
}