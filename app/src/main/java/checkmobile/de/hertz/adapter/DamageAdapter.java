package checkmobile.de.hertz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import checkmobile.de.hertz.R;

/**
 * Created by icetusk on 16.06.16.
 */
public class DamageAdapter extends RecyclerView.Adapter<DamageAdapter.ViewHolder> {
    private String[] mDataset;

    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImageView;
        public ViewHolder(View v) {
            super(v);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DamageAdapter(String[] myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DamageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.damage_element, parent, false);
        // set the view's size, margins, paddings and layout parameters
        // ...

        GridLayout gv = (GridLayout) v.findViewById(R.id.imagesGrid);
        //gv.setAdapter(new ImageGridAdapter(context));
        for(int i=0; i < 5; i++) {
            ImageView iv = new ImageView(v.getContext());

            gv.addView(iv);

            Picasso.with(context).load(R.drawable.cat).resize(displayMetrics.widthPixels/2-10,0).into(iv);

            /*
            android:layout_height="160dp"
            android:layout_alignParentTop="true"
            android:layout_gravity="fill_horizontal"
            android:background="#ff44aacc"
            android:src="@drawable/cat"
            android:layout_width="160dp"
                    />
             */
        }

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}


