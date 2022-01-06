package com.bruce.dice.mydroidcaffe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
   // private TextView mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // mShareTextEditText = findViewById(R.id.social_icons2);
        //inflate toolbar
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);// replaces action bar with tool bar

        //set up the Navigation drawer
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        //set up the navigation view to listen for menu item selection
        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.nav_pizza:
                        Intent pizzaIntent = new Intent(MainActivity.this, PizzaActivity.class);
                        startActivity(pizzaIntent);
                        break;
                    case R.id.nav_cocktails:
                        Intent cocktailsIntent = new Intent(MainActivity.this, CocktailsActivity.class);
                        startActivity(cocktailsIntent);
                        break;
                    case R.id.nav_pasta:
                        Intent pastaIntent = new Intent(MainActivity.this, PastaActivity.class);
                        startActivity(pastaIntent);
                        break;
                    case R.id.contact_us:
                        Uri myUri = Uri.parse("tel:0723750923");
                        Intent callIntent = new Intent(Intent.ACTION_DIAL, myUri);
                        startActivity(callIntent);
                        break;
                    case R.id.about_us:
                        String url = "https://www.javahouseafrica.com/our-story/";
                        Uri webPage = Uri.parse(url);
                        Intent aboutIntent = new Intent(Intent.ACTION_VIEW, webPage);
                        if(aboutIntent.resolveActivity(getPackageManager()) != null){
                            startActivity(aboutIntent);
                        }
                        break;
                    case R.id.share_app:
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT,
                                "Hey check out my app at: https://play.google.com/store/apps/details?id=" + com.bruce.dice.mydroidcaffe.BuildConfig.APPLICATION_ID);
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                        break;
                }

                return true;
            }
        });




        //create an instance of the tabllayout and add the tabs
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_3));
        //set the tab to fill the entire field
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //use the pagerAdapter to connect screens
        //create an instance of the view pager
        final ViewPager viewPager = findViewById(R.id.view_pager);
        //create an instance of the pagerAdapter
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        //set adapter to the viewpager
        viewPager.setAdapter(pagerAdapter);
        //set listener for clicks
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}