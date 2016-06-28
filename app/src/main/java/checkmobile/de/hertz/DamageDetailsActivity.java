package checkmobile.de.hertz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.androidannotations.annotations.EActivity;

import java.util.List;

import checkmobile.de.hertz.activity.CMActivity;
import checkmobile.de.hertz.gson.Damage;
import checkmobile.de.hertz.helper.ProcessesHelper;


public class DamageDetailsActivity extends CMActivity {

    public static final int CREATE_DAMAGE = 1;

    Spinner damagedPart;

    Spinner damageType;

    EditText comment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damage_details);

        damagedPart = (Spinner) findViewById(R.id.damagedPart);
        damageType = (Spinner) findViewById(R.id.damageType);
        comment = (EditText) findViewById(R.id.comment);


        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = getIntent();
                String uid = i.getStringExtra(ProcessesHelper.DAMAGE_ID);
                i = null;

                List<Damage> damages = process.getDamages();

                Damage damage = null;

                for(Damage d: damages) {
                    if(d.getUid().equalsIgnoreCase(uid)) {
                        damage = d;
                        break;
                    }
                }

                if(damage != null) {
                    damage.setPiece(damagedPart.getSelectedItem().toString());
                    damage.setType(damageType.getSelectedItem().toString());
                    damage.setComment(comment.getText().toString());
                }

                process.setDamages(damages);
                processDao.update(process);

                Intent myIntent = getIntent(PhotoListActivity.class, v.getContext());
                myIntent.putExtra(ProcessesHelper.DAMAGE_ID, damage.getUid());
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
                intent.putExtra("images", data.getStringArrayExtra("images"));
                this.setResult(RESULT_OK, intent);
                finish();
            }

        }
    }
}
