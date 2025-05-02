public interface TowerMediator {
    void broadcast(String msg, Aircraft sender);
    boolean requestRunway(Aircraft a, String msg);
    //defines tower comms
}