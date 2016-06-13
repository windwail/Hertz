package checkmobile.de.hertz.helper;

import java.util.HashMap;
import java.util.Map;

import checkmobile.de.hertz.CaptureFuelActivity;
import checkmobile.de.hertz.CaptureMileageActivity;
import checkmobile.de.hertz.DamageActivity;
import checkmobile.de.hertz.EmptyActivity;
import checkmobile.de.hertz.SelectableListActivity;
import checkmobile.de.hertz.TyrePressure;
import checkmobile.de.hertz.entity.Process;

/**
 * Created by icetusk on 23.05.16.
 */
public class ProcessesHelper {

    public static final Map<Process.Type, Class> mapping = new HashMap<>();

    static {
        mapping.put(Process.Type.VEHICLE_ACCESSORIES, SelectableListActivity.class);
        mapping.put(Process.Type.REMOVED_ACCESSORIES, SelectableListActivity.class);
        mapping.put(Process.Type.MECHANICAL_FAULTS, SelectableListActivity.class);
        mapping.put(Process.Type.TYRE_PRESSURE, TyrePressure.class);
        mapping.put(Process.Type.CAPTURE_MILEAGE, CaptureMileageActivity.class);
        mapping.put(Process.Type.CAPTURE_FUEL, CaptureFuelActivity.class);
        mapping.put(Process.Type.OVERVIEW_PHOTOS, EmptyActivity.class);
        mapping.put(Process.Type.PARKING, EmptyActivity.class);
        mapping.put(Process.Type.DAMAGE, DamageActivity.class);
        mapping.put(Process.Type.CAR_INFO, EmptyActivity.class);
    }


    public static Map<Process.Type, Class> getMapping() {
        return mapping;
    }


}
