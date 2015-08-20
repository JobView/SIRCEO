package com.xiankezu.sirceo.widghts;


import com.xiankezu.sirceo.R;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * @author sunday
 * 2013-12-04
 */
public class EditTextWithDel extends EditText {
	private final static String TAG = "EditTextWithDel";
	private Drawable imgInable;
	private Drawable imgAble;
	private Context mContext;

	public EditTextWithDel(Context context) {
		super(context);
		mContext = context;
		init();
	}

	public EditTextWithDel(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	public EditTextWithDel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init();
	}
	
	@Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
	
	private void init() {
		imgInable = mContext.getResources().getDrawable(R.drawable.widghts_delete_gray);
		imgAble = mContext.getResources().getDrawable(R.drawable.widghts_delete);
		addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				setDrawable();
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
		});
		setDrawable();
	}
	
	 // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 100;//设置点击距离
            if(rect.contains(eventX, eventY)) 
            	setText("");
        }
        return super.onTouchEvent(event);
    }

    //设置删除图片
	private void setDrawable() {
		if(length() < 1)
			setCompoundDrawablesWithIntrinsicBounds(null, null, imgInable, null);
		else
			setCompoundDrawablesWithIntrinsicBounds(null, null, imgAble, null);
	}

}
