package io.ogads.offerwall.adpter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.ArrayList;

import io.ogads.offerwall.OfferDetailsActivity;
import io.ogads.offerwall.R;


public class MyOffersLisAdapter extends BaseAdapter {

    public Context context;

    public ArrayList<OffersDataModel> dataModelArrayList1;

    public long getItemId(int i) {
        return 0;
    }

    public int getItemViewType(int i) {
        return i;
    }

    public MyOffersLisAdapter(Context context2, ArrayList<OffersDataModel> arrayList) {
        this.context = context2;
        this.dataModelArrayList1 = arrayList;
    }

    public int getViewTypeCount() {
        return getCount();
    }

    public int getCount() {
        return this.dataModelArrayList1.size();
    }

    public Object getItem(int i) {
        return this.dataModelArrayList1.get(i);
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = ((LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_offers_list, (ViewGroup) null, true);
            viewHolder.name = (TextView) view2.findViewById(R.id.title);
            viewHolder.subtital = (TextView) view2.findViewById(R.id.sub_title);
            viewHolder.point = (TextView) view2.findViewById(R.id.amount);
            viewHolder.f99iv = (ImageView) view2.findViewById(R.id.image2);
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        String picture = this.dataModelArrayList1.get(i).getPicture();
        if (picture == null) {
            Glide.with(this.context).load(picture).into(viewHolder.f99iv);
        } else {
            Glide.with(this.context).load(picture).into(viewHolder.f99iv);
        }
        viewHolder.name.setText(this.dataModelArrayList1.get(i).getNames());
        viewHolder.subtital.setText(this.dataModelArrayList1.get(i).getAdcopy());
        TextView textView = viewHolder.point;
        if (this.dataModelArrayList1.get(i).getPackeg() ==null){
            textView.setText("Points " + this.dataModelArrayList1.get(i).getPayout());
        }else{
            textView.setText( this.dataModelArrayList1.get(i).getPackeg());
        }

        view2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, OfferDetailsActivity.class);
                intent.putExtra("names", ((OffersDataModel) MyOffersLisAdapter.this.dataModelArrayList1.get(i)).getNames());
                intent.putExtra("link", ((OffersDataModel) MyOffersLisAdapter.this.dataModelArrayList1.get(i)).getLink());
                intent.putExtra("adcopy", ((OffersDataModel) MyOffersLisAdapter.this.dataModelArrayList1.get(i)).getAdcopy());
                intent.putExtra("dsc", ((OffersDataModel) MyOffersLisAdapter.this.dataModelArrayList1.get(i)).getDsc());
                intent.putExtra("payout", ((OffersDataModel) MyOffersLisAdapter.this.dataModelArrayList1.get(i)).getPayout());
                intent.putExtra("picture", ((OffersDataModel) MyOffersLisAdapter.this.dataModelArrayList1.get(i)).getPicture());
                intent.putExtra("packege", ((OffersDataModel) MyOffersLisAdapter.this.dataModelArrayList1.get(i)).getPackeg());
                intent.putExtra("offertype", "adgem" );

                context.startActivity(intent);


            }
        });
        return view2;
    }

    private class ViewHolder {

        /* renamed from: iv */
        protected ImageView f99iv;
        protected TextView name;
        protected TextView point;
        protected TextView subtital;

        private ViewHolder() {
        }
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView2) {
            this.imageView = imageView2;
        }

        /* access modifiers changed from: protected */
        public Bitmap doInBackground(String... strArr) {
            try {
                return BitmapFactory.decodeStream(new URL(strArr[0]).openStream());
            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            this.imageView.setImageBitmap(bitmap);
        }
    }
}