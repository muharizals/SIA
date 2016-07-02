package com.tumpi.sia.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.tumpi.sia.Constant;
import com.tumpi.sia.R;
import com.tumpi.sia.fragments.EditEmailFragment;
import com.tumpi.sia.fragments.EditNameFragment;
import com.tumpi.sia.fragments.EditPasswordFragment;
import com.tumpi.sia.fragments.LoginFragment;

public class SettingActivity extends AppCompatActivity {

    private boolean isOpen;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        isOpen = false;
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.action_settings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void OpenEditName(View view){
        if(!isOpen){
            Fragment fragment = new EditNameFragment();
            openFragment(fragment);
        }
    }

    public void OpenEditEmail(View view){
        if(!isOpen){
            Fragment fragment = new EditEmailFragment();
            openFragment(fragment);
        }
    }


    public void OpenEditPassword(View view){
        if(!isOpen){
            Fragment fragment = new EditPasswordFragment();
            openFragment(fragment);
        }
    }


    private void openFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right,android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                .add(android.R.id.content, fragment)
                .addToBackStack(null)
                .commit();
        fragmentManager.executePendingTransactions();
        isOpen=true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            Intent intent = new Intent(Constant.notFinishmain);
            sendBroadcast(intent);
        }

        return super.onOptionsItemSelected(item);
    }



    public void logOutApp(View view){
       // mListener.logOutApp();
        Intent intent = new Intent(Constant.finishMain);
        sendBroadcast(intent);
        finish();
        Intent landing = new Intent(this,LandingActivity.class);
        startActivity(landing);
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0 ){
            getSupportFragmentManager().popBackStack();
            isOpen=false;
        } else {
            super.onBackPressed();
        }

    }
}
