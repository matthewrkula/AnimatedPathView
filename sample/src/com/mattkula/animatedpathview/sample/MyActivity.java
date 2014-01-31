package com.mattkula.animatedpathview.sample;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import com.mattkula.animatedpathview.library.AnimatedPathView;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final AnimatedPathView view = (AnimatedPathView)findViewById(R.id.animated_path);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                float[][] points = new float[][]{
                        {0, 0},
                        {view.getWidth(), 0},
                        {view.getWidth(), view.getHeight()},
                        {0, view.getHeight()},
                        {0, 0},
                        {view.getWidth(), view.getHeight()},
                        {view.getWidth(), 0},
                        {0, view.getHeight()}
                };
                view.setPath(points);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(view, "percentage", 0.0f, 1.0f);
                anim.setDuration(2000);
                anim.setInterpolator(new LinearInterpolator());
                anim.start();
            }
        });
    }
}
