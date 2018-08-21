package e.matrixhive.listofuser.main;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
        mInteractor.login(new MainActivityContract.interactor.IOnLoginFinishedListener() {
            @Override
            public void getUserData(MainModel user) {
                mView.hideProgress();
                List<MainModel.DataBean> list = new ArrayList<>(user.getData());
                Log.d("success", "" + list.toString());
            }

            @Override
            public void getErrorMsg(String errorMsg) {
                mView.hideProgress();
                mView.showErrorMsg("Try again later.");
                Log.d(TAG, "getErrorMsg: ");
            }
        });

    }
}
