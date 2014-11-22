package ro.mole.ro.nueva;



import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragmentActivity;

/**
 * Created by Juan on 19/11/2014.
 */
public class Splash3 extends SherlockFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if (NavUtils.getParentActivityName(this) != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_splash3);
        this.setupActionBar();
        this.setupButton();
    }

    private void setupButton() {
        Button b = (Button)this.findViewById(R.id.startSplash);
        b.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        parentButtonClicked(v);
                    }
                });
    }

    private void parentButtonClicked(View v) {
        animate();
    }

    private void animate() {
        ImageView imgView = (ImageView)findViewById(R.id.animacionImage);
        imgView.setVisibility(ImageView.VISIBLE);
        imgView.setBackgroundResource(R.drawable.loading);

        AnimationDrawable frameAnimation =
                (AnimationDrawable) imgView.getBackground();

        if (frameAnimation.isRunning()) {
            frameAnimation.stop();
        }
        else {
            frameAnimation.stop();
            frameAnimation.start();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Automatically handle hierarchical Up navigation if the proper
            // metadata is available.
            Intent upIntent = NavUtils.getParentActivityIntent(this);
            if (upIntent != null) {
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder b = TaskStackBuilder.create(this);
                    b.addParentStack(this);
                    b.startActivities();

                    finish();
                } else {
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        Splash3.this.finish();
    }
}