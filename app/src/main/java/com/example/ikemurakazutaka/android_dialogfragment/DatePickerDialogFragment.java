package com.example.ikemurakazutaka.android_dialogfragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by ikemurakazutaka on 15/06/30.
 */
public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	Calendar mCalender;

	private static final String CALENDER_KEY = "message_id";

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
	}

	public interface DatePickerDialogFragmentListener {
		void onRelease();

		void onDataSet(Calendar calendar);

		void onCancel(Calendar calendar);
	}

	public static DatePickerDialogFragment newInstance(Calendar calendar) {
		DatePickerDialogFragment fragment = new DatePickerDialogFragment();
		Bundle args = new Bundle();
		args.putSerializable(CALENDER_KEY, calendar);
		fragment.setArguments(args);

		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Bundle args = getArguments();
		if (args.containsKey(CALENDER_KEY)) {
			mCalender = (Calendar) args.getSerializable(CALENDER_KEY);
		} else {
			mCalender = Calendar.getInstance();
		}
		final int year = mCalender.get(Calendar.YEAR);
		final int month = mCalender.get(Calendar.MONTH);
		final int day = mCalender.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
		dialog.getDatePicker().init(year, month, day, new DatePicker.OnDateChangedListener() {
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				mCalender.set(year, monthOfYear, dayOfMonth);
			}
		});
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "絞込解除", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				((DatePickerDialogFragmentListener) getActivity()).onRelease();
			}
		});
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "設定する", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				((DatePickerDialogFragmentListener) getActivity()).onDataSet(mCalender);
			}
		});
		return dialog;
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);
		((DatePickerDialogFragmentListener) getActivity()).onCancel(mCalender);
	}
}
