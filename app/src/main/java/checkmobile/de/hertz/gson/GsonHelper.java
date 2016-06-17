package checkmobile.de.hertz.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.joda.time.DateTime;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by icetusk on 10.06.16.
 */
public class GsonHelper {

    public static Gson getBuilder() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        /*
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();
                */

        final GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

            final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                try {
                    return df.parse(json.getAsString());
                } catch (final java.text.ParseException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });

        builder.registerTypeAdapter(DateTime.class, new JsonDeserializer<DateTime>() {

            final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            @Override
            public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                try {
                    return new DateTime(df.parse(json.getAsString()));
                } catch (final java.text.ParseException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });

        return builder.create();
    }
}
