package com.test.movieinformation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.test.movieinformation.ui.home.AddMovies;
import com.test.movieinformation.ui.SharedPreferences.SharedFragment;

public class MainActivity extends AppCompatActivity {
    //Initialize toolbar
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set navigation drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // set custom toggle menu to open and close drawer
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        //set menu icon
        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        //set default fragment which will open first
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,
                    new AddMovies()).commit();
        }
        //set navigation menu item click event
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,
                                new AddMovies()).commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_movieList:
                        Intent intent = new Intent(MainActivity.this, ShowMovieList.class);
                        startActivity(intent);
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_menu_favorite:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,
                                new SharedFragment()).commit();
                        drawer.closeDrawers();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_help) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Interface")
                    .setMessage("It is used to achieve total abstraction. Since java does not support multiple inheritance in case of class, but by using interface it can achieve multiple inheritance . It is also used to achieve loose coupling. Interfaces are used to implement abstraction.")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
}