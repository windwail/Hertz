package checkmobile.de.hertz.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import checkmobile.de.hertz.domain.CMListElement;

/**
 * Created by icetusk on 25.05.16.
 */
public abstract class CMListHelper implements Serializable {

    protected List<CMListElement> list = new ArrayList<>();

    public List<CMListElement> getList() {
        return list;
    }

    protected abstract void initList();

    public CMListHelper() {
        initList();
    }
}
