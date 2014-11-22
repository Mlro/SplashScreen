package ro.mole.ro.nueva;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Window;

import eu.inmite.android.lib.dialogs.SimpleDialogFragment;


public class SplashScreen extends SherlockFragmentActivity {

    private AnimationDrawable mFrameAnimation;
    protected ImageView mImageLoading;
    private LinearLayout mContLoading;
    public static final int segundos=8;
    public static final int milesegundos=segundos*1000;
    public static final int delay=2;
    private ProgressBar progressbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        Typeface bold = Typeface.createFromAsset(getAssets(), "Roboto-Bold.ttf");
        Typeface light = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
        TextView letter = new TextView(this);
        letter.setTypeface(bold);
        letter.setTextColor(0xFFFFFFFF);
        TextView tv = new TextView(this);
        if (light != null) tv.setTypeface(light);
        tv.setTextColor(0xFFFFFFFF);
        setSupportProgressBarIndeterminateVisibility(false);

        setContentView(R.layout.activity_splashscreen);
        boolean firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
        if (firstrun){
            SimpleDialogFragment.createBuilder(this, getSupportFragmentManager()).setTitle(R.string.hello_world_bienvenido).setMessage(R.string.advertencia).show();
            // Save the state
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("firstrun", false)
                    .commit();}

        mContLoading = (LinearLayout) findViewById(R.id.contLoading);
        mImageLoading = (ImageView) findViewById(R.id.loadingView);
        mImageLoading.setBackgroundResource(R.drawable.loading);

        // Get the background, which has been compiled to an AnimationDrawable object.
        mFrameAnimation = (AnimationDrawable) mImageLoading.getBackground();

        new LongOperation().execute("");
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        progressbar.setMax(maximo_progreso());
        empezaranimacion();

    }

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

    public void empezaranimacion()
    {
        new CountDownTimer(milesegundos, 1000) {

            @Override
            public void onTick(long l) {
                progressbar.setProgress(establecer_progreso(l));
            }

            @Override
            public void onFinish() {
                Intent nuevofrom=new Intent(SplashScreen.this, Nueva1.class);
                startActivity(nuevofrom);
                finish();
            }
        }.start();
    }

    public int establecer_progreso (long milesegunds)
    {
        return (int) ((milesegundos-milesegunds)/1000);
    }

    public int maximo_progreso()
    {
        return segundos-delay;
    }









}
