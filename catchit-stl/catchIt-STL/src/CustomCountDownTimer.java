package com.flysystems.catchit_stl;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

public class CustomCountDownTimer extends CountDownTimer {
    LineFragment lf;
    
    public CustomCountDownTimer( LineFragment lf, long elapseTimeMillisec, long interval) {
        super( elapseTimeMillisec, interval);
    	Log.d("CountDownTimer.constructor", "station = " + lf.getStation().getName() + " route = " + lf.getRoute().getName());
    	Log.d("CountDownTimer.constructor", "elapseTimeMillisec = " + elapseTimeMillisec + " interval = " + interval);
        this.lf = lf;
    }

        @Override
       public void onTick(long millisUntilFinished) {
            int mins = ((int)millisUntilFinished / 1000) / 60;
            int secs = ((int)millisUntilFinished / 1000) % 60;
//          tv.setText(mins + ":" + secs);
//          tv.setText( String.format("%1$tM:%1$ts", delta));
            lf.getTextView().setText( String.format("%1$02d:%2$02d", mins, secs));
        }

        @Override
       public void onFinish() {
//          timerTextView.setText("done!");
        	Log.d("CountDownTimer.onFinish", "station = " + lf.getStation().getName() + " route = " + lf.getRoute().getName());
            lf.init();
        }
}
