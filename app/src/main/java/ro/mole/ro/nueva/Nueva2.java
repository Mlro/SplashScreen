package ro.mole.ro.nueva;


import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.widget.ShareActionProvider;

import eu.inmite.android.lib.dialogs.ISimpleDialogListener;
import eu.inmite.android.lib.dialogs.SimpleDialogFragment;


public class Nueva2 extends SherlockFragmentActivity implements
        ISimpleDialogListener {


    private static final String TAG = "SplashScreenn";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Typeface bold = Typeface.createFromAsset(getAssets(), "Roboto-Bold.ttf");
        Typeface light = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
        TextView letter = new TextView(this);
        letter.setTypeface(bold);
        letter.setTextColor(0xFFFFFFFF);
        TextView tv = new TextView(this);
        if (light != null) tv.setTypeface(light);
        tv.setTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setContentView(R.layout.activity_nueva2);


        // enable the home button

        Button v = (Button) findViewById(R.id.button2);
    }

    public void credits(View view) {
        SimpleDialogFragment.createBuilder(this, getSupportFragmentManager()).setTitle(R.string.credits_title).setMessage(R.string.credits).show();}
        private ShareActionProvider mShareActionProvider;





    @Override
    public boolean onCreateOptionsMenu(final com.actionbarsherlock.view.Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        inflater.inflate(R.menu.share_action_provider, menu);



        mShareActionProvider = (ShareActionProvider) menu.findItem(R.id.emailButton).getActionProvider();
        Intent emailIntent = getDefaultShareIntent();
        if (emailIntent != null)
            mShareActionProvider.setShareIntent(emailIntent);
        return true;

    }



    private Intent getDefaultShareIntent() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"marin.dom64@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getText(R.string.email_subject));
        emailIntent.setType("plain/text");
        return emailIntent;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            int itemId = item.getItemId();
            if (itemId == R.id.more) {
                return false;
            } else if (itemId == R.id.aboutButton) {
                SimpleDialogFragment.createBuilder(this, getSupportFragmentManager()).setTitle(R.string.about_title).setMessage(R.string.dialog_about).show();
            } else if (itemId == R.id.rebootButton) {
                SimpleDialogFragment.createBuilder(this, getSupportFragmentManager()).setTitle(R.string.reboot_title).setMessage(R.string.reboot_message).setPositiveButtonText(R.string.positive_button).setNegativeButtonText(R.string.negative_button).setRequestCode(1).show();
            }

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

    private Intent shareIntent(String linkShare, String titleShare){
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, titleShare);
        intent.putExtra(Intent.EXTRA_TEXT, linkShare);
        return intent;
    }

    @Override
    public void onPositiveButtonClicked(int requestCode){
        if (requestCode == 1) {
            try {
                Process proc = Runtime.getRuntime().exec(new String[] { "su", "-c", "reboot" });
                proc.waitFor();
            } catch (Exception ex) {
                Log.i(TAG, "Could not reboot", ex);
            }
        }
    }
    @Override
    public void onNegativeButtonClicked(int requestCode) {
        if (requestCode == 1) {
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        Nueva2.this.finish();
    }

    public void activity1(View view) {
        Intent ct = new Intent(this,Nueva1.class);

        startActivity(ct);
    }

    public void pasarActividad3(View v) {

        Intent act = new Intent(this, Nueva3.class);

        startActivity(act);

    }
}
