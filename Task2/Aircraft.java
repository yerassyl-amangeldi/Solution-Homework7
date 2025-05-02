public abstract class Aircraft {
    protected String id;
    protected int fuelLevel;

    public Aircraft(String id, int fuelLevel) {
        this.id = id;
        this.fuelLevel = fuelLevel;
    }

    public abstract void receive(String msg);
    public void send(String msg, TowerMediator m) {
        m.broadcast(msg, this);
        m.requestRunway(this, msg);
        //send via tower
    }

    public String getId() {
        return id;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }
}