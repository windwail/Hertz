package checkmobile.de.hertz.helper;

import checkmobile.de.hertz.domain.CMStation;

/**
 * Created by icetusk on 09.06.16.
 */
public class StationHelper extends CMListHelper {
    @Override
    protected void initList() {
        list.add(new CMStation("UKLHR50"));
        list.add(new CMStation("UKSMS50"));
        list.add(new CMStation("UKTest"));
    }
}
