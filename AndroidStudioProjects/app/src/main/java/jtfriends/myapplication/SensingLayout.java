package jtfriends.myapplication;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Yun Seongjun on 2015-04-19.
 */
public class SensingLayout extends LinearLayout {

    public SensingLayout(Context context) {
        super(context);
    }

    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            Toast.makeText(super.getContext(),"ACTION_DOWN", Toast.LENGTH_SHORT).show();
        }


        return super.onTouchEvent(event);
    }

}
