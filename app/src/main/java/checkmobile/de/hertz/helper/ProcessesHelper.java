package checkmobile.de.hertz.helper;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;

import checkmobile.de.hertz.CaptureFuelActivity;
import checkmobile.de.hertz.CaptureMileageActivity;
import checkmobile.de.hertz.CarInfoInfleetActivity_;
import checkmobile.de.hertz.DamageListActivity;
import checkmobile.de.hertz.EmptyActivity;
import checkmobile.de.hertz.PhotoListActivity;
import checkmobile.de.hertz.SelectableListActivity;
import checkmobile.de.hertz.TyrePressure;
import checkmobile.de.hertz.entity.Process;

/**
 * Created by icetusk on 23.05.16.
 */
public class ProcessesHelper {

    public static final String PROCESS_ID = "process_id";

    public static final String CAR_INDEX = "car_index";

    public static final String CAR_COUNT = "car_count";

    public static final String PROCESS_GROUP_ID = "process_group_id";

    public static final String DAMAGE_ID = "damage_id";



    public static final Map<Process.Type, Class<? extends Activity>> mapping = new HashMap<>();

    static {
        mapping.put(Process.Type.VEHICLE_ACCESSORIES, SelectableListActivity.class);
        mapping.put(Process.Type.REMOVED_ACCESSORIES, SelectableListActivity.class);
        mapping.put(Process.Type.MECHANICAL_FAULTS, SelectableListActivity.class);
        mapping.put(Process.Type.TYRE_PRESSURE, TyrePressure.class);
        mapping.put(Process.Type.CAPTURE_MILEAGE, CaptureMileageActivity.class);
        mapping.put(Process.Type.CAPTURE_FUEL, CaptureFuelActivity.class);
        mapping.put(Process.Type.OVERVIEW_PHOTOS, PhotoListActivity.class);
        mapping.put(Process.Type.PARKING, EmptyActivity.class);
        mapping.put(Process.Type.DAMAGE, DamageListActivity.class);
        mapping.put(Process.Type.CAR_INFO, CarInfoInfleetActivity_.class);
    }


    public static Map<Process.Type, Class<? extends Activity>> getMapping() {
        return mapping;
    }

    public static Class<? extends Activity> getActivityClass(Process p) {
        return mapping.get(p.getType());
    }


}
