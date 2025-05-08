import java.util.LinkedList;
import java.util.Queue;

public class ControlTower implements TowerMediator {
    private Queue<Aircraft> landingQueue = new LinkedList<>();
    private Queue<Aircraft> takeoffQueue = new LinkedList<>();
    private boolean runwayOccupied = false;
    private boolean emergencyMode = false;

    @Override
    public void broadcast(String msg, Aircraft sender) {
        System.out.println("Tower: [" + sender.getId() + "] says: " + msg);
        for (Aircraft a : landingQueue) {
            if (a != sender) a.receive(msg);
        }
        for (Aircraft a : takeoffQueue) {
            if (a != sender) a.receive(msg);
        }
    }

    @Override
    public boolean requestRunway(Aircraft a) {
        if (msg.contains("MAYDAY")) {
            handleEmergency(a);
            return true;
        }
        if (msg.contains("LAND")) {
            if (a.getFuelLevel() < 20) {
                landingQueue.add(0, a); //low fuel priority
                broadcast("Low fuel priority for " + a.getId(), this);
            } else {
                landingQueue.add(a);
            }
        } else if (msg.contains("TAKEOFF")) {
            takeoffQueue.add(a);
        }
        return tryGrantRunway();
    }

    private void handleEmergency(Aircraft a) {
        emergencyMode = true;
        runwayOccupied = true;
        landingQueue.clear();
        takeoffQueue.clear();
        broadcast("MAYDAY! All aircraft hold position", this);
        System.out.println("Tower: " + a.getId() + " cleared for emergency landing");
        //mayday gets runway
    }

    private boolean tryGrantRunway() {
        if (runwayOccupied || emergencyMode) return false;
        if (!landingQueue.isEmpty()) {
            Aircraft a = landingQueue.poll();
            runwayOccupied = true;
            System.out.println("Tower: " + a.getId() + " cleared to land");
            return true;
        }
        if (!takeoffQueue.isEmpty()) {
            Aircraft a = takeoffQueue.poll();
            runwayOccupied = true;
            System.out.println("Tower: " + a.getId() + " cleared to takeoff");
            return true;
        }
        return false;
        //fifo with priority
    }

    public void releaseRunway() {
        runwayOccupied = false;
        emergencyMode = false;
        tryGrantRunway();
        //reset for next
    }
}