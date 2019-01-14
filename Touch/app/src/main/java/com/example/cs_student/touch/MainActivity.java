package com.example.cs_student.touch;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout layout;
    TextView text;
    TextView text2;
    TextView text3;
    int touchCount = 0;
    boolean fingerOnScreen = false;
    double Number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (ConstraintLayout) findViewById(R.id.theLayout);
        text = (TextView) findViewById(R.id.theText);
        text2 = (TextView) findViewById(R.id.theText2);
        text3 = (TextView) findViewById(R.id.theText3);

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchCount++;
                //String statusText = String.format("Touch Events: %d", touchCount);
                //text.setText(statusText);

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    fingerOnScreen = true;
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    fingerOnScreen = false;
                }

                if (fingerOnScreen){
                    layout.setBackgroundColor(Color.YELLOW);
                    text.setText("Rolling");
                }
                else {
                    layout.setBackgroundColor(Color.BLUE);
                    Number = Math.round(5*Math.random()+1);
                    String roll = String.format("%1.0f", Number);
                    text.setText(roll);
                }

                if(motionEvent.getAction() == MotionEvent.ACTION_MOVE && fingerOnScreen){
                    float xCoord = motionEvent.getX();
                    float yCoord = motionEvent.getY();

                    text.setX(xCoord - text.getWidth()/2);
                    text.setY(yCoord - text.getHeight()/2);

                    text3.setY(50);

                    text2.setText(String.valueOf(xCoord));
                    text3.setText(String.valueOf(yCoord));



                }

                return true;
            }
        });
    }
}
