package e.matrixhive.listofuser.main;


import android.annotation.SuppressLint;
import android.util.Log;

import org.reactivestreams.Subscriber;

import java.util.List;

import e.matrixhive.listofuser.utils.ApiClient;
import e.matrixhive.listofuser.utils.Listener;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;


public class MainActivityInteractor implements MainActivityContract.interactor {

    @Override
    public void login(Listener<MainModel> listener) {
        ApiClient.getRetrofit().getUserList(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listener);
    }
}
