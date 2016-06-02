package checkmobile.de.hertz.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import checkmobile.de.hertz.R;
import checkmobile.de.hertz.domain.CMProcess;
import checkmobile.de.hertz.helper.ProcessesHelper;


/**
 * Created by icetusk on 23.05.16.
 */
public class ProcessesAdapter extends ArrayAdapter {

    public ProcessesAdapter(Context context, int resource) {
        super(context, resource);

    }

    @Override
    public Object getItem(int position) {
        return ProcessesHelper.getList().get(position);
    }

    public int getCount(){
        return ProcessesHelper.getList().size();
    }

    static class DataHandler
    {
        TextView title;

        ImageView doneIcon;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;

        CMProcess process = ProcessesHelper.getList().get(position);

        DataHandler handler;
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.process_element,parent,false);
            handler=new DataHandler();
            handler.title=(TextView)row.findViewById(R.id.process);
            //handler.doneIcon= (ImageView)row.findViewById(R.id.doneIcon);
            row.setTag(handler);
        }
        else {
            handler=(DataHandler)row.getTag();
        }

        handler.title.setText(process.getName());

        if(process.isRequired()) {
            handler.title.setTypeface(null, Typeface.BOLD);
        }

        if(process.isFinished()) {
            //handler.doneIcon.setVisibility(process.isFinished() ? View.VISIBLE : View.INVISIBLE);
            row.setBackgroundColor(Color.parseColor("#6f99cc00"));
        }




        return row;
    }
}
