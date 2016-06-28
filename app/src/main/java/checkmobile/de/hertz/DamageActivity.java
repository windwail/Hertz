package checkmobile.de.hertz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import checkmobile.de.hertz.activity.CMActivity;
import checkmobile.de.hertz.dao.ProcessDao;
import checkmobile.de.hertz.entity.Process;
import checkmobile.de.hertz.gson.Damage;
import checkmobile.de.hertz.gson.GsonHelper;
import checkmobile.de.hertz.helper.ProcessesHelper;

public class DamageActivity extends CMActivity {

    public static final int CREATE_DAMAGE = 1;

    private String TAG = this.getClass().getName();

    private CustomImageView imageView;

    private Float scale = 1f;

    private ScaleGestureDetector SGD;

    private GestureDetector GD ;

    private Spinner damagedArea;

    private Damage damage;

    private final GestureDetector.SimpleOnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            //TODO: Disable PAN.Think what to do with it.
            /*
            Log.e(TAG, "Scrolled "+distanceX+ " " + distanceY);
            imageView.setPan(-distanceX, -distanceY);
            */

            return true;
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damage);
        imageView = (CustomImageView) findViewById(R.id.imageView);
        damagedArea = (Spinner) findViewById(R.id.damagedArea);

        SGD = new ScaleGestureDetector(this,new ScaleListener());
        GD = new GestureDetector(this, mGestureListener);

        final Button doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(damage == null) {
                    processDao.update(process);
                    List<Damage> damages = process.getDamages();

                    damage = new Damage();
                    damage.setArea(damagedArea.getSelectedItem().toString());
                    damage.setComment("");
                    damage.setImages(new String[]{});
                    damage.setPiece("");
                    damage.setRegisterDate(new DateTime());
                    damage.setSeverity("");

                    damages.add(damage);

                    process.addDamage(damage);
                    processDao.update(process);

                }

                Intent i = getIntent(DamageDetailsActivity.class, v.getContext());
                i.putExtra(ProcessesHelper.DAMAGE_ID, damage.getUid());
                DamageActivity.this.startActivityForResult(i, CREATE_DAMAGE);
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        SGD.onTouchEvent(event);
        GD.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            // TODO: Should be done by buttons
            // Now it is disabled.
            /*
            scale *= detector.getScaleFactor();
            scale = Math.max(0.5f, Math.min(scale, 10f));
            imageView.setScale(scale);
            */
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CREATE_DAMAGE ) {

            if(resultCode == RESULT_OK) {
                process = (Process) processDao.queryForId(process_id);
                List<Damage> damages = process.getDamages();

                String[] images = data.getStringArrayExtra("images");

                for(Damage d: damages) {
                    if(d.getUid().equalsIgnoreCase(damage.getUid())) {
                        damage = d;
                        break;
                    }
                }

                damage.setImages(images);

                process.setDamages(damages);
                processDao.update(process);

                Intent intent = this.getIntent();
                this.setResult(RESULT_OK, intent);
                finish();
            }

        }
    }
}
