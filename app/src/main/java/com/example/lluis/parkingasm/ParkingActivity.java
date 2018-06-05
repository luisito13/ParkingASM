package com.example.lluis.parkingasm;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;
import cat.tomasgis.module.communication.CommManager;
import cat.tomasgis.module.communication.base.AppURL;
import cat.tomasgis.module.communication.listeners.IDataReceiver;
import cat.tomasgis.module.communication.listeners.StringResponseListener;
import com.google.gson.Gson;


public class ParkingActivity extends AppCompatActivity implements IDataReceiver{
    private static final String TAG = com.example.lluis.parkingasm.ParkingActivity.class.getSimpleName();
    StringResponseListener stringListener = new StringResponseListener(this);

    private TextView showParking, nameParking;
    private Button mapa,floor;
    private Parking[] parking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        mapa =findViewById(R.id.mapa);
        floor =findViewById(R.id.floor);

        CommManager.initializeQueu(this);
        if (! CommManager.callRequest(AppURL.PARKING_URL,stringListener))
            Toast.makeText(this, "Call error", Toast.LENGTH_SHORT).show();
    }

    public void onClickBt(View v) {
        if(v==mapa){
            Intent intentMain = new Intent(ParkingActivity.this ,
                    MapActivity.class);
            startActivity(intentMain);
            Log.i(TAG,"Obrim el map_activity");

        }

        if(v==floor){
            Intent intentApp = new Intent(ParkingActivity.this,
                    FloorListActivity.class);
            ParkingActivity.this.startActivity(intentApp);
            Log.i(TAG,"Obrim floorsActivity");
        }
    }


    @Override
    public void onReceiveData(String data) {
        boolean espai=false;
        if (data !=null) {
            if (data.length() > 0) {
                Toast.makeText(this, "Data received", Toast.LENGTH_SHORT).show();
                Log.d(TAG,data);
                Gson gson = new Gson();
                parking=gson.fromJson(data, Parking[].class);

                nameParking=findViewById(R.id.title);
                nameParking.setText(parking[0].getName());

                clearAllData();
                createBaseData();
            }
        }
        else
        {
            Toast.makeText(this, "Data NOT received", Toast.LENGTH_SHORT).show();
            Log.e(TAG,"No data to show");
        }
    }

    protected void clearAllData()
    {
        int numElementsDeleted;
        ContentResolver contentResolver = this.getContentResolver();

        //Slot
        numElementsDeleted = contentResolver.delete(ModelContracts.SlotModel.buildContentUri(),null,null);
        Log.d(TAG,String.format("Slots deleted: %d",numElementsDeleted));

        //Floor
        numElementsDeleted = contentResolver.delete(ModelContracts.FloorModel.buildContentUri(),null,null);
        Log.d(TAG,String.format("Floors deleted %d",numElementsDeleted));

        //Location
        numElementsDeleted = contentResolver.delete(ModelContracts.LocationModel.buildContentUri(),null,null);
        Log.d(TAG,String.format("Locations deleted %d",numElementsDeleted));

        //Parking
        numElementsDeleted = contentResolver.delete(ModelContracts.ParkingModel.buildContentUri(),null,null);
        Log.d(TAG,String.format("Parkings deleted %d",numElementsDeleted));
    }

    protected void createBaseData()
    {
        ContentResolver contentResolver = this.getContentResolver();
        ContentValues contentValuesParking;
        ContentValues contentValuesFloor;
        ContentValues contentValuesSlot;
        ContentValues contentValuesLocation;

        contentValuesParking=ContentValuesUtils.modelToContentValuesP(parking[0]);
        Uri insertUri;
        for (Floor floor:parking[0].getFloors())
        {
            //Insert floor
            contentValuesFloor=ContentValuesUtils.modelToContentValuesF(floor);
            insertUri = contentResolver.insert(ModelContracts.FloorModel.buildContentUri(),contentValuesFloor);
            Log.d(TAG,String.format("Floor added: %s",insertUri.toString()));

            for(Slot slot: floor.getSlots()){
                contentValuesSlot=ContentValuesUtils.modelToContentValuesS(slot,floor.getCompany_number());

                //Insert slot
                insertUri = contentResolver.insert(ModelContracts.SlotModel.buildContentUri(),contentValuesSlot);
                Log.d(TAG,String.format("Slot added: %s",insertUri.toString()));
            }

            //Insert location
            contentValuesLocation=ContentValuesUtils.modelToContentValuesL(parking[0].getLocation(),parking[0].getName());
            insertUri = contentResolver.insert(ModelContracts.LocationModel.buildContentUri(),contentValuesLocation);
            Log.d(TAG,String.format("Location added: %s",insertUri.toString()));
        }
        //Insert parking
        insertUri = contentResolver.insert(ModelContracts.ParkingModel.buildContentUri(),contentValuesParking);
        Log.d(TAG,String.format("Parking added: %s",insertUri.toString()));

    }
}