package com.example.aleksander.bartender;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Aleksander on 01.03.2017.
 */

public class Custom_Cursor_Adapter extends CursorAdapter {
    private TextView txtHoved, txtSub;

    public Custom_Cursor_Adapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.adapter_custom, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        txtHoved =(TextView)view.findViewById(R.id.txtHoved);
        txtSub =(TextView)view.findViewById(R.id.txtSub);

        String drinkName, spritType, blandevannName;

        drinkName = cursor.getString(1);
        spritType = cursor.getString(8);
//        blandevannName = cursor.getString(3);

        txtHoved.setText(drinkName);
        txtSub.setText(spritType);
    }
}