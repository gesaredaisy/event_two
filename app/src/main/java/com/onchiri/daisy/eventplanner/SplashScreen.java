package com.onchiri.daisy.eventplanner;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {


    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asplashscreen);
        img= (ImageView)findViewById(R.id.imageView2);
        ConstraintLayout constraintLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.splashscrenntransitions);
        img.startAnimation(myanim);
        final Intent i = new Intent(this,MainActivity.class);
        Thread timer = new Thread() {

            public void run() {

                try {

                    sleep(5000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();
                }
            }
        };
            timer.start();

    }



}
