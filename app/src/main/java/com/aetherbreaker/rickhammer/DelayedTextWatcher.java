package com.aetherbreaker.rickhammer;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.Timer;
import java.util.TimerTask;

public class DelayedTextWatcher implements TextWatcher {
    private callbackFunction callback;
    private Timer timer = new Timer();
    private final long DELAY = 1000; // Milliseconds

    public interface callbackFunction {
        public void lambda(final Editable s);
    }

//    constructor that accepts a lambda function to be called on text change
    public DelayedTextWatcher(final callbackFunction callback) {
        this.callback = callback;
    }

    @Override public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) { }
    @Override public void onTextChanged(final CharSequence s, final int start, final int before, final int count) { }

    @Override
    public void afterTextChanged(final Editable s) {
        timer.cancel();
        timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        callback.lambda(s);
                    }
                },
                DELAY
        );
    }
}