package cc.kostic.tematika.ui;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import cc.kostic.tematika.R;


public class Settings_frag extends PreferenceFragmentCompat {

	/*
	Custom preference file:
	- get preference manager
	- set custom file name
	- call setPreferencesFromResource(.. as usual
	https://stackoverflow.com/questions/51138872/add-specific-named-sharedpreferences-from-resource-in-preferenceactivity-or-pref
	*/

	/*
	Setting Preference Summary
	https://eng-nohasamirsaad.medium.com/setting-preference-summary-ebc4aab4ccfa
	i
	https://developer.android.com/develop/ui/views/components/settings

	ali ne radi mi
	 */
	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

		PreferenceManager preferenceManager = getPreferenceManager();
		setPreferencesFromResource(R.xml.root_preferences, rootKey);
	}
}