package checkmobile.de.hertz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import checkmobile.de.hertz.R;
import checkmobile.de.hertz.domain.Car;

/**
 * Created by icetusk on 20.05.16.
 */
public class CarsAdapter extends ArrayAdapter {

    List<Car> list=new ArrayList<>();


    public CarsAdapter(Context context, int resource) {
        super(context, resource);


        list.add(new Car("753 UYM", "15547", "KMHDM46D65U174710"));
        list.add(new Car("8 CHR", "3435", "JNRAR05Y2VW340926"));
        list.add(new Car("550 MPC", "1235", "JD1JG1127N4408162"));
        list.add(new Car("JD 8645", "436", "TRU4FAFK1C1094425"));
        list.add(new Car("TS 9762", "23467", "2C3LK53G96H100877"));
        list.add(new Car(" OIG 7652", "6353", "1HTSMABM01H356764"));

    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    public int getCount(){
        return this.list.size();
    }

    static class DataHandler
    {
        TextView title;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;

        DataHandler handler;
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.car_element,parent,false);
            handler=new DataHandler();
            handler.title=(TextView)row.findViewById(R.id.carTextView);
            row.setTag(handler);
        }
        else {
            handler=(DataHandler)row.getTag();
        }

        handler.title.setText(list.get(position).toString());

        return row;
    }
}
