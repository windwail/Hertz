package checkmobile.de.hertz.helper;

import java.util.ArrayList;
import java.util.List;

import checkmobile.de.hertz.DamageActivity;
import checkmobile.de.hertz.domain.CMAccessory;
import checkmobile.de.hertz.domain.CMProcess;

/**
 * Created by icetusk on 25.05.16.
 */
public class AccessoriesHelper extends CMListHelper {

    @Override
    protected void initList() {
        list.add(new CMAccessory("CD disk"));
        list.add(new CMAccessory("Navigation"));
        list.add(new CMAccessory("Wheel"));
        list.add(new CMAccessory("..."));
        list.add(new CMAccessory("..."));
        list.add(new CMAccessory("..."));
        list.add(new CMAccessory("..."));
        list.add(new CMAccessory("..."));
        list.add(new CMAccessory("..."));
        list.add(new CMAccessory("..."));
        list.add(new CMAccessory("..."));
        list.add(new CMAccessory("..."));
        list.add(new CMAccessory("..."));
        list.add(new CMAccessory("..."));
        list.add(new CMAccessory("..."));
        list.add(new CMAccessory("..."));
        list.add(new CMAccessory("..."));
        list.add(new CMAccessory("..."));
        list.add(new CMAccessory("..."));
    }

}
