package com.example.ikemurakazutaka.android_dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends ActionBarActivity implements DatePickerDialogFragment.DatePickerDialogFragmentListener {

	// ///////////////////////////////////////////////////////////////////////////
	// staticフィールド
	// ///////////////////////////////////////////////////////////////////////////
	/** ダイアログで使用するIDのBundleキー */
	private static final String DIALOG_KEY_ERROR_MESSAGE = "dialog_key_error_message";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		// ok cancelダイアログ
		findViewById(R.id.ok_cancel_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				OkCancelDialogFragment dialogFragment = OkCancelDialogFragment.newInstance(R.string.dialog_title, R.string.dialog_message);
				dialogFragment.setOnOkClickListener(new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						showToast("ok clicked");
					}
				});
				dialogFragment.show(getSupportFragmentManager(), null);
			}
		});

		// 日付選択ダイアログ
		findViewById(R.id.picker_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				DialogFragment dialogFragment = DatePickerDialogFragment.newInstance(calendar);
				dialogFragment.show(getFragmentManager(), null);
			}
		});

		findViewById(R.id.radio_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int initSelectedId = 0; // 初期選択しておきた番号
				RadioDialogFragment dialogFragment = RadioDialogFragment.newInstance(initSelectedId);
				dialogFragment.show(getSupportFragmentManager(), null);
			}
		});

		findViewById(R.id.inner_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle args = new Bundle();
				args.putString(DIALOG_KEY_ERROR_MESSAGE, "メッセージ内容");
				ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
				errorDialogFragment.setArguments(args);
				errorDialogFragment.show(getSupportFragmentManager(), null);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * トースト表示
	 * 
	 * @param message
	 */
	private void showToast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRelease() {
		showToast("onRelease");
	}

	@Override
	public void onDataSet(Calendar calendar) {
		Date date = calendar.getTime();
		showToast(new SimpleDateFormat("yyyy/MM/dd(EEE)").format(date));
	}

	@Override
	public void onCancel(Calendar calendar) {
		showToast("onCancel");
	}

	// ///////////////////////////////////////////////////////////////////////////
	// インナークラス
	// ///////////////////////////////////////////////////////////////////////////
	public static class ErrorDialogFragment extends android.support.v4.app.DialogFragment {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			Bundle args = getArguments();
			final String errorMessage = args.getString(DIALOG_KEY_ERROR_MESSAGE);

			// Use the Builder class for convenient dialog construction
			final FragmentActivity activity = getActivity();
			if (activity != null) {
				AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setMessage(errorMessage).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int witch) {

					}
				});

				// Create the AlertDialog object and return it
				return builder.create();
			}

			return null;
		}
	}
}
