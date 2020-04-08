package com.example.gradingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;

    //create database instance
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeElements();
        initializeDatabase();
        loadFragment(new ViewGrades());
        setupNavigationDrawer();
    }

    private void initializeDatabase() {
        //try creating database if not already created
        db = openOrCreateDatabase("Grades", Context.MODE_PRIVATE,null);
        //try creating table in the database if it doesn't exist
        db.execSQL("CREATE TABLE IF NOT EXISTS grades(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "firstName TEXT," +
                "lastName TEXT," +
                "marks INTEGER," +
                "course TEXT," +
                "credit INTEGER)");
    }

    private void setupNavigationDrawer() {
        NavigationView navigationView = findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                int item = menuItem.getItemId();
                if(item == R.id.gradeEntry){
                    fragment = new EnterGrade();
                }
                else if(item == R.id.searchGrades){
                    fragment = new SearchGrades();
                }
                else if(item == R.id.viewGrade){
                    fragment = new ViewGrades();
                }
                else if(item == R.id.updateGrade){
                    fragment = new UpdateGrades();
                }
                else if(item == R.id.deleteGrade){
                    fragment = new DeleteGrades();
                }
                if(fragment != null){
                    loadFragment(fragment);
                    return true;
                }
                return false;
            }
        });
    }

    private void initializeElements() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.nav_drawer);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
    }
    private void loadFragment(Fragment fragment){
        //create fragment manager
        FragmentManager fmanager = getSupportFragmentManager();
        //create transaction to replace the fragment
        FragmentTransaction ftrans = fmanager.beginTransaction();
        //replace the framelayout with new fragment
        ftrans.replace(R.id.fragment,fragment);
        //commit the transaction/replace the fragment
        ftrans.commit();

        drawerLayout.closeDrawers();
    }
}
