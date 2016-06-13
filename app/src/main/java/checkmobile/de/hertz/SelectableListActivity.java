package checkmobile.de.hertz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import checkmobile.de.hertz.domain.CMListElement;
import checkmobile.de.hertz.domain.Car;
import checkmobile.de.hertz.helper.CMListHelper;

public class SelectableListActivity extends AppCompatActivity {

    public static String HELPER_EXTRA = "helper_extra";

    CMListHelper helper;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectable_list);

        helper = (CMListHelper) getIntent().getSerializableExtra(HELPER_EXTRA);

        listView=(ListView)findViewById(R.id.list_view);

        SelectableListAdapter adapter = new SelectableListAdapter(getApplicationContext(), R.layout.layout_selectable_element);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //Intent myIntent = new Intent(view.getContext(), CarInfoOverviewActivity.class);
                //startActivity(myIntent);
            }
        });
    }


    private class SelectableListAdapter extends ArrayAdapter {

        public SelectableListAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public Object getItem(int position) {
            return helper.getList().get(position);
        }

        public int getCount(){
            return helper.getList().size();
        }

        class DataHandler
        {
            TextView title;
        }

        @Override
        public View getView(final int position, View row, ViewGroup parent) {

            final CMListElement element = helper.getList().get(position);

            DataHandler handler;
            if(row==null)
            {
                LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row=inflater.inflate(R.layout.layout_selectable_element,parent,false);
                handler=new DataHandler();
                handler.title=(TextView)row.findViewById(R.id.elementLabel);



                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        element.setSelected(!element.isSelected());
                        v.setActivated(element.isSelected());
                    }
                });

                row.setTag(handler);
            }
            else {
                handler=(DataHandler)row.getTag();
            }

            if(element.isSelected()) {
                row.setActivated(true);
            } else {
                row.setActivated(false);
            }


            handler.title.setText(helper.getList().get(position).getName());

            return row;
        }
    }
}
