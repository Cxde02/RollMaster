package com.example.rollmaster;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rollButton = findViewById(R.id.rollButton);
        final ImageView leftDice = findViewById(R.id.image_leftDice);
        final ImageView rightDice = findViewById(R.id.image_rightDice);
        final TextView sumTextView = findViewById(R.id.sumTextView);

        final int[] diceArray = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Rollmaster", "Pressed");

                // Rotate the dice for a smooth animation
                rotateDice(leftDice);
                rotateDice(rightDice);

                // Delay the setting of the new dice images and displaying the sum to allow time for the animation
                leftDice.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Random randomNumberGenerator = new Random();
                        int numberL = randomNumberGenerator.nextInt(6);
                        int numberR = randomNumberGenerator.nextInt(6);

                        leftDice.setImageResource(diceArray[numberL]);
                        rightDice.setImageResource(diceArray[numberR]);

                        int sum = numberL + numberR + 2; // Add 2 because the array indices start from 0
                        sumTextView.setText("You got: " + sum);
                    }
                }, 500); // Adjust the delay as needed
            }
        });
    }

    private void rotateDice(final ImageView dice) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(dice, "rotation", 0f, 360f);
        rotation.setDuration(500);

        // Set up an animator listener to handle the end of the rotation animation
        rotation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });

        rotation.start();
    }
}
