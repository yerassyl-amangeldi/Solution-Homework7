import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Dashboard extends JFrame {
    private JLabel runwayStatus;
    private JLabel landingQueueSize;
    private JLabel takeoffQueueSize;
    private JTextArea log;

    public Dashboard(ControlTower tower) {
        setTitle("Airport Tower Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        runwayStatus = new JLabel("Runway: Free");
        landingQueueSize = new JLabel("Landing Queue: 0");
        takeoffQueueSize = new JLabel("Takeoff Queue: 0");
        log = new JTextArea(10, 30);
        log.setEditable(false);

        add(runwayStatus);
        add(landingQueueSize);
        add(takeoffQueueSize);
        add(new JScrollPane(log));

        // Обновление каждую секунду
        new Thread(() -> {
            while (true) {
                update(tower.isRunwayOccupied(), tower.getLandingQueue(), tower.getTakeoffQueue(), "");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        setVisible(true);
        //basic ui
    }

    public void update(boolean isOccupied, LinkedList<Aircraft> landingQueue, LinkedList<Aircraft> takeoffQueue, String msg) {
        runwayStatus.setText("Runway: " + (isOccupied ? "Occupied" : "Free"));
        landingQueueSize.setText("Landing Queue: " + landingQueue.size());
        takeoffQueueSize.setText("Takeoff Queue: " + takeoffQueue.size());
        if (!msg.isEmpty()) {
            log.append(msg + "\n");
        }
        //refresh display
    }
}