package com.example.video;

public class VideoManagerInfo {
	// 视频地址
	private String videoPath = null;
	// 图片地址
	private String imgPath = null;
	// 视频名称
	private String videoName = null;
	// 观看人数
	private int playTimes = 0;
	//是否被选中
	private boolean isChoose=false;
	public boolean isChoose() {
		return isChoose;
	}
	public void setChoose(boolean isChoose) {
		this.isChoose = isChoose;
	}
	//视频position
	private int mPosition=0;
	
	public int getmPosition() {
		return mPosition;
	}
	public void setmPosition(int mPosition) {
		this.mPosition = mPosition;
	}
	
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String a) {
		this.imgPath = a;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public int getPlayTimes() {
		return playTimes;
	}
	public void setPlayTimes(int playTimes) {
		this.playTimes = playTimes;
	}
	public String toString() {
		return "VideoManagerInfo []";
	}
	
	
	
	
	

}
