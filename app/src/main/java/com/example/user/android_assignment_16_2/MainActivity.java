package com.example.user.android_assignment_16_2;
//Package objects contain version information about the implementation and specification of a Java package.
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    //public is a method and fields can be accessed by the members of any class.
    //class is a collections of objects.
    //created MainActivity and extends with AppCompatActivity which is Parent class.

    public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
        //AsyncTask is designed to be a helper class around Thread and Handler and does not constitute a generic threading framework
        ProgressBar myProgressBar;
        //A user interface element that indicates the progress of an operation.

        public MyAsyncTask(ProgressBar target) {
            myProgressBar = target;
        }

        @Override
        //we use override to tells the compiler that the following method overrides a method of its superclass.
        protected Void doInBackground(Void... params) {
            //A user interface element that indicates the progress of an operation.
            //This method can be invoked from doInBackground(Params...) to publish updates on the UI thread while the background computation is still running.
            for(int i=0; i<100; i++){
                publishProgress(i);
                SystemClock.sleep(100);
                //thread will stop after given milli seconds
            }
            return null;
        }

        @Override
        //we use override to tells the compiler that the following method overrides a method of its superclass.
        protected void onProgressUpdate(Integer... values) {
            myProgressBar.setProgress(values[0]);
        }
        //Runs on the UI thread after publishProgress(Progress...) is invoked
    }

    Button buttonStart;
    //Intializing the variables.
    ProgressBar progressBar1, progressBar2, progressBar3,progressBar4;
    //Intializing the variables.
    MyAsyncTask asyncTask1, asyncTask2, asyncTask3,asyncTask4;
    //Intializing the variables.

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    //Indicates that Lint should treat this type as targeting a given API level, no matter what the project target is.
    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    protected void onCreate(Bundle savedInstanceState) {
        //protected can be accessed by within the package and class and subclasses
        //The Void class is an uninstantiable placeholder class to hold a reference to the Class object
        //representing the Java keyword void.
        //The savedInstanceState is a reference to a Bundle object that is passed into the onCreate method of every Android Activity
        // Activities have the ability, under special circumstances, to restore themselves to a previous state using the data stored in this bundle.
        super.onCreate(savedInstanceState);
        //Android class works in same.You are extending the Activity class which have onCreate(Bundle bundle) method in which meaningful code is written
        //So to execute that code in our defined activity. You have to use super.onCreate(bundle)
        setContentView(R.layout.activity_main);
        //R means Resource
        //layout means design
        //main is the xml you have created under res->layout->main.xml
        //Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        //he other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        //the design


        progressBar1 = (ProgressBar)findViewById(R.id.progressbar1);
        progressBar2 = (ProgressBar)findViewById(R.id.progressbar2);
        progressBar3 = (ProgressBar)findViewById(R.id.progressbar3);
        progressBar4 = (ProgressBar)findViewById(R.id.progressbar4);
        ///findViewById:A user interface element that displays text to the user.
        buttonStart = (Button)findViewById(R.id.start);
        //giving id to the button
        buttonStart.setOnClickListener(new View.OnClickListener(){

            @Override
            //we use override to tells the compiler that the following method overrides a method of its superclass.
            public void onClick(View v) {
                final boolean API_LEVEL_11 = android.os.Build.VERSION.SDK_INT > 11;
                //we will give the limit of android version. we will give progress for each bar
                progressBar1.setProgress(0);
                progressBar2.setProgress(0);
                progressBar3.setProgress(0);
                progressBar4.setProgress(0);
                //setting the progress bar to 0.
                asyncTask1 = new MyAsyncTask(progressBar1);
                asyncTask2 = new MyAsyncTask(progressBar2);
                asyncTask3 = new MyAsyncTask(progressBar3);
                asyncTask4 = new MyAsyncTask(progressBar4);
                //creating the object for async task
                if(API_LEVEL_11)
                //if pool is api level then the execute
                {
                    asyncTask1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    asyncTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    asyncTask3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    asyncTask4.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    //An Executor that can be used to execute tasks in parallel.
                }

            }});

    }

    @SuppressLint("NewApi")
    //Indicates that Lint should ignore the specified warnings for the annotated element.
    private void StartAsyncTaskInParallel(MyAsyncTask task) {
        //this method is two run the task parallely
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        //it will excute the thread.
    }

}

