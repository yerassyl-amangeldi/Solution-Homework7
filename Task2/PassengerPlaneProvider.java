public class PassengerPlaneProvider implements AircraftProvider {
    @Override
    public Aircraft createAircraft(String id, int fuelLevel) {
        return new PassengerPlane(id, fuelLevel);
        //provide passenger plane
    }
}