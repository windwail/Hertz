package checkmobile.de.hertz.domain;

/**
 * Created by icetusk on 20.05.16.
 */
public class Car {

    private String unit;
    private String plate;
    private String vin;

    public Car(String plate, String unit, String vin) {
        this.plate = plate;
        this.unit = unit;
        this.vin = vin;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return plate + '\n' +  unit + '\n' + vin;
    }
}
