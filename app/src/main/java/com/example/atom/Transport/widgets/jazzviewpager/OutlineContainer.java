package com.example.atom.Transport.widgets.jazzviewpager;

import com.example.atom.Transport.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;

/**
 * 首页动画下过实现的自定义控件
 */
public class OutlineContainer extends FrameLayout implements Animatable {

	private Paint mOutlinePaint;

	private boolean mIsRunning = false;
	private long mStartTime;
	private float mAlpha = 1.0f;
	private static final long ANIMATION_DURATION = 500;
	private static final long FRAME_DURATION = 1000 / 60;
	private final Interpolator mInterpolator = new Interpolator() {
		public float getInterpolation(float t) {
			t -= 1.0f;
			return t * t * t + 1.0f;
		}
	};

	public OutlineContainer(Context context) {
		super(context);
		init();
	}

	public OutlineContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public OutlineContainer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		mOutlinePaint = new Paint();
		mOutlinePaint.setAntiAlias(true);//设置是否抗锯齿
		mOutlinePaint.setStrokeWidth(dpToPx(getResources(), 2));
		mOutlinePaint.setColor(getResources().getColor(R.color.blue));
		mOutlinePaint.setStyle(Style.STROKE);
		//Paint.Style.FILL    :填充内部
		//Paint.Style.FILL_AND_STROKE  ：填充内部和描边
		//Paint.Style.STROKE  ：仅描边

		int padding = dpToPx(getResources(), 10);
		setPadding(padding, padding, padding, padding);
	}

	/*View组件的绘制会调用draw(Canvas canvas)方法，draw过程中主要是先画Drawable背景，对 drawable调用setBounds()
	然后是draw(Canvas c)方法.有点注意的是背景drawable的实际大小会影响view组件的大小，drawable的实际大小通过getIntrinsicWidth()
	和getIntrinsicHeight()获取，当背景比较大时view组件大小等于背景drawable的大小画完背景后，draw过程会调用onDraw(Canvas canvas)
	方法，然后就是dispatchDraw(Canvas canvas)方法, dispatchDraw()主要是分发给子组件进行绘制，我们通常定制组件的时候重写的是
	onDraw()方法。值得注意的是ViewGroup容器组件的绘制，当它没有背景时直接调用的是dispatchDraw()方法, 而绕过了draw()方法，当
	它有背景的时候就调用draw()方法，而draw()方法里包含了dispatchDraw()方法的调用。因此要在ViewGroup上绘制东西的时候往往重写
	的是dispatchDraw()方法而不是onDraw()方法，或者自定制一个Drawable，重写它的draw(Canvas c)和 getIntrinsicWidth(),
    getIntrinsicHeight()方法，然后设为背景。*/
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		int offset = dpToPx(getResources(), 5);
		if (mOutlinePaint.getColor() != JazzyViewPager.sOutlineColor) {
			mOutlinePaint.setColor(JazzyViewPager.sOutlineColor);
		}
		mOutlinePaint.setAlpha((int) (mAlpha * 255));
		Rect rect = new Rect(offset, offset, getMeasuredWidth() - offset,
				getMeasuredHeight() - offset);
		canvas.drawRect(rect, mOutlinePaint);
	}

	public void setOutlineAlpha(float alpha) {
		mAlpha = alpha;
	}

	@Override
	public boolean isRunning() {
		return mIsRunning;
	}

	@Override
	public void start() {
		if (mIsRunning)
			return;
		mIsRunning = true;
		mStartTime = AnimationUtils.currentAnimationTimeMillis();
		post(mUpdater);
	}

	@Override
	public void stop() {
		if (!mIsRunning)
			return;
		mIsRunning = false;
	}

	private final Runnable mUpdater = new Runnable() {
		@Override
		public void run() {
			long now = AnimationUtils.currentAnimationTimeMillis();
			long duration = now - mStartTime;
			if (duration >= ANIMATION_DURATION) {
				mAlpha = 0.0f;
				invalidate();
				stop();
				return;
			} else {
				mAlpha = mInterpolator.getInterpolation(1 - duration
						/ (float) ANIMATION_DURATION);
				invalidate();
			}
			postDelayed(mUpdater, FRAME_DURATION);
		}
	};

	private int dpToPx(Resources res, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				res.getDisplayMetrics());
	}
}
