package com.example.apple.oldfriend.weidge;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by apple on 16/5/3.
 */
public class UnScrollLisiView extends ListView {
    public UnScrollLisiView(Context context) {
        super(context);
    }

    public UnScrollLisiView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnScrollLisiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UnScrollLisiView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                return true;
            }
            default: {
                return super.onTouchEvent(ev);
            }
        }

    }
}
