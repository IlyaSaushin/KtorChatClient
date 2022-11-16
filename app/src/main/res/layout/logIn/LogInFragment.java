package com.earl.javachat.ui.logIn;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.earl.javachat.JavaChatApp;
import com.earl.javachat.R;
import com.earl.javachat.core.Keys;
import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.core.PossibleServerErrors;
import com.earl.javachat.core.SharedPreferenceManager;
import com.earl.javachat.data.restModels.LoginDto;
import com.earl.javachat.databinding.FragmentLoginBinding;
import com.earl.javachat.ui.NavigationContract;

import javax.inject.Inject;

public class LogInFragment extends Fragment implements OperationResultListener {

    FragmentLoginBinding binding;
    NavigationContract navigator;
    SharedPreferenceManager preferenceManager;
    @Inject
    LogInFormValidation validation;
    @Inject
    LogInPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((JavaChatApp) requireContext().getApplicationContext()).appComponent.injectLogInFragment(this);
        navigator = ((NavigationContract) requireActivity());
        preferenceManager = new SharedPreferenceManager(requireContext());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.logInButton.setOnClickListener(v -> logIn());
        binding.signUpButton.setOnClickListener(v -> navigator.register());
    }

    private Boolean isValidate() {
        return validation.validate(
                binding.logInEmail,
                binding.logInPassword
        );
    }

    private void logIn() {
        if (isValidate()) {
            navigator.showProgressBar();

            LoginDto user = new LoginDto(
                    binding.logInEmail.getText().toString().trim(),
                    binding.logInPassword.getText().toString().trim()
            );
            presenter.logIn(user, this);
/*            preferenceManager.putString(Keys.KEY_TOKEN, token);
            Log.d("tag", "logIn: token -> " + token);*/
        }
    }

    @Override
    public <T> void success(T success) {
        navigator.hideProgressBar();
        preferenceManager.putBoolean(Keys.KEY_IS_SIGNED_UP, true);
        preferenceManager.putString(Keys.KEY_TOKEN, success.toString());
        Toast.makeText(requireContext(), success.toString(), Toast.LENGTH_SHORT).show();
        navigator.chat();
    }

    @Override
    public void fail(Exception exception) {
        navigator.hideProgressBar();
        if (exception.toString().equals(PossibleServerErrors.INVALID_EMAIL_OR_PASSWORD)) {
            Toast.makeText(requireContext(), R.string.invalid_pass_err, Toast.LENGTH_SHORT).show();
        } else if(exception.toString().equals(PossibleServerErrors.NO_SUCH_USER)) {
            Toast.makeText(requireContext(), R.string.no_such_user, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), R.string.unknown_error, Toast.LENGTH_SHORT).show();
            Log.d("tag", "fail: " + exception);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}