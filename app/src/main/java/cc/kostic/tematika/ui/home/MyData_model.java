package cc.kostic.tematika.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Arrays;
import java.util.List;


public class MyData_model extends AndroidViewModel {

	private final Application application;

	private final MutableLiveData<Integer> id = new MutableLiveData<>();
	private final MutableLiveData<String> txt = new MutableLiveData<>();

	private final MutableLiveData<List<String>> list = new MutableLiveData<>();

	public MyData_model(@NonNull Application application, int id, String txt) {
		super(application);
		this.application = application;
		this.id.postValue(id);
		this.txt.postValue(txt);

		this.list.postValue( Arrays.asList("a", "b", "c", "d"));
	}

	public LiveData<Integer> getId() {
		return id;
	}
	public void setId(int id) {
		this.id.postValue(id);
	}

	public LiveData<String> getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt.postValue(txt);
	}

	public LiveData<List<String>> getList() {
		return list;
	}

	/////////////
	// FACTORY //
	/////////////

	public static class Factory implements ViewModelProvider.Factory {
		private final Application application;
		private final int id;
		private final String txt;

		public Factory(Application application, int id, String txt) {
			this.application = application;
			this.id = id;
			this.txt = txt;
		}

		@SuppressWarnings("unchecked")
		@NonNull
		@Override
		public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
			return (T) new MyData_model(application, id, txt);
		}
	}
}
