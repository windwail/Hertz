package checkmobile.de.hertz.domain;

import java.io.Serializable;

/**
 * Created by icetusk on 25.05.16.
 */
public abstract class CMListElement implements Serializable {

    private String name;

    private boolean selected;

    public CMListElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
