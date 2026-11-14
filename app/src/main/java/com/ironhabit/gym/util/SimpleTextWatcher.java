package com.ironhabit.gym.util;

import android.text.Editable;
import android.text.TextWatcher;

/** TextWatcher con implementación vacía de los métodos que casi nunca se usan. */
public abstract class SimpleTextWatcher implements TextWatcher {
    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
    @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }
    @Override public abstract void afterTextChanged(Editable s);
}