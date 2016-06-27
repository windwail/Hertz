package checkmobile.de.hertz.gson;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by icetusk on 27.06.16.
 */
@Getter
@Setter
public class Damage extends CMGson {

    private DateTime registerDate;

    private String comment;

    private String area;

    private String piece;

    private String type;

    private String severity;

    private String[] images;
}
