package com.earl.javachat.ui.logIn;

import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.Repository;
import com.earl.javachat.data.restModels.LoginDto;

import javax.inject.Inject;

public class LogInPresenter implements LogInContract.Presenter {

    @Inject
    Repository.BaseRepository repository;

    public LogInPresenter(Repository.BaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logIn(LoginDto user, OperationResultListener callback) {
        repository.logIn(user, callback);
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroy() {

    }
}
