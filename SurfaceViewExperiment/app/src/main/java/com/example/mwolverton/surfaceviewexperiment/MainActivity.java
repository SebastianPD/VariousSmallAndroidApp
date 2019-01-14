package com.example.mwolverton.surfaceviewexperiment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    Canvas cBuffer;
    Bitmap bmpBuffer;
    Paint p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView = new SurfaceView(this);
        setContentView(surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        p = new Paint();
        p.setColor(Color.MAGENTA);
        p.setStrokeWidth(3);

        OnTouchListener tl = new OnTouchListener() {
            boolean renderFrame = false;
            float lastX = 0;
            float lastY = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = event.getX();
                        lastY = event.getY();
                        cBuffer.drawCircle(event.getX(), event.getY(), 3, p);
                        renderFrame = true;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        cBuffer.drawLine(lastX, lastY, event.getX(), event.getY(), p);
                        lastX = event.getX();
                        lastY = event.getY();
                        renderFrame = true;
                        break;
                }
                if (renderFrame) {
                    Canvas c = surfaceHolder.lockCanvas();
                    c.drawBitmap(bmpBuffer, 0, 0, new Paint());
                    surfaceHolder.unlockCanvasAndPost(c);
                    renderFrame = false;
                }
                return true;
            }
        };
        surfaceView.setOnTouchListener(tl);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas c = holder.lockCanvas();
        bmpBuffer = Bitmap.createBitmap(c.getWidth(), c.getHeight(), Bitmap.Config.ARGB_8888);
        cBuffer = new Canvas(bmpBuffer);
        cBuffer.drawRGB(45, 45, 45);
        c.drawBitmap(bmpBuffer, 0, 0, new Paint());
        holder.unlockCanvasAndPost(c);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();

        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        setContentView(surfaceView);
    }


}
