package io.ogads.offerwall;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Start implements View.OnClickListener {
   public static Context context;
   public static Dialog dialog;

    public static OnClickListner onClickListner;

    interface OnClickListner{
        void OnClick(View v);
    }


    public void setOnClickListner(OnClickListner onClickListner){
        this.onClickListner=onClickListner;
    }


    public static void Runmyapp(Context context,String url,String api_key,String user,int xx){

        try {
            Intent myIntent = new Intent(context,Class.forName("io.ogads.offerwall.StartActivity"));
            myIntent.putExtra("url",url);
            myIntent.putExtra("api_key",api_key);
            myIntent.putExtra("user",user);
            myIntent.putExtra("points",xx);
            context.startActivity(myIntent );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    public static void jasem(Context ctx, View.OnClickListener onClickListenere, View.OnClickListener asd){
         dialog = new Dialog(ctx);
        dialog.setContentView(R.layout.dialog_close_app);
        dialog.show();



        Button close = (Button) dialog.findViewById(R.id.close);
        close.setOnClickListener(onClickListenere);



        Button share = (Button) dialog.findViewById(R.id.mShare);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asd.onClick(view);
               dialog.dismiss();

            }
        });


    }



    @Override
    public void onClick(View view) {
  Toast.makeText(context,"oooooo",Toast.LENGTH_LONG).show();
    }
}
