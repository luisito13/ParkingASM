package com.example.lluis.parkingasm;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;


public class SlotListActivity extends AppCompatActivity{
    private static final String TAG = com.example.lluis.parkingasm.SlotListActivity.class.getSimpleName();

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_extended);

        mContext = this.getApplicationContext();

        Cursor cursor = this.getContentResolver().query(ModelContracts.SlotModel.buildContentUri(),
                ModelContracts.SlotModel.DEFAULT_PROJECTIONS,
                null,null, ModelContracts.SlotModel.DEFAULT_SORT);

        SlotListAdapter crsAdapter = new SlotListAdapter(mContext,cursor);
        ((ListView)this.findViewById(R.id.list_detall)).setAdapter(crsAdapter);
    }

}