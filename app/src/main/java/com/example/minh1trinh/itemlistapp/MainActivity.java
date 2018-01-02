package com.example.minh1trinh.itemlistapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.minh1trinh.itemlistapp.fragment.ListItemFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNext:
                Intent intent = new Intent(this, RealmActivity.class);
                startActivity(intent);
                break;
        }

    }
}
