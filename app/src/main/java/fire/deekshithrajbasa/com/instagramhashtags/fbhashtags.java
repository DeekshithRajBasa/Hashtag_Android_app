package fire.deekshithrajbasa.com.instagramhashtags;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.StringTokenizer;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;


public class fbhashtags extends AppCompatActivity implements RewardedVideoAdListener {

    public static final String EXTRA_URL = "imageurl";
    DatabaseReference dref;
    ListView listview;
    private RecyclerView recyclerView;
    private DatabaseReference myref;
    ArrayList<String> list = new ArrayList<>();
    Button button;
    Button copyText;
    TextView textView;
    public Intent i;
    static String desc;
    public static InterstitialAd mInterstitialAd;
    private static RewardedVideoAd mRewardedVideoAd;
    Toolbar toolbar;
    FloatingActionButton fab;

    private EditText mSearchField;

    private ImageButton mSearchBtn;

    public static ArrayList<String> imageUrl = new ArrayList<>();
    public static ArrayList<String> titleTag = new ArrayList<>();
    public static ArrayList<String> descTag = new ArrayList<>();
    public static ArrayList<Integer> pos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbhashtags);

        fab();

        //taptargetview
        new MaterialTapTargetPrompt.Builder(fbhashtags.this)
                .setTarget(findViewById(R.id.searchLayoutfb))
                .setPrimaryText("Search Hashtags")
                .setSecondaryText("Search hashtags by adding '#' to your search keyword. ")
                // .setPromptBackground(new RectanglePromptBackground())
                .setPromptFocal(new RectanglePromptFocal())
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                    @Override
                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) {
                            // User has pressed the prompt target
                        }
                    }
                })
                .show();

        mSearchField = (EditText) findViewById(R.id.search_field);

        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);
        mSearchBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                String searchText = mSearchField.getText().toString();


                firebaseUserSearch(searchText);
            }

        });


        MobileAds.initialize(this, "ca-app-pub-1075395443616360/7356913361");

        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        mRewardedVideoAd.loadAd("ca-app-pub-1075395443616360/7356913361",
                new AdRequest.Builder().build());
        //recycle view
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        //firebase database
        myref = FirebaseDatabase.getInstance().getReference().child("/instagram");
        FirebaseRecyclerAdapter<facebookAdapter, fbhashtags.BlogViewHolder> recyclerAdapter = new FirebaseRecyclerAdapter<facebookAdapter, fbhashtags.BlogViewHolder>(
                facebookAdapter.class,
                R.layout.individual_rowfb,
                fbhashtags.BlogViewHolder.class,
                myref
        ) {

            @Override
            protected void populateViewHolder(fbhashtags.BlogViewHolder viewHolder, facebookAdapter model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setImage(model.getImage());
                viewHolder.setPosition(position);
                imageUrl.add("" + model.getImage());
                // StringTokenizer tokens = new StringTokenizer(model.getDescription(), "#");
                if (!descTag.contains(model.getDescription()))
                    descTag.add(model.getDescription());
                if (!titleTag.contains(model.getTitle().toLowerCase().toString()))
                    titleTag.add(model.getTitle().toLowerCase().toString());
                if (!pos.contains(position))
                    pos.add(position);
                Log.v("title:", "" + descTag.get(position));

            }
        };


        recyclerView.setAdapter(recyclerAdapter);


    }

    private void firebaseUserSearch(String searchText) {
        Toast.makeText(getApplicationContext(), "Started Search for :" + searchText, Toast.LENGTH_LONG).show();
        int i = 0;
        for (String string : descTag) {
            i++;
            if (string.matches(searchText))
                Toast.makeText(getApplicationContext(), "Found: " + searchText + " at: " + i, Toast.LENGTH_LONG).show();

        }

        Query firebaseSearchQuery = myref.orderByChild("description").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<facebookAdapter, fbhashtags.BlogViewHolder> RecyclerAdapter = new FirebaseRecyclerAdapter<facebookAdapter, fbhashtags.BlogViewHolder>(

                facebookAdapter.class,
                R.layout.individual_rowfb,
                fbhashtags.BlogViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(fbhashtags.BlogViewHolder viewHolder, facebookAdapter model, int position) {


                //viewHolder.setDetails(instahashtags.class, model.getTitle(), model.getDescription(), model.getImage());

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setImage(model.getImage());
                viewHolder.setPosition(position);

            }
        };

        recyclerView.setAdapter(RecyclerAdapter);

    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }

    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView textView_title;
        TextView textView_decription;
        ImageView imageView;
        int position;


        public BlogViewHolder(final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if (mRewardedVideoAd.isLoaded()) {
                        mRewardedVideoAd.show();

                    } else {

                        Intent passdata = new Intent(view.getContext(), customview.class);


                        passdata.putExtra("title", textView_title.getText().toString());
                        passdata.putExtra("description", textView_decription.getText().toString());
                        passdata.putExtra("image", imageUrl.get(position));

                  /*
                            Pair[] pairs = new Pair[4];
                    pairs[0] = new Pair<View , String>(  , "imagetrans");

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,pairs); */


                        view.getContext().startActivity(passdata);

                        //  Toast.makeText(view.getContext(), "copy #hashtags", Toast.LENGTH_SHORT).show();

                    }
                }
            });

            mView = itemView;
            textView_title = (TextView) itemView.findViewById(R.id.title);
            textView_decription = (TextView) itemView.findViewById(R.id.description);
            imageView = (ImageView) itemView.findViewById(R.id.image);


        }

        public void setTitle(String title) {
            textView_title.setText(title + "");
        }

        public void setDescription(String description) {
            textView_decription.setText(description);
        }

        public void setPosition(int pos) {
            this.position = pos;
        }

        public void setImage(String image) {
            Picasso.with(mView.getContext())
                    .load(image)
                    .into(imageView);
        }


    }

    @Override
    public void onBackPressed() {
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        } else {

            this.finish();
        }
    }

    private void fab() {
        //fab
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), fabmenu.class));


            }

        });


    }
}





