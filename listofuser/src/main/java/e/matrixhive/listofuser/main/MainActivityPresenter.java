package e.matrixhive.listofuser.main;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import e.matrixhive.listofuser.utils.Listener;

import static android.content.ContentValues.TAG;

public class MainActivityPresenter implements MainActivityContract.presenter {
    private MainActivityContract.view mView;
    private MainActivityInteractor mInteractor;

    public MainActivityPresenter(MainActivityContract.view mView) {
        this.mView = mView;
        this.mInteractor = new MainActivityInteractor();
    }

    @Override
    public void setOnButtonClick() {
        mView.showProgress();
        mInteractor.login(new Listener<MainModel>(){

            @Override
            public void onError(Throwable e) {
            mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(MainModel e) {

            }
        });


    }
}
