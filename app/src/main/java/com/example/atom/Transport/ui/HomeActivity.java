package com.example.atom.Transport.ui;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.atom.Transport.AppManager;
import com.example.atom.Transport.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author Tau.Chen 陈涛
 *
 * @email tauchen1990@gmail.com,1076559197@qq.com
 *
 * @date 2013年9月12日
 *
 * @version V_1.0.0
 *
 * @description     由homeActivity跳转到各个页面
 *
 */
public class HomeActivity extends TabActivity {

	public static final String TAG = HomeActivity.class.getSimpleName();

	private RadioGroup mTabButtonGroup;
	private TabHost mTabHost;

	public static final String TAB_MAIN = "MAIN_ACTIVITY";
	public static final String TAB_SEARCH = "SEARCH_ACTIVITY";
	public static final String TAB_CATEGORY = "CATEGORY_ACTIVITY";
	public static final String TAB_CART = "CART_ACTIVITY";
	public static final String TAB_PERSONAL = "PERSONAL_ACTIVITY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AppManager.getInstance().addActivity(this);
		setContentView(R.layout.activity_home);
		findViewById();
		initView();
	}

	private void findViewById() {
		mTabButtonGroup = (RadioGroup) findViewById(R.id.home_radio_button_group);
	}

	private void initView() {

		mTabHost = getTabHost();

		Intent i_news = new Intent(this, NewsActivity.class);//跳转到主信息界面
		Intent i_navigate = new Intent(this, NavigateActivity.class);//导航界面
		Intent i_chat = new Intent(this, ChatActivity.class);//聊天信息界面
		Intent i_contact = new Intent(this, ContactorActivity.class);//联系人界面
		Intent i_personal = new Intent(this, PersonalActivity.class);//个人中心界面

		mTabHost.addTab(mTabHost.newTabSpec(TAB_MAIN).setIndicator(TAB_MAIN)
				.setContent(i_news));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_SEARCH)
				.setIndicator(TAB_SEARCH).setContent(i_navigate));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_CATEGORY)
				.setIndicator(TAB_CATEGORY).setContent(i_chat));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_CART).setIndicator(TAB_CART)
				.setContent(i_contact));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_PERSONAL)
				.setIndicator(TAB_PERSONAL).setContent(i_personal));

		mTabHost.setCurrentTabByTag(TAB_MAIN);

		mTabButtonGroup
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (checkedId) {
							case R.id.home_tab_news:
								mTabHost.setCurrentTabByTag(TAB_MAIN);
								break;

							case R.id.home_tab_navigate:
								mTabHost.setCurrentTabByTag(TAB_SEARCH);
								break;

							case R.id.home_tab_chat:
								mTabHost.setCurrentTabByTag(TAB_CATEGORY);
								break;

							case R.id.home_tab_contactor:
								mTabHost.setCurrentTabByTag(TAB_CART);
								break;

							case R.id.home_tab_personal:
								mTabHost.setCurrentTabByTag(TAB_PERSONAL);
								break;

							default:
								break;
						}
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
			case R.id.menu_about:

				break;

			case R.id.menu_setting:

				break;

			case R.id.menu_history:

				break;

			case R.id.menu_feedback:

				break;

			case R.id.menu_exit:

				showAlertDialog("退出程序", "退出物流系统？？", "确定", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						AppManager.getInstance().AppExit(getApplicationContext());
						ImageLoader.getInstance().clearMemoryCache();
					}
				}, "取消", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});

				break;

			case R.id.menu_help:

				break;

			default:
				break;
		}
		return true;
	}

	/** 含有标题、内容、两个按钮的对话框 **/
	protected void showAlertDialog(String title, String message,
								   String positiveText,
								   OnClickListener onPositiveClickListener,
								   String negativeText,
								   OnClickListener onNegativeClickListener) {
		new AlertDialog.Builder(this).setTitle(title).setMessage(message)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
	}

}
