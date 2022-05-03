package com.example.inmobiliarianovara.ui.Inquilino;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InquilinoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public InquilinoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}