package com.testphase.materialtest.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.testphase.materialtest.R;
import com.testphase.materialtest.layout.NavigationDrawerFragment;
import com.testphase.materialtest.layout.PrimaryListFragment;
import com.testphase.materialtest.services.MyService;

import me.tatarka.support.job.JobInfo;
import me.tatarka.support.job.JobScheduler;


public class MainActivity extends AppCompatActivity {

    //private static final int JOB_ID = 100;
    private Toolbar toolbar;
    //private RecyclerView mRecyclerView;
    //private RecyclerView.LayoutManager mLayoutManager;
    //private RecyclerView.Adapter mAdapter;

    //private JobScheduler mJobScheduler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_appbar);

        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Background Processes
        /*mJobScheduler = JobScheduler.getInstance(this);
        constructJob();*/

        //Navigation Drawer
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        //Recyclerview main list
        PrimaryListFragment primaryListFragment = (PrimaryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_primary_list);
        primaryListFragment.newInstance("","");

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(this, "You have clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*private void constructJob(){
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, new ComponentName(this, MyService.class));
        builder.setPeriodic(20000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true);

        mJobScheduler.schedule(builder.build());
    }*/

}
