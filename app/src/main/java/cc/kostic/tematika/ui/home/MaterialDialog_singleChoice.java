package cc.kostic.tematika.ui.home;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cc.kostic.tematika.databinding.MaterialDialogSetViewBinding;


public class MaterialDialog_singleChoice extends DialogFragment {
	private final String TAG = "TAG " + getClass().getSimpleName();

	// to call this dialog:
	// MaterialDialog_setView dlg = MaterialDialog_setView.newInstance(1, "bla");
	// dlg.show(getChildFragmentManager(), "MDb_tag");

	private int choice;
	public static final String arg_Choice = "argCHOICE";

	private String txt;
	public static final String arg_Txt = "argTXT";

	private MaterialDialogModel model;
	private String[] itemList = new String[]{"x", "y", "z", "t", "a", "b", "c"};
	private final String loremIpsum = "What is Lorem Ipsum?\n" +
			"\n" +
			"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
			"\n" +
			"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n";



	/**
	 * Factory method to create a new instance of this fragment
	 *
	 * @return A new instance of fragment MaterialDialog_setView.
	 * @args Argumenti iz pozivajuceg Activity ili Fragment
	 */
	public static MaterialDialog_singleChoice newInstance(int choice, String txt) {
		MaterialDialog_singleChoice fragment = new MaterialDialog_singleChoice();
		Bundle args = new Bundle();
		args.putInt(arg_Choice, choice);
		args.putString(arg_Txt, txt);
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle args = getArguments();
		if (args != null) {
			choice = args.getInt(arg_Choice);
			txt = args.getString(arg_Txt);
		}

		// redosled:
		// onCreate -> onCreateDialog -> onCreateView
		// model ce biti raspoloziv u onCreateDialog
		MaterialDialogModel.Factory factory = new MaterialDialogModel.Factory(requireActivity().getApplication(), choice, txt);
		model = new ViewModelProvider(requireParentFragment(), factory).get(MaterialDialogModel.class);

	}


	/*
	 * PAZNJA!
	 * onViewCreated()    NECE BITI POZVAN    ako se override onCreateDialog()
	 */

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		return super.onCreateView(inflater, container, savedInstanceState);
	}


	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		// return super.onCreateDialog(savedInstanceState);

		// iz top level activity
		// MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);      // this je context
		// iz fragmenta
		MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
		builder.setTitle("Naslov " + choice);
		if (choice == 1) {
			builder.setMessage(loremIpsum);
		}
		if (choice == 2) {
			builder.setSingleChoiceItems(itemList, 1, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// nista
				}
			});
		}


		builder.setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				// model.
			}
		});

		builder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				// model.
			}
		});


		// AlertDialog ad = builder.create();
		// ListView lv = ad.getListView();
		// lv.setDivider(new ColorDrawable(getResources().getColor(R.color.blue_200, requireActivity().getTheme())));
		// lv.setDividerHeight(1);
		// return ad;

		// ili samo
		return builder.create();
	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}


}
