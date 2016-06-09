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

public class StationAdapter extends ArrayAdapter {

    List list=new ArrayList();

    public StationAdapter(Context context, int resource) {
        super(context, resource);

        list.add("Station 1");
        list.add("Station 2");
        list.add("Station 3");

    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    public void add(Object object){
        super.add(object);
        list.add(object);
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
            row=inflater.inflate(R.layout.list_element,parent,false);
            handler=new DataHandler();
            handler.title=(TextView)row.findViewById(R.id.menuButton);
            row.setTag(handler);
        }
        else {
            handler=(DataHandler)row.getTag();
        }

        handler.title.setText(list.get(position).toString());

        return row;
    }
}
