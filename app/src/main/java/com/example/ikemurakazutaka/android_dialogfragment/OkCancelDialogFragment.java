package com.example.ikemurakazutaka.android_dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by ikemurakazutaka on 15/06/30.
 */
public class OkCancelDialogFragment extends DialogFragment {
	private DialogInterface.OnClickListener okClickListener = null;
	private DialogInterface.OnClickListener cancelClickListener = null;

	public static OkCancelDialogFragment newInstance(int title, int message) {
		OkCancelDialogFragment fragment = new OkCancelDialogFragment();
		Bundle args = new Bundle();
		args.putInt("title", title);
		args.putInt("message", message);
		fragment.setArguments(args);

		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int title = getArguments().getInt("title");
		int message = getArguments().getInt("message");

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(title).setMessage(message).setPositiveButton(R.string.dialog_ok_button, this.okClickListener).setNegativeButton(R.string.dialog_cancel_button, this.cancelClickListener);

		return builder.create();
	}

	/**
	 * OKクリックリスナーの登録
	 */
	public void setOnOkClickListener(DialogInterface.OnClickListener listener) {
		this.okClickListener = listener;
	}

	/**
	 * Cancelクリックリスナーの登録
	 */
	public void setOnCancelClickListener(DialogInterface.OnClickListener listener) {
		this.cancelClickListener = listener;
	}
}
