package adapter;

import java.util.Timer;
import java.util.TimerTask;

import at.grabner.circleprogress.CircleProgressView;

public class CounterTask extends TimerTask {
    private CircleProgressView progressView;
    private Timer timer;
    private boolean isCountDown;
    private int min;
    private int max;
    private int current;

    public CounterTask(CircleProgressView progressView, Timer timer) {
        this.progressView = progressView;
        this.timer = timer;
    }

    public void setCounter(boolean isCountDown, int min, int max, int current, int block) {
        this.isCountDown = isCountDown;
        this.min = min;
        this.max = max;
        this.current = current;
        progressView.setValue(current);
        progressView.setMaxValue(max);
        progressView.setBlockCount(block);
    }


    @Override
    public void run() {
        current = (int) progressView.getCurrentValue();
        if (isCountDown && current == min || !isCountDown && current == max) {
            timer.cancel();
        } else {
            progressView.setValue(isCountDown ? --current : ++current);
            progressView.postOnAnimation(new Runnable() {
                @Override
                public void
                run() {
                    int minutes = 0;
                    int seconds = (int) progressView.getCurrentValue();
                    if (seconds >= 60) {
                        minutes = seconds / 60;
                        seconds %= 60;
                    }
                    String second = (seconds > 9 ? "" : "0") + seconds;
                    progressView.setText(minutes + ":" + second);
                }
            });
        }
    }
}
