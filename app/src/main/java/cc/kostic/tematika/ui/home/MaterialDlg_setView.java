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

import cc.kostic.tematika.databinding.MaterialDialogSetViewBinding;


public class MaterialDlg_setView extends DialogFragment {
	private final String TAG = "TAG " + getClass().getSimpleName();

	// to call this dialog:
	// MaterialDlg_setView dlg = MaterialDlg_setView.newInstance(1, "bla");
	// dlg.show(getChildFragmentManager(), "MDb_tag");


	// TODO: Rename arguments
	private int id;
	public static final String arg_ID = "argID_bk";

	private String txt;
	public static final String arg_Txt = "argTxt_bk";

	MaterialDialogSetViewBinding binding;
	MyData_model model;

	/**
	 * Factory method to create a new instance of this fragment
	 *
	 * @return A new instance of fragment MaterialDlg_setView.
	 * @args Argumenti iz pozivajuceg Activity ili Fragment
	 */
	public static MaterialDlg_setView newInstance(int id, String txt) {
		MaterialDlg_setView fragment = new MaterialDlg_setView();
		Bundle args = new Bundle();
		args.putInt(arg_ID, id);
		args.putString(arg_Txt, txt);
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle args = getArguments();
		if (args != null) {
			id = args.getInt(arg_ID);
			txt = args.getString(arg_Txt);
		}

		// redosled:
		// onCreate -> onCreateDialog -> onCreateView
		// model ce biti raspoloziv u onCreateDialog
		MyData_model.Factory factory = new MyData_model.Factory(requireActivity().getApplication(), id, txt);
		model = new ViewModelProvider(requireParentFragment(), factory).get(MyData_model.class);

	}


	/*
	 * PAZNJA!
	 * onViewCreated()    NECE BITI POZVAN    ako se override onCreateDialog()
	 */

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// LifecycleOwner vlo = getViewLifecycleOwner();
		// model.get __SOMETHING__.observe(vlo ...

		// binding...set
		return binding.getRoot();
		// return super.onCreateView(inflater, container, savedInstanceState);
	}


	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		// return super.onCreateDialog(savedInstanceState);

		binding = MaterialDialogSetViewBinding.inflate(getLayoutInflater(), null, false);

		// owner -> THIS je klucno!!
		model.getId().observe(this, new Observer<Integer>() {
			@Override
			public void onChanged(Integer i) {
				String s = "No# " + i + "\n";
				s += "Obavezno staviti ceo layout unutar <ScrollView> \n da bi sprecio probleme sa \n positive i negative dugmicima \n u landscape modu";
				binding.tvDlg.setText(s);
			}
		});

		model.getTxt().observe(this, new Observer<String>() {
			@Override
			public void onChanged(String s) {
				binding.etDlg.setText(s);
			}
		});


		binding.bDlg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(binding.bDlg.getContext(), "Hop!", Toast.LENGTH_SHORT).show();
			}
		});

		// iz top level activity
		// MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);      // this je context
		// iz fragmenta
		MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
		builder.setTitle("Naslov " + id);
		// SET MESSAGE XOR SET VIEW
		if (1 == 2) {
			builder.setMessage("message");
		} else {
			builder.setView(binding.getRoot());
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
		binding = null;
	}


}
