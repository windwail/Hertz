package checkmobile.de.hertz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import checkmobile.de.hertz.activity.CMActivity;
import checkmobile.de.hertz.adapter.DamageAdapter;
import checkmobile.de.hertz.helper.ProcessesHelper;

public class DamageListActivity extends CMActivity {

    public static final int CREATE_DAMAGE = 1;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damage_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new DamageAdapter(new String[]{"","",""}, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

        Button addDamage = (Button) findViewById(R.id.addDamage);


        addDamage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), DamageActivity.class);
                myIntent.putExtra(ProcessesHelper.PROCESS_ID, process.getId());
                DamageListActivity.this.startActivityForResult(myIntent, CREATE_DAMAGE);

            }
        });

        Button saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process.setFinished(true);
                processDao.update(process);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CREATE_DAMAGE) {

            if( resultCode == RESULT_OK) {
               //Intent intent = this.getIntent();
               //process.setFinished(true);
               //processDao.update(process);
               //this.setResult(RESULT_OK, intent);
            }
            //finish();
        }
    }
}
