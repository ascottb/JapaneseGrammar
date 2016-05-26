package austin.jgram;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by Austin on 5/18/16.
 */
public class LoadingScreen extends Activity {

    private Context context;

    public LoadingScreen(Context con) {
        context = con;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.activity_splash_progress_bar);
        new LoadingTask(progressBar).execute();
    }

    public class LoadingTask extends AsyncTask<String, Integer, Integer> {

        //public interface LoadingTaskFinishedListener {
        //    void onTaskFinished(); // If you want to pass something back to the listener add a param to this method
        //}

        // This is the progress bar you want to update while the task is in progress
        private final ProgressBar progressBar;
        // This is the listener that will be told when this task is finished
        //private final LoadingTaskFinishedListener finishedListener;

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
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]); // This is ran on the UI thread so it is ok to update our progress bar ( a UI view ) here
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            onTaskFinished(); // Tell whoever was listening we have finished
        }
    }

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
    }
}
