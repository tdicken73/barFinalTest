package com.foodcycle.foodcycle;

import java.util.Locale;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the application.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
            // Return a DummySectionFragment (defined as a static inner class
            // below) with the page number as its lone argument.
        	switch (position) {
        	 case 0: return FragmentMenu.newInstance();
             case 1: return FragmentHungry.newInstance();
             case 2: return Inventory.newInstance();
             default: return Inventory.newInstance();
        	}
        	
     
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 3;
        }
        

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            PagerTitleStrip titleStrip = (PagerTitleStrip) findViewById(R.id.pager_title_strip);
            titleStrip.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
            titleStrip.setTextColor(0xFFFFFFFF);
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                	return getString(R.string.inventory).toUpperCase(l);
            }
            return null;
        }
    }
    

}

