package com.example.gdsc.ui.faq;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FaqViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FaqViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Faq fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}