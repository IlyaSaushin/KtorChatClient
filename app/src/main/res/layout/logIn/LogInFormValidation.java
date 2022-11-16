package com.earl.javachat.ui.logIn;

import android.content.Context;
import android.widget.EditText;

import com.earl.javachat.R;

public interface LogInFormValidation {

    boolean validate(EditText input, EditText password);

    class BaseValidation implements LogInFormValidation {

        @Override
        public boolean validate(EditText input, EditText password) {
            boolean validation = true;
            Context context = input.getContext();
            if (input.getText().toString().trim().isEmpty()) {
                input.setError(context.getString(R.string.empty_string_err));
                validation = false;
            } else if (password.getText().toString().trim().isEmpty()) {
                password.setError(context.getString(R.string.empty_string_err));
                validation = false;
            } else if (password.getText().toString().length() < 6) {
                password.setError(context.getString(R.string.shor_pass_err));
                validation = false;
            }
            return validation;
        }
    }
}
