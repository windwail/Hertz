package checkmobile.de.hertz.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import checkmobile.de.hertz.CaptureFuelActivity;
import checkmobile.de.hertz.CaptureMileageActivity;
import checkmobile.de.hertz.DamageActivity;
import checkmobile.de.hertz.EmptyActivity;
import checkmobile.de.hertz.SelectableListActivity;
import checkmobile.de.hertz.TyrePressure;
import checkmobile.de.hertz.domain.CMProcess;
import checkmobile.de.hertz.entity.Process;

/**
 * Created by icetusk on 23.05.16.
 */
public class ProcessesHelper {

    public static final String VEHICLE_ACCESSORIES = "Vehicle accessories";
    public static final String REMOVED_ACCESSORIES = "Removed accessories";
    public static final String MECHANICAL_FAULTS = "Mechanical faults";
    public static final String TYRE_PRESSURE = "Tyre pressure";
    public static final String CAPTURE_MILEAGE = "Capture mileage";
    public static final String CAPTURE_FUEL = "Capture fuel";
    public static final String OVERVIEW_PHOTOS = "Overview photos";
    public static final String PARKING = "Parking";
    public static final String DAMAGE = "Damage";
    public static final String CAR_INFO = "Car information";
    public static final Map<String, Class> mapping = new HashMap<>();
    private static final List<CMProcess> list = new ArrayList<>();

    static {
        list.add(new CMProcess(VEHICLE_ACCESSORIES, SelectableListActivity.class, new AccessoriesHelper()));
        list.add(new CMProcess(REMOVED_ACCESSORIES, SelectableListActivity.class, new AccessoriesHelper()));
        list.add(new CMProcess(MECHANICAL_FAULTS, SelectableListActivity.class, new AccessoriesHelper()));
        list.add(new CMProcess(TYRE_PRESSURE, TyrePressure.class));
        list.add(new CMProcess(CAPTURE_MILEAGE, CaptureMileageActivity.class));
        list.add(new CMProcess(CAPTURE_FUEL, CaptureFuelActivity.class));
        list.add(new CMProcess(OVERVIEW_PHOTOS, EmptyActivity.class));
        list.add(new CMProcess(PARKING, EmptyActivity.class));
        list.add(new CMProcess(DAMAGE, DamageActivity.class));

        list.get(0).setFinished(true);
        list.get(0).setRequired(true);

        mapping.put(VEHICLE_ACCESSORIES, SelectableListActivity.class);
        mapping.put(REMOVED_ACCESSORIES, SelectableListActivity.class);
        mapping.put(MECHANICAL_FAULTS, SelectableListActivity.class);
        mapping.put(TYRE_PRESSURE, TyrePressure.class);
        mapping.put(CAPTURE_MILEAGE, CaptureMileageActivity.class);
        mapping.put(CAPTURE_FUEL, CaptureFuelActivity.class);
        mapping.put(OVERVIEW_PHOTOS, EmptyActivity.class);
        mapping.put(PARKING, EmptyActivity.class);
        mapping.put(DAMAGE, DamageActivity.class);
        mapping.put(CAR_INFO, EmptyActivity.class);
    }


    public static List<CMProcess> getList() {
        return list;
    }

    public static Map<String, Class> getMapping() {
        return mapping;
    }


    /**
     * Create bunch of Process instances for each car;
     * @return
     */
    public static List<Process> getInfleetProcessesForCar() {
        List<Process> list = new ArrayList<>();
        list.add(new Process(CAR_INFO));
        list.add(new Process(CAPTURE_MILEAGE));
        list.add(new Process(CAPTURE_FUEL));
        list.add(new Process(DAMAGE));
        return list;
    }


}
