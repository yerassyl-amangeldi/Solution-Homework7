public class Main {
    public static void main(String[] args) {
        ControlTower tower = new ControlTower();
        Aircraft p1 = new PassengerPlane("PS123", 15); // low fuel
        Aircraft c1 = new CargoPlane("CG456", 60);
        Aircraft h1 = new Helicopter("HC789", 50);

        p1.send("Request LAND", tower); // low fuel priority
        c1.send("Request TAKEOFF", tower);
        h1.send("Request MAYDAY", tower); // emergency

        tower.releaseRunway();
        tower.releaseRunway();
        //manual test
    }
}