package cc.kostic.tematika.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

		binding.bDialogSetmessage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MaterialDialog_singleChoice dlg = MaterialDialog_singleChoice.newInstance(1, "levo desno");
				dlg.show(getChildFragmentManager(), "MaterialDialog_setMsg");
			}
		});

		binding.bDialogSinglechoice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MaterialDialog_singleChoice dlg = MaterialDialog_singleChoice.newInstance(2, "levo desno");
				dlg.show(getChildFragmentManager(), "MaterialDialog_singleChoice");
			}
		});

		binding.bDialogSetview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MaterialDialog_setView dlg = MaterialDialog_setView.newInstance(44, "levo desno tekst");
				dlg.show(getChildFragmentManager(), "MaterialDialog_setView");
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