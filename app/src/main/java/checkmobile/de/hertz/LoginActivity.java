package checkmobile.de.hertz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import checkmobile.de.hertz.helper.AccessoriesHelper;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent menuIntent = new Intent(getApplicationContext(), SelectableListActivity.class );
                Intent menuIntent = new Intent(getApplicationContext(), StationActivity.class );
                menuIntent.putExtra(SelectableListActivity.HELPER_EXTRA, new AccessoriesHelper());
                startActivity(menuIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return(super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                return (true);

            case R.id.help:
                return (true);
        }

        return(super.onOptionsItemSelected(item));
    }
}
