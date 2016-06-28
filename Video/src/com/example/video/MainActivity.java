package com.example.video;

import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private ViewPager mViewPager = null;
	private static final String tag = MainActivity.class.getSimpleName();
	private Button mCircle = null;
	private Button mRandom = null;
	private Button mSingle = null;
	private Button mRecovery = null;
	private VideoManagerInfo videoManagerInfo = null;
	private ActionBarActivity mActivity = null;
	private FragmentAdapter mFragmentAdapter;
	private Handler mHandler = new Handler();
	private int screen_width = 0;
	private int columns_width = 0;
	public static boolean setCheck = false;
	
	private ImageView userIcon;
	private TextView mTextView=null;
	private Context mAppContext = null;
	private static List<VideoManagerInfo> videoManagerInfoList = null;
	

	public static List<VideoManagerInfo> getVideoManagerInfoList() {
		return videoManagerInfoList;
	}

	public static void setVideoManagerInfoList(
			List<VideoManagerInfo> videoManagerInfoList) {
		MainActivity.videoManagerInfoList = videoManagerInfoList;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mActivity = this;
		mAppContext=this;
		initList();
		initviews();
		showVideos();
	}

	private void initList() {
		videoManagerInfo = new VideoManagerInfo();
		videoManagerInfoList = new ArrayList<VideoManagerInfo>();

		videoManagerInfo.setVideoName("直播");
		videoManagerInfoList.add(videoManagerInfo);
		videoManagerInfo = new VideoManagerInfo();
		videoManagerInfo.setVideoName("上午好");
		videoManagerInfoList.add(videoManagerInfo);
		videoManagerInfo = new VideoManagerInfo();
		videoManagerInfo.setVideoName("舞蹈时刻");
		videoManagerInfoList.add(videoManagerInfo);
		videoManagerInfo = new VideoManagerInfo();
		videoManagerInfo.setVideoName("呵呵哒");
		videoManagerInfoList.add(videoManagerInfo);
		videoManagerInfo = new VideoManagerInfo();
		videoManagerInfo.setVideoName("甜甜的");
		videoManagerInfoList.add(videoManagerInfo);
		videoManagerInfo = new VideoManagerInfo();
		videoManagerInfo.setVideoName("名字不要太长");
		videoManagerInfoList.add(videoManagerInfo);
		videoManagerInfo = new VideoManagerInfo();
		videoManagerInfo.setVideoName("头像");
		videoManagerInfoList.add(videoManagerInfo);
		videoManagerInfo = new VideoManagerInfo();
		videoManagerInfo.setVideoName("吧主");
		videoManagerInfoList.add(videoManagerInfo);
		videoManagerInfo = new VideoManagerInfo();
		videoManagerInfo.setVideoName("好玩");
		videoManagerInfoList.add(videoManagerInfo);
		videoManagerInfo = new VideoManagerInfo();
		videoManagerInfo.setVideoName("买不了");
		videoManagerInfoList.add(videoManagerInfo);
		videoManagerInfo = new VideoManagerInfo();
		videoManagerInfo.setVideoName("不要错过");
		videoManagerInfoList.add(videoManagerInfo);
		videoManagerInfo = new VideoManagerInfo();
		videoManagerInfo.setVideoName("全场2元");
		videoManagerInfoList.add(videoManagerInfo);

	}

	private void initviews() {
		mViewPager = (ViewPager) findViewById(R.id.vp);
		screen_width = getResources().getDisplayMetrics().widthPixels;
		columns_width = screen_width * 2 / 9;
		
		//观看人数
		mTextView=(TextView) findViewById(R.id.watchnum);
		mTextView.setText("观看人次： 22345");
		
		// 循环按钮
		mCircle = (Button) findViewById(R.id.circle);
		mCircle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						// 设置循环播放

						Toast.makeText(mAppContext, "设置成功，全部视频将循环播放",
								Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
		// 随机播放按钮
		mRandom = (Button) findViewById(R.id.random);
		mRandom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						// 设置随机播放
						Toast.makeText(mAppContext, "设置成功，全部视频将随机播放",
								Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
		// 单曲循环按钮
		mSingle = (Button) findViewById(R.id.single);
		mSingle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						// 设置重复播放
						int position = videoManagerInfo.getmPosition();
						// 判断有问题
						if (videoManagerInfoList.get(position).isChoose()
								&& setCheck) {

							Toast.makeText(mAppContext, "设置成功，该视频将重复播放",
									Toast.LENGTH_SHORT).show();

						}
					}
				});
			}
		});
		// 删除按钮
		mRecovery = (Button) findViewById(R.id.recovery);
		mRecovery.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						// 判断有问题
						if (setCheck) {
							// 弹出一个对话框
							new AlertDialog.Builder(mAppContext)
									.setTitle("系统提示")
									// 设置对话框标题
									.setMessage("删除后不可恢复")
									// 设置显示的内容
									.setPositiveButton("删除",new DialogInterface.OnClickListener() {// 添加返回按钮
												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {// 响应事件
													delItems();
													showVideos();
													// 如果删除成功则弹出删除成功Toast
													Toast.makeText(
															mAppContext,
															"删除成功",
															Toast.LENGTH_SHORT)
															.show();
												}
											})
									.setNegativeButton("取消",new DialogInterface.OnClickListener() {// 添加确定按钮
												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {// 确定按钮的响应事件

													Log.i("alertdialog",
															" 请保存数据！");

												}
											}).show();// 在按键响应事件中显示此对话框
						}
					}
				});
			}
		});

		// 设置按钮
		userIcon = (ImageView) findViewById(R.id.img_usericon);
		userIcon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						if (!setCheck) {
							userIcon.setImageDrawable(getResources()
									.getDrawable(R.drawable.a));
							mCircle.setVisibility(View.VISIBLE);
							mRandom.setVisibility(View.VISIBLE);
							mSingle.setVisibility(View.VISIBLE);
							mRecovery.setVisibility(View.VISIBLE);
						} else {
							
							userIcon.setImageDrawable(getResources()
									.getDrawable(R.drawable.user));
							mCircle.setVisibility(View.INVISIBLE);
							mRandom.setVisibility(View.INVISIBLE);
							mSingle.setVisibility(View.INVISIBLE);
							mRecovery.setVisibility(View.INVISIBLE);
						}
						setCheck = !setCheck;
						
					}
				});
			}
		});
	}
	
	//视频删除功能
	private void delItems() {
		for (int i = videoManagerInfoList
				.size() - 1; i >= 0; i--) {
			if(videoManagerInfoList.get(i).isChoose()) {
				videoManagerInfoList.remove(i);
			}
		}
	}

	private void showVideos() {

		Log.d(tag, "showVideos 01:");
		ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
		fragmentList.add(new GridFragment(mActivity));
		mFragmentAdapter = new FragmentAdapter(
				mActivity.getSupportFragmentManager(), fragmentList);
		mViewPager.setAdapter(mFragmentAdapter);
		Log.d(tag, "showVideos :");
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	

}
