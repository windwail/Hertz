package checkmobile.de.hertz.gson;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by icetusk on 10.06.16.
 */
public class GsonInfleetStart extends CMGson{

    @Getter
    @Setter
    String deliveryNoteNo;

    @Getter
    @Setter
    String truckLicensePlate;

    @Getter
    @Setter
    String driverName;

    @Getter
    @Setter
    String driversEmail;

    @Getter
    @Setter
    Integer numberOfCars;

    @Getter
    @Setter
    String generalCondition;

}
