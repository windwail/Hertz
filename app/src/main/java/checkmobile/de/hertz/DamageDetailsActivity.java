package checkmobile.de.hertz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import checkmobile.de.hertz.activity.CMActivity;
import checkmobile.de.hertz.helper.ProcessesHelper;

public class DamageDetailsActivity extends CMActivity {

    public static final int CREATE_DAMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damage_details);


        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), PhotoListActivity.class);
                myIntent.putExtra(ProcessesHelper.PROCESS_ID, process.getId());
                DamageDetailsActivity.this.startActivityForResult(myIntent, CREATE_DAMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CREATE_DAMAGE) {

            if( resultCode == RESULT_OK) {
                Intent intent = this.getIntent();
                this.setResult(RESULT_OK, intent);
            }
            finish();

        }
    }
}
