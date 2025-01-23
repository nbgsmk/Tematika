package cc.kostic.tematika.ui.home;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class Material_Dlg_setMsg extends DialogFragment {
	private final String TAG = "TAG " + getClass().getSimpleName();

	private int					position;
	public static final String arg_POSITION = "argPOSITION_bk";

	private final String loremIpsum = "What is Lorem Ipsum?\n" +
			"\n" +
			"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n";



	/**
	 * Factory method to create a new instance of this fragment
	 *
	 * @return A new instance of fragment NotaEdit_dlg.
	 * @args Argumenti iz pozivajuceg Activity ili Fragment
	 */
	public static Material_Dlg_setMsg newInstance(int position) {
		Material_Dlg_setMsg fragment = new Material_Dlg_setMsg();
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
		builder.setTitle("Naslov " + position);
		builder.setMessage(loremIpsum);
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
