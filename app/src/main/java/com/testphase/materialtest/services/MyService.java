package com.testphase.materialtest.services;

import android.os.AsyncTask;

import me.tatarka.support.job.JobParameters;
import me.tatarka.support.job.JobService;

/**
 * Created by deea on 16/01/16.
 */
public class MyService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        //L.t(this, "OnStartJob");
        new MyTask(this).execute(jobParameters);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    private static class MyTask extends AsyncTask<JobParameters, Void, JobParameters>{

        MyService myService;
        MyTask(MyService myService){
            this.myService = myService;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JobParameters doInBackground(JobParameters... params) {
            return params[0];
        }

        @Override
        protected void onPostExecute(JobParameters jobParameters) {
            myService.jobFinished(jobParameters, false);
        }
    }
}
