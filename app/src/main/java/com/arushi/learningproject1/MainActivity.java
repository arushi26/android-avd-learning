package com.arushi.learningproject1;

//import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.graphics.drawable.Animatable2Compat.AnimationCallback;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    AnimatedVectorDrawableCompat mAnimatedVector;
    AnimationCallback mAnimationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        /* Loader - Squares */
        ImageView pb = findViewById(R.id.pb);

        /* https://stackoverflow.com/questions/41767676/how-to-restart-android-animatedvectordrawables-animations */
        mAnimatedVector = AnimatedVectorDrawableCompat.create(this, R.drawable.avd_animated_icon);
        pb.setImageDrawable(mAnimatedVector);
        final Handler mainHandler = new Handler(Looper.getMainLooper());

        if(mAnimatedVector != null) {
            mAnimationCallback = new AnimationCallback() {
                @Override
                public void onAnimationEnd(final Drawable drawable) {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mAnimatedVector.start();
                        }
                    });
                }
            };

            mAnimatedVector.registerAnimationCallback(mAnimationCallback);

            mAnimatedVector.start();
        }


//        /* Loader - Circle */
//        ImageView pbRound = findViewById(R.id.pb_round);
//        ((AnimatedVectorDrawable) pbRound.getBackground()).start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAnimatedVector.unregisterAnimationCallback(mAnimationCallback);
    }

}
