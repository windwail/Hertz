package checkmobile.de.hertz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CarInfleetOverview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_infleet_overview);

        Button saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DriverSignature.class);
                //myIntent.putExtra(ProcessesHelper.PROCESS_ID, process.getId());
                CarInfleetOverview.this.startActivityForResult(myIntent, FINISH_INFLEET);
            }
        });
    }

    public static final int FINISH_INFLEET = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == FINISH_INFLEET) {

            if( resultCode == RESULT_OK) {
                Intent intent = this.getIntent();
                this.setResult(RESULT_OK, intent);
            }
            finish();

        }
    }
}
