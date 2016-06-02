package checkmobile.de.hertz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class CaptureMileageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_mileage);

        TextView inDate = (TextView) findViewById(R.id.inDate);
        TextView outDae = (TextView) findViewById(R.id.outDateTime);

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        inDate.setText(currentDateTimeString);
        outDae.setText(currentDateTimeString);
    }
}
