package checkmobile.de.hertz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.SeekBar;

import checkmobile.de.hertz.fragment.CMNumberPicker;

public class TyrePressure extends AppCompatActivity {
    public static final String FRONT_LEFT = "Left front wheel";
    public static final String FRONT_RIGHT = "Right front wheel";
    public static final String BACK_LEFT = "Left back wheel";
    public static final String BACK_RIGHT = "Right back wheel";


    private CMNumberPicker frontLeft;
    private CMNumberPicker frontRight;
    private CMNumberPicker backLeft;
    private CMNumberPicker backRight;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tyre_pressure);




        /*
        SeekBar bar = (SeekBar) findViewById(R.id.seekBar);

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float f = progress;
                pres.setText(""+f/100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        */

    }
}
