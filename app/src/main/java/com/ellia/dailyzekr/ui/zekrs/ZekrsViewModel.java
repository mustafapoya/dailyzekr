package com.ellia.dailyzekr.ui.zekrs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ZekrsViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ZekrsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Zekrs fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
