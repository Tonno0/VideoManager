package com.example.video;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.image.ImageOptions;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;



@SuppressLint("InflateParams")
public class GridFragment extends Fragment {
	private static final String tag = GridFragment.class.getSimpleName();
	
	private Context mAppContext =null;
	private List<VideoManagerInfo> videoManagerInfoList=MainActivity.getVideoManagerInfoList();
	public static boolean setCheck = false;
	private Activity mActivity = null;
	
	
	
	public GridFragment(Activity activity) {
		mAppContext = activity.getApplicationContext();
		mActivity = activity;
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		GridView gridView = new GridView(mAppContext);
		int hs = mAppContext.getResources().getDisplayMetrics().heightPixels / 50;
		gridView.setVerticalSpacing(hs);
		gridView.setNumColumns(2);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

		final GridFragment.VideoAdapter mAdapter = new GridFragment.VideoAdapter(
				mAppContext);
		mAdapter.setData(videoManagerInfoList);
		gridView.setAdapter(mAdapter);

		Log.d(tag, "GridFragment  onCreateView");
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (MainActivity.setCheck) {
					if (!videoManagerInfoList.get(position).isChoose()) {
						videoManagerInfoList.get(position).setChoose(true);

					} else {
						videoManagerInfoList.get(position).setChoose(false);
						mAdapter.notifyDataSetChanged();
					}

				} else {
					ViewHolder holder = null;

				}
				mAdapter.notifyDataSetChanged();
			}
		});
		return gridView;
	}

	

	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
	}

	public class VideoAdapter extends BaseAdapter {
		private List<VideoManagerInfo> list = new ArrayList<VideoManagerInfo>();

		public VideoAdapter(Context context) {
		}

		public void setData(List<VideoManagerInfo> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			return list != null ? list.size() : 0;
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;

			if (convertView == null) {
				holder = new ViewHolder();
				LayoutInflater mInflater = LayoutInflater.from(mAppContext);
				convertView = mInflater.inflate(R.layout.listview_select, null);
				holder.bg = (ImageView) convertView
						.findViewById(R.id.imageSelect_bg);
				holder.videoName = (TextView) convertView
						.findViewById(R.id.imageSelect_videoName);
				holder.videoKeep = (TextView) convertView
						.findViewById(R.id.imageSelect_videoKeep);
				holder.videoFee = (TextView) convertView
						.findViewById(R.id.imageSelect_fee);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (list != null) {
				VideoManagerInfo vInfo = list.get(position);
				holder.videoName.setText(vInfo.getVideoName());
				x.image().bind(holder.bg, vInfo.getImgPath(), options);
			}
			if(videoManagerInfoList.get(position).isChoose()){
				holder.videoKeep.setVisibility(View.VISIBLE);
			}else{
				holder.videoKeep.setVisibility(View.INVISIBLE);
			}

			return convertView;
		}

		private ImageOptions options = new ImageOptions.Builder()
		// 设置加载过程中的图片
				.setLoadingDrawableId(R.drawable.loadingimg)
				// 设置加载失败后的图片
				.setFailureDrawableId(R.drawable.loadingimg)
				// 设置使用缓存
				.setUseMemCache(true)
				// 设置显示圆形图片
				.setCircular(false)
				// 设置支持gif
				.setIgnoreGif(false).build();
	}

	public class ViewHolder {
		public ImageView bg;
		public TextView videoLength;
		public TextView videoName;
		public TextView playTimes;

		public TextView videoKeep;
		public TextView videoDownload;
		public TextView videoFee;

	}

}
