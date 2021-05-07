package com.example.deep.androidtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        *//**Making this activity, full screen *//*
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_splash);
        ImageView image=findViewById(R.id.mylogo);
        //u have to load your animation
        Animation anim=AnimationUtils.loadAnimation(this,R.anim.nav_default_enter_anim);
        image.setAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent in=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(in);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}