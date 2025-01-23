package cc.kostic.tematika.ui.home;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class Material_Dlg_setView extends DialogFragment {
	private final String TAG = "TAG " + getClass().getSimpleName();

	private int					position;
	public static final String arg_POSITION = "argPOSITION_bk";



	/**
	 * Factory method to create a new instance of this fragment
	 *
	 * @return A new instance of fragment NotaEdit_dlg.
	 * @args Argumenti iz pozivajuceg Activity ili Fragment
	 */
	public static Material_Dlg_setView newInstance(int position) {
		Material_Dlg_setView fragment = new Material_Dlg_setView();
		Bundle args = new Bundle();
		args.putInt(arg_POSITION, position);
		fragment.setArguments(args);
		return fragment;
	}



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle args = getArguments();
		if (args != null) {
			position = args.getInt(arg_POSITION);
		}

	}


	/*
	 * PAZNJA!
	 * onViewCreated()    NECE BITI POZVAN    ako se override onCreateDialog()
	 */



	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		// return super.onCreateDialog(savedInstanceState);

		MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
		builder.setTitle("Naslov");
		builder.setMessage("Jos uvek je message, nije view. TODO!");
		// builder.setView(binding.getRoot());
		builder.setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				// nista
			}
		});

		return builder.create();
	}



	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}


}
