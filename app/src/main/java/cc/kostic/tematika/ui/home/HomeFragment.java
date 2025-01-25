package cc.kostic.tematika.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import cc.kostic.tematika.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

	private FragmentHomeBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

		binding = FragmentHomeBinding.inflate(inflater, container, false);
		View root = binding.getRoot();

		final TextView textView = binding.textHome;
		homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

		binding.bDay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
			}
		});
		binding.bNight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
			}
		});
		binding.bDialogSetmessage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MaterialDlg dlg = MaterialDlg.newInstance(1, "levo desno");
				dlg.show(getChildFragmentManager(), "MaterialDialog_setMsg");
			}
		});

		binding.bDialogSinglechoice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MaterialDlg dlg = MaterialDlg.newInstance(2, "levo desno");
				dlg.show(getChildFragmentManager(), "MaterialDlg");
			}
		});

		binding.bDialogSetview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MaterialDlg_setView dlg = MaterialDlg_setView.newInstance(44, "levo desno tekst");
				dlg.show(getChildFragmentManager(), "MaterialDlg_setView");
			}
		});


		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}