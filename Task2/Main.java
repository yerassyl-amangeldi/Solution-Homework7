import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ControlTower tower = new ControlTower();
        Random rand = new Random();
        Aircraft[] aircraft = new Aircraft[10];


        //spawn 10 random aircraft
        for (int i = 0; i < 10; i++) {
            int fuel = rand.nextInt(100) + 1;
            int type = rand.nextInt(3);
            String id = "AC" + i;
            if (type == 0) aircraft[i] = new PassengerPlane(id, fuel);
            else if (type == 1) aircraft[i] = new CargoPlane(id, fuel);
            else aircraft[i] = new Helicopter(id, fuel);
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        for (Aircraft a : aircraft) {
            scheduler.scheduleAtFixedRate(() -> {
                String action = rand.nextInt(10) < 8 ? "LAND" : "TAKEOFF";
                if (rand.nextInt(100) < 5) action = "MAYDAY"; //5% chance
                a.send("Request " + action, tower);
                tower.releaseRunway();
            }, rand.nextInt(5), 1, TimeUnit.SECONDS);
        }

        //run for 10 seconds
        try {
            Thread.sleep(10000);
            scheduler.shutdown();
            scheduler.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //simulate traffic
    }
}