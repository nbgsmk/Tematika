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

		binding.bDialogMsg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Material_Dlg_setMsg dlg = Material_Dlg_setMsg.newInstance(44);
				dlg.show(getChildFragmentManager(), "Material_Dlg_msg");

			}
		});

		binding.bDialogView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Material_Dlg_setView dlg = Material_Dlg_setView.newInstance(44);
				dlg.show(getChildFragmentManager(), "Material_Dlg_view");

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