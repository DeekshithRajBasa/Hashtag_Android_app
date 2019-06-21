package fire.deekshithrajbasa.com.instagramhashtags;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    CardView insta , fb;
    ShowcaseView.Builder showcase ;
    final int Showcaseint = 28;
    FloatingActionButton fab;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.too);
        setSupportActionBar(toolbar);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        insta = (CardView) findViewById(R.id.instaba);
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insta = new Intent(getApplicationContext(),instahashtags.class);
                startActivity(insta);
            }
        });

        fb = (CardView) findViewById(R.id.instaba2);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insta = new Intent(getApplicationContext(),fbhashtags.class);
                startActivity(insta);
            }
        });

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),fabmenu.class));


            }

        });

        if(!isConnected(MainActivity.this))
            startActivity(new Intent(getApplicationContext(),nointernet.class));//buildDialog(MainActivity.this).show();
        else {
            Toast.makeText(MainActivity.this,"Welcome", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_main);
        }


    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
        else return false;
        } else
        return false;
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
         //   return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Uri uri = Uri.parse("http://instagram.com/deekshith_raj_basa");
            Intent instagram = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(instagram);
            return true;
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Uri uri = Uri.parse("market://search?q=pub:Deekshith Raj Basa");
            Intent instagram = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(instagram);


        } else if (id == R.id.nav_slideshow) {
            String url = "http://tophashtag.tk";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);


        } else if (id == R.id.nav_manage) {

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
                Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_LONG).show();
                //return true;
            }

        } else if (id == R.id.rate_us) {

            String str = getPackageName();
            try
            {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str)));
                // return true;
            }
            catch (ActivityNotFoundException localActivityNotFoundException1) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + str)));
                // return true;
            }
        } else if (id == R.id.nav_share) {

               shareApp(getApplicationContext());

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
