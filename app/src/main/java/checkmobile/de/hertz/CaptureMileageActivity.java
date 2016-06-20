package checkmobile.de.hertz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

import checkmobile.de.hertz.activity.CMActivity;
import checkmobile.de.hertz.helper.ProcessesHelper;

public class CaptureMileageActivity extends CMActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.initData();

        setContentView(R.layout.activity_capture_mileage);

        TextView inDate = (TextView) findViewById(R.id.inDate);
        TextView outDae = (TextView) findViewById(R.id.outDateTime);

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        inDate.setText(currentDateTimeString);
        outDae.setText(currentDateTimeString);

        Button save = (Button) findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                process.setFinished(true);
                processDao.update(process);

                finish();
            }
        });
    }
}
