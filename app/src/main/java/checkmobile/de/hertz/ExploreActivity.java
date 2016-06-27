package checkmobile.de.hertz;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;

import checkmobile.de.hertz.activity.CMActivity;

public class ExploreActivity extends ListActivity {
    private TextView selection;
    private static final String[] items={"lorem", "ipsum", "dolor",
            "sit", "amet",
            "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis",
            "etiam", "vel", "erat", "placerat", "ante",
            "porttitor", "sodales", "pellentesque", "augue", "purus"};
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.explore);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice,
                items));

        getListView().setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        selection=(TextView)findViewById(R.id.selection);
    }
    @Override
    public void onListItemClick(ListView parent, View v, int position,
                                long id) {
        selection.setText(items[position]);
    }
}
