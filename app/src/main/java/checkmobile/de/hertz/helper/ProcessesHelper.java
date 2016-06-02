package checkmobile.de.hertz.helper;

import java.util.ArrayList;
import java.util.List;

import checkmobile.de.hertz.CaptureFuelActivity;
import checkmobile.de.hertz.CaptureMileageActivity;
import checkmobile.de.hertz.DamageActivity;
import checkmobile.de.hertz.EmptyActivity;
import checkmobile.de.hertz.SelectableListActivity;
import checkmobile.de.hertz.TyrePressure;
import checkmobile.de.hertz.domain.CMProcess;

/**
 * Created by icetusk on 23.05.16.
 */
public class ProcessesHelper {

    private static List<CMProcess> list=new ArrayList<>();

    static {
        list.add(new CMProcess("Vehicle accessories", SelectableListActivity.class, new AccessoriesHelper()));
        list.add(new CMProcess("Removed accessories", SelectableListActivity.class, new AccessoriesHelper()));
        list.add(new CMProcess("Mechanical faults", SelectableListActivity.class, new AccessoriesHelper()));
        list.add(new CMProcess("Tyre pressure", TyrePressure.class));
        list.add(new CMProcess("Capture mileage", CaptureMileageActivity.class));
        list.add(new CMProcess("Capture fuel", CaptureFuelActivity.class));
        list.add(new CMProcess("Overview photos", EmptyActivity.class));
        list.add(new CMProcess("Parking", EmptyActivity.class));
        list.add(new CMProcess("Damage", DamageActivity.class));

        list.get(0).setFinished(true);
        list.get(0).setRequired(true);
    }

    public static List<CMProcess> getList() {
        return list;
    }

}
