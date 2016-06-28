package checkmobile.de.hertz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import checkmobile.de.hertz.activity.CMActivity;
import checkmobile.de.hertz.adapter.DamageAdapter;
import checkmobile.de.hertz.entity.Process;
import checkmobile.de.hertz.gson.Damage;
import checkmobile.de.hertz.helper.ProcessesHelper;

public class DamageListActivity extends CMActivity {

    public static final int CREATE_DAMAGE = 1;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Damage> damages;

    private void updateDamages() {
        process = (Process) processDao.queryForId(process_id);
        damages = process.getDamages();


        if (damages == null) {
            damages = new ArrayList<>();
        }

        mAdapter = new DamageAdapter(damages, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }

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
        updateDamages();

        Button addDamage = (Button) findViewById(R.id.addDamage);


        addDamage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent myIntent = new Intent(view.getContext(), DamageActivity.class);
                myIntent.putExtra(ProcessesHelper.PROCESS_ID, process.getId());
                DamageListActivity.this.startActivityForResult(myIntent, CREATE_DAMAGE);



                /* Just for test.

                 Damage damage = new Damage();
                 damage.setArea("Area");
                 damage.setComment("Comment");
                 damage.setImages(new String[]{"img1", "img2"});
                 damage.setPiece("Piece");
                 damage.setRegisterDate(new DateTime());
                 damage.setSeverity("Severity");

                 damages.add(damage);

                 process.setVariablesGson(GsonHelper.getBuilder().toJson(damages));

                 processDao.update(process);

                 mAdapter.notifyDataSetChanged();
                 */

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

        if (requestCode == CREATE_DAMAGE) {

            if (resultCode == RESULT_OK) {

                updateDamages();

                //Intent intent = this.getIntent();
                //process.setFinished(true);
                //processDao.update(process);
                //this.setResult(RESULT_OK, intent);
            }
            //finish();
        }
    }
}
