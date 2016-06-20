package checkmobile.de.hertz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DriverSignature extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_signature);

        Button accept = (Button) findViewById(R.id.acceptButton);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DriverSignature.this.getIntent();
                DriverSignature.this.setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
