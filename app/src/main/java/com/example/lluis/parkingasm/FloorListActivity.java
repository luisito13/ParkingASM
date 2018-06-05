package com.example.lluis.parkingasm;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ListView;
import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;
import android.content.Context;
import android.view.View;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class FloorListActivity extends AppCompatActivity{
    private static final String TAG = FloorListActivity.class.getSimpleName();

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floors);

        ((ListView)this.findViewById(R.id.list)).setAdapter(null);

        mContext = this.getApplicationContext();

        Cursor cursor = this.getContentResolver().query(ModelContracts.FloorModel.buildContentUri(),
                ModelContracts.FloorModel.DEFAULT_PROJECTIONS,
                null,null, ModelContracts.FloorModel.DEFAULT_SORT);

        FloorListAdapter crsAdapter = new FloorListAdapter(mContext,cursor);
        ListView llista_floors = findViewById(R.id.list);
        llista_floors.setAdapter(crsAdapter);
        llista_floors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String selectedItem = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(FloorListActivity.this, SlotListActivity.class);
                FloorListActivity.this.startActivity(intent);
            }
        });
    }
}