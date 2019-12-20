package com.ellia.dailyzekr.ui.zekrcounter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ZekrCounterViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ZekrCounterViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Setting fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
