package com.tumpi.sia.activity;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tumpi.sia.Constant;
import com.tumpi.sia.MainActivity;
import com.tumpi.sia.R;
import com.tumpi.sia.fragments.LoginFragment;
import com.tumpi.sia.fragments.RegisterFragment;
import com.tumpi.sia.fragments.ResetPaswwordFragment;

public class LandingActivity extends AppCompatActivity implements LoginFragment.OnLoginInteractionListener{
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    ImageButton mNextBtn;
    Button signinbtn,signupbtn;

    ImageView zero,one,two;
    ImageView[] indicators;

    CoordinatorLayout mCoordinator;
    private boolean isopen;
    int page=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black_trans80));
        }

        setContentView(R.layout.activity_landing);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        isopen=false;


        signinbtn = (Button)findViewById(R.id.signinbutton);
        signupbtn = (Button)findViewById(R.id.signupbutton);

        zero = (ImageView)findViewById(R.id.intro_indicator_0);
        one = (ImageView)findViewById(R.id.intro_indicator_1);
        two = (ImageView)findViewById(R.id.intro_indicator_2);

        mCoordinator = (CoordinatorLayout)findViewById(R.id.main_content);

        indicators = new ImageView[]{zero,one,two};

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setCurrentItem(page);
        updateIndicators(page);

        final int color1 = ContextCompat.getColor(this, R.color.red);
        final int color2 = ContextCompat.getColor(this, R.color.purple);
        final int color3 = ContextCompat.getColor(this, R.color.cyan);

        final int[] colorList = new int[]{color1, color2, color3};

        final ArgbEvaluator evaluator = new ArgbEvaluator();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //color update
                int colorUpdate = (Integer) evaluator.evaluate(positionOffset,colorList[position],colorList[position==2?position :position+1]);
                mViewPager.setBackgroundColor(colorUpdate);
            }

            @Override
            public void onPageSelected(int position) {
                page = position;
                updateIndicators(page);
                switch (position){
                    case 0 :mViewPager.setBackgroundColor(color1);break;
                    case 1 :mViewPager.setBackgroundColor(color2);break;
                    case 3 : mViewPager.setBackgroundColor(color3);break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        signinbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!isopen){
                    Fragment fragment = new LoginFragment();
                    openFragment(fragment);
                }
            }
        });


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isopen){
                    Fragment fragment = new RegisterFragment();
                    openFragment(fragment);
                }
            }
        });

    }

    Intent putectraintent(Intent i, int pg){
        switch (pg){
            case 0 : i.putExtra(Constant.warna,R.color.red);break;
            case 1 : i.putExtra(Constant.warna,R.color.purple);break;
            case 2 : i.putExtra(Constant.warna,R.color.cyan);break;
        }
        return i;
    }

    public void finishLoginActivity(){
        onBackPressed();
        finish();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void testInteraction(View view){
        Fragment fragment = new ResetPaswwordFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right,android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                .add(android.R.id.content, fragment)
                .addToBackStack(null)
                .commit();
        fragmentManager.executePendingTransactions();
        isopen=true;
    }


    private void openFragment(Fragment fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right,android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .add(android.R.id.content, fragment)
                    .addToBackStack(null)
                    .commit();
            fragmentManager.executePendingTransactions();
            isopen=true;
    }

    void maketimeoutpager(){
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        if(page == 2){
                            page = 0;
                        }else{
                            page += 1;
                        }
                        mViewPager.setCurrentItem(page,true);
                        // maketimeoutpager();
                    }
                }, 10000);
    }


    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0 ){
            getSupportFragmentManager().popBackStack();
            isopen=false;
        } else {
            super.onBackPressed();
        }

    }


    void updateIndicators(int position){
        for (int i=0;i<indicators.length;i++){
            indicators[i].setBackgroundResource(i == position ? R.drawable.indicator_selected : R.drawable.indicator_unselected);
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        ImageView img;
        int[] bgs = new int[]{R.drawable.ic_assignment_black_24dp,R.drawable.ic_wb_cloudy_black_24dp,R.drawable.ic_assignment_turned_in_black_24dp};

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_landing, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            TextView desctextVie = (TextView)rootView.findViewById(R.id.text_description);

            int a = getArguments().getInt(ARG_SECTION_NUMBER);
            switch (a){
                case 1 :textView.setText(getResources().getText(R.string.landing_title_one));
                    desctextVie.setText(getResources().getText(R.string.landing_desc_one));break;
                case 2 :textView.setText(getResources().getText(R.string.landing_title_two));
                    desctextVie.setText(getResources().getText(R.string.landing_desc_two));break;
                case 3 :textView.setText(getResources().getText(R.string.landing_title_three));
                    desctextVie.setText(getResources().getText(R.string.landing_desc_three));break;
            }
            img = (ImageView)rootView.findViewById(R.id.section_img);
            img.setBackgroundResource(bgs[getArguments().getInt(ARG_SECTION_NUMBER)-1]);

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return Constant.sectionone;
                case 1:
                    return Constant.sectiontwo;
                case 2:
                    return Constant.sectionthree;
            }
            return null;
        }
    }
}
