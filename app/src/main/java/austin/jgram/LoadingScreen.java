package austin.jgram;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 * Created by Austin on 5/18/16.
 */
public class LoadingScreen extends Activity {

    private Context context;

    private ViewSwitcher viewSwitcher;

    public LoadingScreen(Context con) {
        context = con;
    }
    public LoadingScreen() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);
        //ProgressBar progressBar = (ProgressBar) findViewById(R.id.activity_splash_progress_bar);
        //new LoadingTask(progressBar).execute();
        new LoadingTask().execute();
    }

    //public class LoadingTask extends AsyncTask<String, Integer, Integer> {
    private class LoadingTask extends AsyncTask {

        //public interface LoadingTaskFinishedListener {
        //    void onTaskFinished(); // If you want to pass something back to the listener add a param to this method
        //}

        // This is the progress bar you want to update while the task is in progress
        private ProgressBar progressBar;
        private TextView tv_progress;
        // This is the listener that will be told when this task is finished
        //private final LoadingTaskFinishedListener finishedListener;

        @Override
        protected void onPreExecute() {
            viewSwitcher = new ViewSwitcher(LoadingScreen.this);
            viewSwitcher.addView(ViewSwitcher.inflate(LoadingScreen.this, R.layout.activity_splash, null));
            ImageView img = (ImageView) viewSwitcher.findViewById(R.id.gif);
            Drawable draw = ResourcesCompat.getDrawable(getResources(), R.drawable.tali_animation, null);
            img.setBackground(draw);
            AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

            tv_progress = (TextView) viewSwitcher.findViewById(R.id.tv_progress);
            progressBar = (ProgressBar) viewSwitcher.findViewById(R.id.progressBar);
            progressBar.setMax(100);
            setContentView(viewSwitcher);
            frameAnimation.start();
        }


        @Override
        protected Object doInBackground(Object[] params) {
            try {
                synchronized (this) {
                    int counter = 0;
                    while (counter <= 4) {
                        this.wait(850);
                        counter++;
                        onProgressUpdate(counter*25);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        //@Override
        protected void onProgressUpdate(final Integer... result) {
            if (result[0] <= 100) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_progress.setText("Progress: " + Integer.toString(result[0]) + "%");
                        progressBar.setProgress(result[0]);
                    }
                });

            }
            else {
                onPostExecute(null);
            }
        }

        //@Override
        protected void onPostExecute(Void result) {
            //viewSwitcher.addView(ViewSwitcher.inflate(LoadingScreen.this, R.layout.content_grammar, null));
            //viewSwitcher.showNext();
            finish();
        }


        /*
        public LoadingTask(ProgressBar progressBar) {
            this.progressBar = progressBar;
            //this.finishedListener = finishedListener;
        }

        @Override
        protected Integer doInBackground(String... params) {
            Log.i("Tutorial", "Starting task with url: " + params[0]);
            if(resourcesDontAlreadyExist()){
                downloadResources();
            }
            // Perhaps you want to return something to your post execute
            return 1234;
        }

        private boolean resourcesDontAlreadyExist() {
            // Here you would query your app's internal state to see if this download had been performed before
            // Perhaps once checked save this in a shared preference for speed of access next time
            return true; // returning true so we show the splash every time
        }


        private void downloadResources() {
            // We are just imitating some process thats takes a bit of time (loading of resources / downloading)
            int count = 10;
            for (int i = 0; i < count; i++) {

                // Update the progress bar after every step
                int progress = (int) ((i / (float) count) * 100);
                publishProgress(progress);

                // Do some long loading things
                try { Thread.sleep(1000); } catch (InterruptedException ignore) {}
            }
        }*/




    }
    @Override
    public void onBackPressed() {
        if(viewSwitcher.getDisplayedChild() != 0) {
            super.onBackPressed();
        }
    }
/*
    public void onTaskFinished() {
        completeSplash();
    }

    private void completeSplash(){
        startApp();
        finish(); // Don't forget to finish this Splash Activity so the user can't return to it!
    }

    private void startApp() {
        //Intent intent = new Intent(LoadingScreen.this, MainActivity.class);
        Intent intent2 = new Intent(LoadingScreen.this, context.getClass());
        startActivity(intent2);
    }*/
}
