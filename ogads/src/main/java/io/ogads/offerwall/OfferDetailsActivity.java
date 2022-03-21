package io.ogads.offerwall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class OfferDetailsActivity extends AppCompatActivity {

    private ImageView pic;
    TextView name2,adcpy2,dsc2;
    private Button complete_button2;
    public String username, hash, link;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);


        Intent intent = getIntent();

        String name=  intent.getStringExtra("names");
        hash =   intent.getStringExtra("hash");
        String  adcopy=  intent.getStringExtra("adcopy");
        String dsc=  intent.getStringExtra("dsc");
        String payout=  intent.getStringExtra("payout");
        link=  intent.getStringExtra("link");
        String picture= intent.getStringExtra("picture");
        String packege= intent.getStringExtra("packege");

        pic =findViewById(R.id.bg_image);
        name2 =findViewById(R.id.name2);
        name2.setText(name);
        adcpy2 =findViewById(R.id.adcopy2);
        adcpy2.setText(adcopy);
        dsc2 =findViewById(R.id.dsc2);
        dsc2.setText(dsc);
        complete_button2 =findViewById(R.id.complete_button2);
        Glide.with(OfferDetailsActivity.this).load(picture).into(pic);

        complete_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    startActivity(Intent.createChooser(intent, "Chose browser"));

            }
        });
    }




}