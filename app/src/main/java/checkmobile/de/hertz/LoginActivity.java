package checkmobile.de.hertz;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import checkmobile.de.hertz.activity.CMActivity;
import checkmobile.de.hertz.helper.AccessoriesHelper;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.client.methods.HttpPut;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

public class LoginActivity extends CMActivity {

    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent menuIntent = new Intent(getApplicationContext(), SelectableListActivity.class );


                Intent menuIntent = new Intent(getApplicationContext(), StationActivity.class );
                menuIntent.putExtra(SelectableListActivity.HELPER_EXTRA, new AccessoriesHelper());
                startActivity(menuIntent);


                // Create and populate a simple object to be used in the request

                //new RestTest().execute();
            }
        });

    }

    class RestTest extends AsyncTask<Void, Void, Void> {

        private Exception exception;

        protected Void doInBackground(Void... urls) {

            HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead HttpPut

            try {
                HttpPost request = new HttpPost("http://192.168.81.24:8081/api/v1/infleeting");
                request.addHeader("content-type", "application/json");
                StringEntity params =new StringEntity("{\"deliveryNote\":{\"deliveryNumber\":\"123\",\"licensePlate\":\"31\",\"driverName\":\"123\",\"driverEmail\":\"312@mail.com\",\"vehicleCount\":2,\"condition\":\"ok\"},\"vehicles\":[{\"order\":0,\"vehicleCoreData\":{\"unitNumber\":123,\"licensePlate\":\"31\",\"vin\":123,\"carGroup\":\"A\"},\"mileage\":{\"mileage\":11},\"fueling\":{\"fuel\":2},\"damageConditions\":{\"damages\":[{\"area\":\"Front\",\"piece\":\"Bumper\",\"type\":\"Crack\",\"severity\":\"Small\",\"pictures\":[]}]},\"vehicleAdditionalData\":{\"vehicleType\":\"PKW\",\"model\":\"Q5\",\"manufacturer\":\"Audi\",\"gearing\":\"automatic\",\"fuelType\":\"Diesel\",\"tankCapacity\":22,\"сolour\":\"black\",\"metallic\":true},\"overviewPhoto\":{\"pictures\":[]},\"vehicleAccessories\":{\"spareBulbs\":true,\"taxDisc\":true},\"technicalFaults\":{\"brakes\":true,\"clutch\":true,\"cooling\":true,\"electrical\":true,\"engine\":true,\"serviceNeeded\":true,\"steering\":true,\"warningLights\":true}},{\"order\":1,\"vehicleCoreData\":{\"unitNumber\":321,\"licensePlate\":\"312\",\"vin\":123,\"carGroup\":\"A\"},\"mileage\":{\"mileage\":22},\"fueling\":{\"fuel\":6},\"damageConditions\":{\"damages\":[{\"area\":\"Front\",\"piece\":\"Bumper\",\"type\":\"Crack\",\"severity\":\"Medium\",\"pictures\":[]}]},\"vehicleAdditionalData\":{\"vehicleType\":\"PKW\",\"model\":\"520\",\"manufacturer\":\"BMW\",\"gearing\":\"manual\",\"fuelType\":\"Diesel\",\"tankCapacity\":33,\"сolour\":\"silver\"},\"overviewPhoto\":{\"pictures\":[]},\"vehicleAccessories\":{},\"technicalFaults\":{}}],\"user\":\"userName\",\"timestamp\":1466427873572,\"uuid\":\"daf5b004-874b-bd01-e2bb-e7c7bddf767d\",\"signature\":{\"signature\":\"data:image/png;base64,iVBORw0...Jggg==\"}}");
                request.setEntity(params);
                HttpResponse response = httpClient.execute(request);

                Log.e("TAG","SUCCESS!");
                // handle response here...
            }catch (Exception ex) {
                // handle exception here
                Log.e("TAG", ex.getMessage(), ex);
            }

            return null;

        }


        protected void onPostExecute(Void feed) {
            // TODO: check this.exception
            // TODO: do something with the feed
        }
    }




}
