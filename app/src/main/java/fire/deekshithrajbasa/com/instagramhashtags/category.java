package fire.deekshithrajbasa.com.instagramhashtags;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;

public class category extends AppCompatActivity {
 CardView insta , fb;
  ShowcaseView.Builder showcase ;
    final int Showcaseint = 28;
    FloatingActionButton fab;
    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);



        new MaterialTapTargetPrompt.Builder(category.this)
                .setTarget(findViewById(R.id.targetinsta))
                .setPrimaryText("Instagram Hashtags")
                .setSecondaryText("Press on instagram to get hashtags")

                .setPromptFocal(new RectanglePromptFocal())
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
                {
                    @Override
                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state)
                    {
                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED)
                        {
                            startActivity(new Intent(getApplicationContext(),instahashtags.class));
                        }
                    }
                })
                .show();


        insta = (CardView) findViewById(R.id.instaba);
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insta = new Intent(category.this,instahashtags.class);
                startActivity(insta);
            }
        });

        fb = (CardView) findViewById(R.id.instaba2);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insta = new Intent(category.this,fbhashtags.class);
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



        ssshowview();

    }





    public void ssshowview() {

        showcase = new ShowcaseView.Builder(this)
                .withMaterialShowcase()
                .setTarget(new ViewTarget(R.id.imagetabcat, this))
                .setContentTitle("WELCOME TO #HASHTAG")
                .singleShot(Showcaseint)
                .setStyle(R.style.showcasetitlestyle)
                .setContentText("Hey! Use your favourite #Hashtags. Happy #Hashtag")
                .setShowcaseEventListener(new OnShowcaseEventListener() {
                    @Override
                    public void onShowcaseViewHide(ShowcaseView showcaseView) {


                    }

                    @Override
                    public void onShowcaseViewDidHide(ShowcaseView showcaseView) {

                    }

                    @Override
                    public void onShowcaseViewShow(ShowcaseView showcaseView) {

                    }

                    @Override
                    public void onShowcaseViewTouchBlocked(MotionEvent motionEvent) {

                    }
                });
        showcase.build();
    }


}
