package com.koteswara.activityforresultapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> someActivityResultLauncher;
    public static final int requestCode =1; //Intent Request Code
    private static TextView resultMessage;
    private static Button getMessageBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultMessage = (TextView)findViewById(R.id.resultMessage);
        getMessageBtn = (Button)findViewById(R.id.secondActivityBtn);


        getMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Open second activity with request code
                Intent in = new Intent(MainActivity.this, SecondActivity.class);
               // startActivityForResult(in, requestCode);
                someActivityResultLauncher.launch(in);

            }
        });
         someActivityResultLauncher  = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                if (result.getResultCode()== Activity.RESULT_OK){
                    String message = result.getData().getStringExtra("message");
                    resultMessage.setText(message);
                }
                if (result.getResultCode() == RESULT_CANCELED)

                    //When result is cancelled display toast
                    Toast.makeText(MainActivity.this, "Activity cancelled.", Toast.LENGTH_SHORT).show();


            }

        });

    }
    
 /*   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestCode) {
            if (resultCode == RESULT_OK) {

                //If result code is OK then get String extra and set message
                String message = data.getStringExtra("message");
                resultMessage.setText(message);
            }

            if (resultCode == RESULT_CANCELED)

                //When result is cancelled display toast
                Toast.makeText(MainActivity.this, "Activity cancelled.", Toast.LENGTH_SHORT).show();


        }
    }*/
}