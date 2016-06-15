package checkmobile.de.hertz.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

import checkmobile.de.hertz.R;

/**
 * Created by icetusk on 15.06.16.
 */
public class ImageAdapter extends ArrayAdapter<File> {

    List<File> list=new ArrayList<>();



    public ImageAdapter(Context context, int resource) {
        super(context, resource);
    }


    @Override
    public File getItem(int position) {
        return this.list.get(position);
    }

    public void add(File object){
        super.add(object);
        list.add(object);
    }

    public int getCount(){
        return this.list.size();
    }

    static class DataHandler
    {
        ImageView image;
        //TextView comment;
        ImageButton delete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;

        final DataHandler handler;
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.image_element,parent,false);
            handler=new DataHandler();
            handler.image=(ImageView)row.findViewById(R.id.image);
            //handler.comment=(TextView) row.findViewById(R.id.comment);
            row.setTag(handler);


            final File file = list.get(position);

            Log.e(getClass().getName(), "Position" + position + "Init file:"+file.getName());
            setPic(handler.image, file);

            handler.delete = (ImageButton) row.findViewById(R.id.buttonDelete);

            handler.delete.setOnClickListener(new View.OnClickListener() {

                private File file;

                {
                    this.file =  list.get(position);
                }

                @Override
                public void onClick(View v) {
                    Log.e(getClass().getName(), "Remove file:"+file.getName());
                    list.remove(this.file);
                    notifyDataSetInvalidated();
                }
            });
        }

        return row;
    }


    private void setPic(ImageView mImageView, File file) {
        // Get initial metrics
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int targetW = (int)dpWidth;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = photoW/targetW;

        // Resize file
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = scaleFactor;
            Log.e(getClass().getName(), "Scale factor:"+scaleFactor);
            int compress = 75;
            BitmapFactory.decodeStream(is, null, options).compress(Bitmap.CompressFormat.JPEG, compress, new FileOutputStream(new File(file.getAbsolutePath())));
            is.close();
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage(), e);
        }

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), bmOptions);

        Log.e(getClass().getName(), "Bitmap width:"+bitmap.getWidth());
        Log.e(getClass().getName(), "Bitmap height:"+bitmap.getHeight());

        mImageView.setImageBitmap(bitmap);
    }
}
