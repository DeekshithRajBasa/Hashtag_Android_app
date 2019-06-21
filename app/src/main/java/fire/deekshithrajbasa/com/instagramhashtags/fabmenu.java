package fire.deekshithrajbasa.com.instagramhashtags;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class fabmenu extends AppCompatActivity {
Dialog dialog;
TextView txtclose;
CardView cardView;
Button bt , blog , report , share , rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabmenu);
        dialog = new Dialog(this);
        /*cardView = (CardView)findViewById(R.id.instabtn);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/deekshith_raj_basa");
                Intent instagram = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(instagram);
            }
        });*/


        rate = (Button) findViewById(R.id.rate);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = getPackageName();
                try
                {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str)));
                    // return true;
                }
                catch (ActivityNotFoundException localActivityNotFoundException1) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + str)));
                    // return true;

            }}

        });

        share= (Button)findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareApp(getApplicationContext());

            }
        });
bt = (Button) findViewById(R.id.Instagram);
bt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Uri uri = Uri.parse("http://instagram.com/deekshith_raj_basa");
        Intent instagram = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(instagram);


    }
});
blog= (Button)findViewById(R.id.blog);
blog.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String url = "http://tophashtag.tk";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }
});
report= (Button)findViewById(R.id.bug);
report.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        Intent localIntent2 = new Intent("android.intent.action.SEND");
        localIntent2.setType("message/rfc822");
        localIntent2.putExtra("android.intent.extra.EMAIL", new String[] { "deekshithraj.basa@gmail.com" });
        localIntent2.putExtra("android.intent.extra.SUBJECT", "#hashtag");
        localIntent2.putExtra("android.intent.extra.TEXT", "I would like to Report bug regarding your App #hashtag.");
        try
        {
            startActivity(Intent.createChooser(localIntent2, "Choose Gmail"));
            // return true;
        }
        catch (ActivityNotFoundException localActivityNotFoundException3)
        {
            Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_LONG).show();
            //return true;
        }

    }
});




        }


    public void Showpopup(View v){
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


    public static void shareApp(Context context)
    {
        final String appPackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this amazing #hashtag app at: https://play.google.com/store/apps/details?id=" + appPackageName);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }


}
