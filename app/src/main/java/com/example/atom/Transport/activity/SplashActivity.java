package com.example.atom.Transport.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.atom.Transport.R;
import com.example.atom.Transport.config.Constants;
import com.example.atom.Transport.ui.HomeActivity;
import com.example.atom.Transport.ui.base.BaseActivity;

/**
 * @author Tau.Chen 陈涛
 *
 * @email tauchen1990@gmail.com,1076559197@qq.com
 *
 * @date 2013年9月12日
 *
 * @version V_1.0.0
 *
 * @description
 *
 */
public class SplashActivity extends BaseActivity {

	public static final String TAG = SplashActivity.class.getSimpleName();

	private ImageView mSplashItem_iv = null;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.itau.tmall.ui.base.BaseActivity#findViewById()
	 */
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		mSplashItem_iv = (ImageView) findViewById(R.id.splash_loading_item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		Constants.SCREEN_DENSITY = metrics.density;
		Constants.SCREEN_HEIGHT = metrics.heightPixels;
		Constants.SCREEN_WIDTH = metrics.widthPixels;

		mHandler = new Handler(getMainLooper());
		findViewById();
		initView();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.itau.jingdong.ui.base.BaseActivity#initView()
	 */
	@Override
	protected void initView() {
		// TODO Auto-generated method stub

		Animation translate = AnimationUtils.loadAnimation(this,
				R.anim.splash_loading);
		translate.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				openActivity(HomeActivity.class);
				//从一个activity跳转到另外一个activity时的动画
				Log.d("SplashActivity","#####################openHomeActivity?");
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
				SplashActivity.this.finish();
			}
		});
		mSplashItem_iv.setAnimation(translate);
	}

}
