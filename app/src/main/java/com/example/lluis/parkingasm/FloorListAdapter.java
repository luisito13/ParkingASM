package com.example.lluis.parkingasm;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;


public class FloorListAdapter extends BaseAdapter {

    private Context mContext;
    private static final String TAG = FloorListAdapter.class.getSimpleName();

    Cursor mData;

    public FloorListAdapter(Context context, Cursor data)
    {
        if(context==null) Log.e(TAG,"Context is null");
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;

        return mData.getCount();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_floor,null);
        }

        mData.moveToPosition(i);

        String name = mData.getString(mData.getColumnIndex(ModelContracts.FloorContract.NAME));

        ((TextView) view.findViewById(R.id.floor_name)).setText(name);

        Cursor cursor = mContext.getContentResolver().query(ModelContracts.SlotModel.buildContentUri(),
                ModelContracts.SlotModel.DEFAULT_PROJECTIONS,
                "floor_id=?",
                new String[]{"1"},
                ModelContracts.SlotModel.DEFAULT_SORT);

        ((TextView) view.findViewById(R.id.nombre_slots)).setText(String.valueOf(cursor.getCount()));
        cursor.close();

        return view;
    }
}