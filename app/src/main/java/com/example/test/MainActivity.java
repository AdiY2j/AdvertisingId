package com.example.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Observable;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Hello MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getADId();
    }

    private void getADId() {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                AdvertisingIdClient.Info idInfo = null;
                try {
                    idInfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String advertId = null;
                try{
                    advertId = idInfo.getId();
                }catch (Exception e){
                    e.printStackTrace();
                }
                return advertId;
            }
            @Override
            protected void onPostExecute(String advertId) {
                Log.d("Hello", "onPostExecute: "+ advertId);
            }
        };
        task.execute();

    }

}
