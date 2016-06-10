package checkmobile.de.hertz.fragment;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import checkmobile.de.hertz.R;

/**
 * Created by icetusk on 26.05.16.
 */
public class CMNumberPicker extends Fragment {

    public final static String TAG = CMNumberPicker.class.getName();

    public final static String VALUE = "value";

    public final static String MIN_VALUE = "min_value";

    public final static String MAX_VALUE = "max_value";

    public final static String TITLE = "title";

    float value;

    float minValue;

    float maxValue;

    String title;

    float step;

    String units = "";

    ImageButton plus;

    ImageButton minus;

    boolean decimal;

    boolean buttons;

    private  EditText edit;

    public static CMNumberPicker newInstance() {
        return new CMNumberPicker();
    }

    public CMNumberPicker() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;

        if(buttons) {
            v = inflater.inflate(R.layout.fragment_number_picker, container, false);
        } else {
            v = inflater.inflate(R.layout.fragment_number_shower, container, false);
        }

        if(savedInstanceState != null) {
            value = savedInstanceState.getFloat(VALUE);
        }

        TextView u = (TextView) v.findViewById(R.id.units);
        u.setText(units);

        edit = (EditText) v.findViewById(R.id.value);

        if(!decimal) {
            edit.setInputType(InputType.TYPE_CLASS_NUMBER);
            edit.setText(String.format("%d", (int) value));
        } else {
            edit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            edit.setText(String.format("%.02f", value));
        }


        if(buttons) {

            plus = (ImageButton) v.findViewById(R.id.plusButton);

            minus = (ImageButton) v.findViewById(R.id.minusButton);

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    value = Float.parseFloat(edit.getText().toString());
                    if ((value + step) <= maxValue) {
                        value += step;

                        if (decimal) {
                            edit.setText(String.format("%.02f", value));
                        } else {
                            edit.setText(String.format("%d", (int) value));
                        }
                    }
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    value = Float.parseFloat(edit.getText().toString());
                    if ((value - step) >= minValue) {
                        value -= step;
                        if (decimal) {
                            edit.setText(String.format("%.02f", value));
                        } else {
                            edit.setText(String.format("%d", (int) value));
                        }
                    }
                }
            });
        }


        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putFloat(VALUE, value);
    }

    /**
     * Parse attributes during inflation from a view hierarchy into the
     * arguments we handle.
     */
    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        TypedArray a = activity.obtainStyledAttributes(attrs,R.styleable.CMNumberPicker);

        value = a.getFloat(R.styleable.CMNumberPicker_cm_picker_value, 0.0f);
        minValue = a.getFloat(R.styleable.CMNumberPicker_cm_picker_min, 0.0f);
        maxValue = a.getFloat(R.styleable.CMNumberPicker_cm_picker_max, 0.0f);
        step = a.getFloat(R.styleable.CMNumberPicker_cm_picker_step, 0.1f);
        units = a.getString(R.styleable.CMNumberPicker_cm_picker_units);
        title = a.getString(R.styleable.CMNumberPicker_cm_picker_title);
        decimal = a.getBoolean(R.styleable.CMNumberPicker_cm_picker_float, false);
        buttons = a.getBoolean(R.styleable.CMNumberPicker_cm_picker_buttons, true);

        a.recycle();
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public float getValue() {
        return Float.parseFloat( edit.getText().toString() );
    }

    public void setValue(float value) {
        this.value = value;
    }


}
