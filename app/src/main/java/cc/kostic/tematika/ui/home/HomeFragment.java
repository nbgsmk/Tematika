package cc.kostic.tematika.ui.home;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import cc.kostic.tematika.R;
import cc.kostic.tematika.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

	ActionMode actionMode;
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

		binding.bActionMode.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				actionMode = requireActivity().startActionMode(menu_AppBar__actionMode, ActionMode.TYPE_PRIMARY);
			}
		});

		LifecycleOwner vlo = getViewLifecycleOwner();
		MenuHost mh = requireActivity();
		mh.addMenuProvider(menu_AppBar_Normal, vlo, Lifecycle.State.RESUMED);	// FREEZE! kreiranje/unistavanje menu kad se ode u drugi navigation drawer -> vidi kako se poziva konstruktor za ViewPagerAdapter


		return root;
	}









	/////////////////////////////////////
	// Normalni menu
	/////////////////////////////////////
	private final MenuProvider menu_AppBar_Normal = new MenuProvider() {
		@Override
		public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
			menuInflater.inflate(R.menu.menu_appbar__normal, menu);
		}

		@Override
		public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
			int itemId = menuItem.getItemId();
			if (itemId == R.id.menu_appBar__actionMode) {
				actionMode = requireActivity().startActionMode(menu_AppBar__actionMode, ActionMode.TYPE_PRIMARY);
				return true;
			}
			
			return false;
		}

		@Override
		public void onPrepareMenu(@NonNull Menu menu) {
			MenuProvider.super.onPrepareMenu(menu);
		}
		
		@Override
		public void onMenuClosed(@NonNull Menu menu) {
			MenuProvider.super.onMenuClosed(menu);
		}
	};



	/////////////////////////////////////
	// ActionMode tj dinamicki menu
	/////////////////////////////////////

	ActionMode.Callback menu_AppBar__actionMode = new ActionMode.Callback(){

		@Override
		public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
			actionMode.getMenuInflater().inflate(R.menu.menu_appbar__actionmode, menu);
			actionMode.setTitle("Naslov");
			actionMode.setSubtitle("subtitle");
			return true;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
			return true;
		}


		@Override
		public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
			int itemId = menuItem.getItemId();
			if (itemId == R.id.menu_appBar_actionmode__text) {
				actionMode.finish();
				return true;
			}
			if (itemId == R.id.menu_appBar_actionmode__ikonica) {
				actionMode.finish();
				return true;
			}
			
			return false;
		}

		@Override
		public void onDestroyActionMode(ActionMode actionMode) {
		}

	};


	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}