package adapter;

import java.util.Timer;
import java.util.TimerTask;

import at.grabner.circleprogress.CircleProgressView;

public class CounterTask extends TimerTask {
    private CircleProgressView progressView;
    private Timer timer;
    private boolean isCountDown;
    private int current;
    private int end;

    public CounterTask(CircleProgressView progressView, Timer timer) {
        this.progressView = progressView;
        this.timer = timer;
    }

    public void setCounter(boolean isCountDown, int start, int end, int block) {
        this.isCountDown = isCountDown;
        this.end = end;
        progressView.setValue(start);
        progressView.setMaxValue(isCountDown ? start : end);
        progressView.setBlockCount(block);
    }


    @Override
    public void run() {
        current = (int) progressView.getCurrentValue();
        if (current == end) {
            timer.cancel();
        } else {
            progressView.setValue(isCountDown ? --current : ++current);
            progressView.postOnAnimation(new Runnable() {
                @Override
                public void run() {
                    int min = 0;
                    int sec = (int) progressView.getCurrentValue();
                    if (sec >= 60) {
                        min = sec / 60;
                        sec %= 60;
                    }
                    String second = (sec > 9 ? "" : "0") + sec;
                    progressView.setText(min + ":" + second);
                }
            });
        }
    }
}
