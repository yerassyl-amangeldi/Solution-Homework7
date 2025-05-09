import java.util.LinkedList;

public class FuelPriorityScheduler implements RunwayScheduler {
    @Override
    public Aircraft scheduleRunway(LinkedList<Aircraft> landingQueue, LinkedList<Aircraft> takeoffQueue) {
        Aircraft lowestFuel = null;
        int minFuel = Integer.MAX_VALUE;

        for (Aircraft a : landingQueue) {
            if (a.getFuelLevel() < minFuel) {
                minFuel = a.getFuelLevel();
                lowestFuel = a;
            }
        }
        if (lowestFuel != null) {
            landingQueue.remove(lowestFuel);
            return lowestFuel;
        }
        if (!takeoffQueue.isEmpty()) return takeoffQueue.poll();
        return null;
        //lowest fuel first
    }
}