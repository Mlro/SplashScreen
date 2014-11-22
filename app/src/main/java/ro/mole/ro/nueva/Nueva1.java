package ro.mole.ro.nueva;


import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.actionbarsherlock.widget.ShareActionProvider;

import eu.inmite.android.lib.dialogs.SimpleDialogFragment;

/**
 * Created by Juan on 04/11/2014.
 */
public class Nueva1 extends SherlockFragmentActivity {

    private AnimationDrawable mFrameAnimation;
    private LinearLayout mContLoading;
    private Button v;





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
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        shareTitle = "Comparte ...";
        shareUrl = "http://www.nosinmiubuntu.com/2012/06/como-utilizar-la-actionbar-de-sherlock.html";

        setContentView(R.layout.activity_nueva1);




        Button v = (Button) findViewById(R.id.button1);

        mContLoading = (LinearLayout) findViewById(R.id.contLoading);
        ImageView mImageLoading = (ImageView) findViewById(R.id.loadingView);
        mImageLoading.setBackgroundResource(R.drawable.cargando);

        // Get the background, which has been compiled to an AnimationDrawable object.
        mFrameAnimation = (AnimationDrawable) mImageLoading.getBackground();
        boolean firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
        if (firstrun){
            SimpleDialogFragment.createBuilder(this, getSupportFragmentManager()).setTitle(R.string.enter_title).setMessage(R.string.dialog_enter).show();
            // Save the state
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("firstrun", false)
                    .commit();}

        new LongOperation().execute("");
    }
    protected void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    protected void setSubTitle(String subtitle){
        getSupportActionBar().setSubtitle(subtitle);
    }

    private String shareTitle;
    private String shareUrl;
    protected void setShare(String shareTitle, String shareUrl) {
        this.shareTitle = shareTitle;
        this.shareUrl = shareUrl;
        actionProvider.setShareIntent(shareIntent(this.shareUrl, this.shareTitle));
    }

    ShareActionProvider actionProvider;

    protected SubMenu subMenu;



    /**
     * Asyntask to simulate a background job
     */
    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            //Start  Loading Animation
            mContLoading.setVisibility(View.VISIBLE);
            mFrameAnimation.start();
        }

        @Override
        protected String doInBackground(String... params) {
            //ToDo your Network Job/Request etc. here
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {
            //ToDo with result you got from Task
            //Stop Loading Animation
            mFrameAnimation.stop();
            mContLoading.setVisibility(View.GONE);

        }
    }



    private ShareActionProvider mShareActionProvider;

    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportMenuInflater().inflate(R.menu.main, menu);

        MenuItem actionItem = menu.findItem(R.id.menu_item_share_action_provider_action_bar);
        actionProvider = (ShareActionProvider) actionItem.getActionProvider();
        actionProvider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);

        actionProvider.setShareIntent(shareIntent(shareUrl, shareTitle));

        subMenu = menu.addSubMenu("Opciones");
        subMenu.add("About")
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(Nueva1.this,About.class);

                        startActivity(intent);
                        return true;
                    }

                });
        subMenu.add("Otro")
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        //Implementar aquí la navegación necesaria
                        return false;
                    }
                });

        subMenu.getItem().setIcon(R.drawable.abs__ic_menu_moreoverflow_holo_light);
        subMenu.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);


        return super.onCreateOptionsMenu(menu);
    }

    public void addSubMenu(String title, MenuItem.OnMenuItemClickListener l){
        subMenu.add(title)
                .setOnMenuItemClickListener(l);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);


    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        Nueva1.this.finish();
    }

    public void activity1(View view) {
        Intent act = new Intent(this,SplashScreen.class);

        startActivity(act);

    }
    public void activity2(View v){
        Intent intent = new Intent(this,ro.mole.ro.nueva.Nueva2.class);
        startActivity(intent);
    }
    private Intent shareIntent(String linkShare, String titleShare){
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, titleShare);
        intent.putExtra(Intent.EXTRA_TEXT, linkShare);
        return intent;
    }
}
