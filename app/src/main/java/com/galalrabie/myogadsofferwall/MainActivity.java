package com.galalrabie.myogadsofferwall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import io.ogads.offerwall.Start;
import io.ogads.offerwall.StartActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          Start.Runmyapp(this,"https://mycatappfaster.xyz/ogads.php","5624|1N0M2N5CBTzi0Kxxk7y5nODfvjCC3aAWes8urVnc","jasem41",240);






    }
}