package ro.mole.ro.nueva;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import eu.inmite.android.lib.dialogs.SimpleDialogFragment;
import static com.nineoldandroids.view.ViewPropertyAnimator.animate;
@SuppressWarnings("ALL")
public class About extends SherlockFragmentActivity  {
    ImageView image;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (NavUtils.getParentActivityName(this) != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_about);
    }



    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void rotate(View view) {
        image = (ImageView) findViewById(R.id.image_about);
        image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                animate(image)
                        .rotationBy((Math.random() > 0.5f ? 360 : -360))
                        .setInterpolator(new DecelerateInterpolator())
                        .setDuration(700).start();
            }
        });
    }

    public void link(View view){
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://github.com/Mlro/SplashScreen")));}
    public void creditos(View view){
        SimpleDialogFragment.createBuilder(this, getSupportFragmentManager()).setTitle(R.string.credits_title).setMessage(R.string.credits).show();}

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
        About.this.finish();
    }

    public void about(View view) {
        Intent act = new Intent(this,Nueva1.class);

        startActivity(act);

    }


}
