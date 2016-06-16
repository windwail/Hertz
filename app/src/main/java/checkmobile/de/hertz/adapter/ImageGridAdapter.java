package checkmobile.de.hertz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import checkmobile.de.hertz.R;

/**
 * Created by icetusk on 16.06.16.
 */
public class ImageGridAdapter  extends BaseAdapter
{
    private List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;

    public ImageGridAdapter(Context context)
    {
        inflater = LayoutInflater.from(context);

        items.add(new Item(R.drawable.cat));
        items.add(new Item(R.drawable.cat));
        items.add(new Item(R.drawable.cat));
        items.add(new Item(R.drawable.cat));
        items.add(new Item(R.drawable.cat));
        items.add(new Item(R.drawable.cat));
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i)
    {
        return items.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return items.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View v = view;
        ImageView picture;
        TextView name;

        if(v == null)
        {
            v = inflater.inflate(R.layout.grid_element, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
        }

        picture = (ImageView)v.getTag(R.id.picture);

        Item item = (Item)getItem(i);

        picture.setImageResource(item.drawableId);

        return v;
    }

    private class Item
    {
        final int drawableId;

        Item(int drawableId)
        {
            this.drawableId = drawableId;
        }
    }
}