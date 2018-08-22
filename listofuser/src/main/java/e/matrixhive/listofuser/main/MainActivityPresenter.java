package e.matrixhive.listofuser.main;

import android.util.Log;

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
        mInteractor.usersList(new Listener<MainModel>() {

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
                Log.d(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onNext(MainModel e) {
                mView.hideProgress();
                Log.d(TAG, "onpost: "+e.getPostsModel());
                Log.d(TAG, "onuser: "+e.getUsersModel());
                Log.d(TAG, "oncomment: "+e.getCommentsModel());
                Log.d(TAG, "onalbum: "+e.getAlbumsModel());
            }
        });


    }
}
