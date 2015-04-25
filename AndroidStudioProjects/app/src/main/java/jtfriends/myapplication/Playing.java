package jtfriends.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Yun Seongjun on 2015-04-16.
 */
public class Playing extends Activity{

    boolean buttonClicked=true;
    AnimationDrawable animDrawable;
    LinearLayout man;
    LinearLayout leftStart;
    TextView myPower;
    ImageView saliva;
    long now;
    int power;
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            now = Calendar.getInstance().getTimeInMillis();

        } else if(event.getAction()==MotionEvent.ACTION_UP){
            power=(int)(Calendar.getInstance().getTimeInMillis()-now)/10;
            myPower.setText("Power : "+power);
            spit();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing);

        //전체화면
        final Window win = getWindow();
        win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //찾아주기
        man = (LinearLayout) findViewById(R.id.man);
        leftStart = (LinearLayout) findViewById(R.id.leftStart);
        myPower = (TextView) findViewById(R.id.power);
        saliva = (ImageView) findViewById(R.id.saliva);

        //스머프 애니메이션
        initFrameAnimation();
        startAnimation();
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
        leftStart.startAnimation(anim);



    }


    private void initFrameAnimation(){
        Resources res = getResources();
        int duration = 100;
        animDrawable = new AnimationDrawable();
        animDrawable.setOneShot(false);
        animDrawable.addFrame(res.getDrawable(R.drawable.walk1), duration);
        animDrawable.addFrame(res.getDrawable(R.drawable.walk2), duration);
        animDrawable.addFrame(res.getDrawable(R.drawable.walk3), duration);
        animDrawable.addFrame(res.getDrawable(R.drawable.walk4), duration);
        animDrawable.addFrame(res.getDrawable(R.drawable.walk5), duration);
        animDrawable.addFrame(res.getDrawable(R.drawable.walk6), duration);
        animDrawable.addFrame(res.getDrawable(R.drawable.walk7), duration);
        animDrawable.addFrame(res.getDrawable(R.drawable.walk8), duration);
    }

    @TargetApi(16)
    private void startAnimation() {
        leftStart.setBackground(animDrawable);
        animDrawable.setVisible(true, true);
        animDrawable.start();
    }
    private void stopAnimation() {
        animDrawable.stop();
        animDrawable.setVisible(false, false);
    }



    private void manInflater() {
        LinearLayout man = (LinearLayout) findViewById(R.id.leftStart);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.man, man, true);
    }
    private void spit(){
        saliva.setVisibility(View.VISIBLE);
        Animation animSaliva = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translatesaliva);
        saliva.startAnimation(animSaliva);
    }

}
