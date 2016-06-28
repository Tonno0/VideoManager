package com.example.video;

import java.lang.reflect.Field;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.VideoView;


public class MyVideoView extends VideoView {

	Field mVideoWidth = null;
	Field mVideoHeight = null;
	private int scrw, scrh = 0;

	public MyVideoView(Context context) {
		super(context);
		try {
			mVideoWidth = VideoView.class.getDeclaredField("mVideoWidth");
			mVideoHeight = VideoView.class.getDeclaredField("mVideoHeight");
			mVideoWidth.setAccessible(true);
			mVideoHeight.setAccessible(true);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		scrw = dm.widthPixels;
		scrh = dm.heightPixels;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = 0;
		int height = 0;
		try {
			int w = mVideoWidth.getInt(this);
			int h = mVideoHeight.getInt(this);
			width = getDefaultSize(w, widthMeasureSpec);
			height = getDefaultSize(h, heightMeasureSpec);
			
			if (w > 0 && h > 0 && scrw > 0 && scrh > 0) {
				if ((float) w / h > (float) scrw / height) {
					height = (int) ((float) h * scrw / w);
				} else {
					width = (int) ((float) w * scrh / h);
				}
				
			}
			setMeasuredDimension(width, height);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
