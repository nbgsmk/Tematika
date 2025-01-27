package cc.kostic.tematika;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import cc.kostic.tematika.databinding.AppBinding;

public class App extends AppCompatActivity {

	private AppBarConfiguration mAppBarConfiguration;
	private AppBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = AppBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		setSupportActionBar(binding.appBarMain.toolbar);
		binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(binding.getRoot(), "Искочица", Snackbar.LENGTH_INDEFINITE)
					.setAction("Окет!", new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							// nephodan listener da bi se pokazalo dugme na shackbaru
						}
					})
					.setAnchorView(R.id.fab)
					.show();
			}
		});
		DrawerLayout drawer = binding.drawerLayout;
		NavigationView navigationView = binding.navView;
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		mAppBarConfiguration = new AppBarConfiguration.Builder(
				R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
				.setOpenableLayout(drawer)
				.build();
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
		NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
		NavigationUI.setupWithNavController(navigationView, navController);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu__app, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		super.onOptionsItemSelected(item);
		int id = item.getItemId();

		if (id == R.id.menu_app__day) {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
			Toast.makeText(getApplicationContext(), "Daylight mode", Toast.LENGTH_LONG).show();
			return true;
		}
		if (id == R.id.menu_app__night) {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
			Toast.makeText(getApplicationContext(), "Night mode", Toast.LENGTH_LONG).show();
			return true;
		}
		if (id == R.id.menu_app__overflow) {
			Toast.makeText(getApplicationContext(), "Overflow - activity", Toast.LENGTH_LONG).show();
			return true;
		}

		return false;
	}

	@Override
	public boolean onSupportNavigateUp() {
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
		return NavigationUI.navigateUp(navController, mAppBarConfiguration)
				|| super.onSupportNavigateUp();
	}
}