public class HelicopterProvider implements AircraftProvider {
    @Override
    public Aircraft createAircraft(String id, int fuelLevel) {
        return new Helicopter(id, fuelLevel);
        //provide helicopter
    }
}