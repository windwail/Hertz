package checkmobile.de.hertz.entity;

import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import checkmobile.de.hertz.dao.ProcessDao;
import checkmobile.de.hertz.gson.Damage;
import checkmobile.de.hertz.gson.GsonHelper;
import lombok.Getter;
import lombok.Setter;

@DatabaseTable(tableName = "process", daoClass = ProcessDao.class)
public class Process implements Serializable {

    public enum Type {
        VEHICLE_ACCESSORIES,
        REMOVED_ACCESSORIES,
        MECHANICAL_FAULTS,
        TYRE_PRESSURE,
        CAPTURE_MILEAGE,
        CAPTURE_FUEL,
        OVERVIEW_PHOTOS,
        PARKING,
        DAMAGE,
        CAR_INFO
    }

    public Process() {
    }


    //Database Field for Table Student
    @DatabaseField(generatedId = true)
    @Getter
    @Setter
    private int id;

    @DatabaseField(dataType = DataType.ENUM_STRING, canBeNull = false)
    @Getter
    @Setter
    private Type type;

    @DatabaseField(dataType = DataType.DATE_TIME)
    @Getter
    @Setter
    private DateTime createDate;

    @DatabaseField
    @Getter
    @Setter
    String variablesGson;

    @DatabaseField
    @Getter
    @Setter
    private boolean finished;

    @DatabaseField
    @Getter
    @Setter
    private boolean mandatory;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "parentGroup")
    @Getter
    @Setter
    private ProcessGroup parent;

    @ForeignCollectionField
    @Getter
    @Setter
    Collection<Image> images = new ArrayList<>();

    public List<Damage> getDamages() {
        ArrayList<Damage> damages;
        java.lang.reflect.Type listType = new TypeToken<List<Damage>>() {}.getType();
        damages = GsonHelper.getBuilder().fromJson(getVariablesGson(), listType);

        if (damages == null) {
            damages = new ArrayList<>();
        }

        return damages;
    }

    public void setDamages(List<Damage> damages) {
        setVariablesGson(GsonHelper.getBuilder().toJson(damages));
    }

    public void updateDamage(Damage damage) {

    }

    public void addDamage(Damage d) {
        List<Damage> damages = getDamages();
        damages.add(d);
        setVariablesGson(GsonHelper.getBuilder().toJson(damages));
    }

}
