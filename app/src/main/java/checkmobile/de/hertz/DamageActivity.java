package checkmobile.de.hertz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

public class DamageActivity extends AppCompatActivity {

    private String TAG = this.getClass().getName();

    private CustomImageView imageView;

    private Float scale = 1f;

    private ScaleGestureDetector SGD;

    private GestureDetector GD ;

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

        SGD = new ScaleGestureDetector(this,new ScaleListener());
        GD = new GestureDetector(this, mGestureListener);

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
}
