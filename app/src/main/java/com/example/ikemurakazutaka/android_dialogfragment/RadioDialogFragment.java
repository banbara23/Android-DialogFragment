package com.example.ikemurakazutaka.android_dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by ikemurakazutaka on 15/06/30.
 */
public class RadioDialogFragment extends DialogFragment {
	private static final String KEY_SELECTED_ID = "selected_id";
	private int mSelectedId;

	public interface ReviewSortSelectDialogFragmentListener {
		void onPositiveClick(int sortId);
	}

	public static RadioDialogFragment newInstance(final int sortId) {
		RadioDialogFragment fragment = new RadioDialogFragment();
		final Bundle args = new Bundle();
		args.putInt(KEY_SELECTED_ID, sortId);
		fragment.setArguments(args);

		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		final int selectedSortId = getArguments().getInt(KEY_SELECTED_ID);
		String[] items = new String[] { getString(R.string.radio_value_1), getString(R.string.radio_value_2), getString(R.string.radio_value_3) };
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("タイトル");
		return builder.setSingleChoiceItems(items, selectedSortId, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mSelectedId = which;
			}
		}).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				final Fragment f = getTargetFragment();
				if (f != null) {
					if (f instanceof ReviewSortSelectDialogFragmentListener) {
						((ReviewSortSelectDialogFragmentListener) f).onPositiveClick(mSelectedId);
					}
				} else {
					FragmentActivity activity = getActivity();
					if (activity != null && activity instanceof ReviewSortSelectDialogFragmentListener) {
						((ReviewSortSelectDialogFragmentListener) activity).onPositiveClick(mSelectedId);
					}
				}
			}
		}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dismiss();
			}
		}).create();
	}
}
