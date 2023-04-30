package com.aetherbreaker.rickhammer;

import android.text.Editable;
import android.text.TextWatcher;

public class DelayedTextWatcher implements TextWatcher {
    final android.os.Handler handler = new android.os.Handler();
    Runnable runnable;
    Callback callback;

    public interface Callback {
        void run(final Editable s);
    }

    public DelayedTextWatcher(Callback callback) {
        this.callback = callback;
    }

    public void onTextChanged(final CharSequence s, int start, final int before, int count) {
        handler.removeCallbacks(runnable);
    }

    @Override
    public void afterTextChanged(final Editable s) {
        // Show some progress, because you can access UI here
        runnable = new Runnable() {
            @Override
            public void run() {
                callback.run(s);
            }
        };
        handler.postDelayed(runnable, 500);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
};