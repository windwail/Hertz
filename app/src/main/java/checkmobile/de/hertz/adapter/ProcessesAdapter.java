package checkmobile.de.hertz.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import checkmobile.de.hertz.R;
import checkmobile.de.hertz.entity.Process;


/**
 * Created by icetusk on 23.05.16.
 */
public class ProcessesAdapter extends ArrayAdapter<Process> {

    List<Process> processes;

    Context context;

    public ProcessesAdapter(Context context, int resource , List<Process> processes) {
        super(context, resource);
        this.processes = processes;
        this.context = context;
    }

    @Override
    public Process getItem(int position) {
        return processes.get(position);
    }

    public int getCount(){
        return processes.size();
    }

    static class DataHandler
    {
        TextView title;
        ImageView imageIcon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;

        Process process = processes.get(position);

        DataHandler handler;
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.process_element,parent,false);
            handler=new DataHandler();
            handler.title=(TextView)row.findViewById(R.id.process);
            handler.imageIcon= (ImageView)row.findViewById(R.id.imageIcon);
            row.setTag(handler);
        }
        else {
            handler=(DataHandler)row.getTag();
        }

        handler.title.setText(process.getType().toString());

        if(process.isMandatory()) {
           // handler.title.setTypeface(null, Typeface.BOLD);
        }

        if(process.isFinished()) {
            //handler.doneIcon.setVisibility(process.isFinished() ? View.VISIBLE : View.INVISIBLE);
            row.setBackgroundColor(Color.parseColor("#4299cc00"));

            handler.imageIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_ok) );
        } else{
            handler.imageIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_empty) );
        }




        return row;
    }
}
