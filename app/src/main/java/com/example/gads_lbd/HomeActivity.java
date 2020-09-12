package com.example.gads_lbd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gads_lbd.adapters.SectionsPageAdapter;
import com.example.gads_lbd.ui.LearningFragment;
import com.example.gads_lbd.ui.SkillsFragment;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new LearningFragment(), "Learning Leaders");
        adapter.addFragment(new SkillsFragment(), "Skill IQ Leaders");
        viewPager.setAdapter(adapter);

    }

    public void submit(View view){

        Intent intent = new Intent(this, SubmissionActivity.class);
        startActivity(intent);

    }
}