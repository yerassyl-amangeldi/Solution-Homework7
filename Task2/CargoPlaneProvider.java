public class CargoPlaneProvider implements AircraftProvider {
    @Override
    public Aircraft createAircraft(String id, int fuelLevel) {
        return new CargoPlane(id, fuelLevel);
        //provide cargo plane
    }
}