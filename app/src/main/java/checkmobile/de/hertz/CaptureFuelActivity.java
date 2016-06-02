package checkmobile.de.hertz;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.SeekBar;
import android.widget.TextView;

public class CaptureFuelActivity extends AppCompatActivity {

    TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_fuel);

        label = (TextView) findViewById(R.id.fuelText);


        //As this is a example we are going to use the width of the screen as a point to measure off,
        //For this to work correctly you really need the exact width of the SeekBar, but we cannot get
        //this as the Seekbar has no been drawn yet and will have a value of 0.


        //Get the width of the main view.
        Display display = getWindowManager().getDefaultDisplay();
        Point displaysize = new Point();
        display.getSize(displaysize);
        int width = displaysize.x;




        //set the seekbar maximum (Must be a even number, having a remainder will cause undersirable results)
        //this variable will also determine the number of points on the scale.
        int seekbarmax = 8;




        int seekbarpoints = (width/seekbarmax); //this will determine how many points on the scale there should be on the seekbar


        //find the seekbar in the view, and set some behaviour properties
        SeekBar seekbar = (SeekBar)findViewById(R.id.seekBar);


        //Set the seekbar to a max range of 10
        seekbar.setMax(seekbarmax);




        //Create a new bitmap that is the width of the screen
        Bitmap bitmap = Bitmap.createBitmap(width, 100, Bitmap.Config.ARGB_8888);
        //A new canvas to draw on.
        Canvas canvas = new Canvas(bitmap);


        //a new style of painting - colour and stoke thickness.
        Paint paint = new Paint();
        paint.setColor(Color.BLUE); //Set the colour to red
        paint.setStyle(Paint.Style.STROKE); //set the style
        paint.setStrokeWidth(1); //Stoke width


        Paint textpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textpaint.setColor(Color.rgb(61, 61, 61));// text color RGB
        textpaint.setTextSize(28);// text size


        //this draws a box around the edge of the bitmap
        canvas.drawRect(0,0,width-1,99,paint);


        int point = 0; //initiate the point variable


        //Start a for loop that will loop seekbarpoints number of times.
        for (int i = 0; i < seekbarpoints; i++  ) {


            if (i == 0) {
                canvas.drawText(Integer.toString(i), point, 95, textpaint);
            }else if (i>9){
                canvas.drawText(Integer.toString(i), point - 14, 95, textpaint);
            }else {
                if (i == seekbarmax) {
                    canvas.drawText(Integer.toString(i), point - 18, 95, textpaint);
                } else {
                    canvas.drawText(Integer.toString(i), point - 8, 95, textpaint);
                }
            }
            //the modulus operator is make the long and short lines as shown in the image
            //if i can be divided without a remainder then it will draw a short line


            if ((i%2)==0) {
                //short line
                point = point  + seekbarpoints;
                canvas.drawLine(point, 30, point, 0, paint);
                //drawLine(startx,startx,endy,endy)
            }else{
                //long line
                point = point  + seekbarpoints;
                canvas.drawLine(point, 50, point, 0, paint);
            }
        }


        //Create a new Drawable
        Drawable d = new BitmapDrawable(getResources(),bitmap);


        //Set the seekbar widgets background to the above drawable.
        //seekbar.setProgressDrawable(d);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                label.setText(progress+"/8");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
