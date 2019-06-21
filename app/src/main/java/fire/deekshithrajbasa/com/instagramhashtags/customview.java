package fire.deekshithrajbasa.com.instagramhashtags;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class customview extends AppCompatActivity {
TextView tv , tv1 , textView;
CircleImageView img;
String ImageURL;
ImageButton cpy;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);

        MobileAds.initialize(this,
                "ca-app-pub-1075395443616360~8895008703");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1075395443616360/9246362491");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());



       // String i = getIntent().getExtras().getString("facebook");
        //Log.v("RECEIVED DATA",""+i);
        tv = (TextView)findViewById(R.id.textView2) ;
        tv.setText(getIntent().getExtras().getString("title"));
        //tv = findViewById(R.id.textView);
        //tv.setText(""+i);

        tv1 = (TextView)findViewById(R.id.textView) ;
        tv1.setText(getIntent().getExtras().getString("description"));

   img = (CircleImageView)findViewById(R.id.circleImageView);

         if(getIntent().getExtras().getString("image")!=null)
        {
            ImageURL = getIntent().getExtras().getString("image");
           // Picasso.with(getApplicationContext())
             //       .load(ImageURL)
             //       .into(img);

        }
       textView =(TextView)findViewById(R.id.textView);
       cpy = (ImageButton) findViewById(R.id.buttoncopy);
         cpy.setOnClickListener(new View.OnClickListener() {

             @SuppressWarnings("deprecation")
             @Override
             public void onClick(View view) {

                 if (mInterstitialAd.isLoaded()) {
                     mInterstitialAd.show();
                 } else {

                     ((ClipboardManager)getSystemService(getApplicationContext().CLIPBOARD_SERVICE))
                             .setText(tv1.getText().toString());

                     //making snack bar
                     Snackbar snackbar1 = Snackbar.make(view, "#Hashtags Copied Sucessfully!", Snackbar.LENGTH_LONG)
                             .setAction("Open Instagram", new View.OnClickListener() {
                                 @Override
                                 public void onClick(View view) {

                                     Intent instaintent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
                                     startActivity(instaintent);

                                 }
                             });
                     snackbar1.setActionTextColor(Color.rgb(255, 69, 0));

                     snackbar1.show();
                     // Toast.makeText(getApplicationContext(),"#Hashtags Copied",Toast.LENGTH_SHORT).show();
                 }}});
    }

    public void onBackPressed(){

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {

            this.finish();
        }

    }
}
