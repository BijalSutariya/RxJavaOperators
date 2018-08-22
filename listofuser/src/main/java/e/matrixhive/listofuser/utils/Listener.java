package e.matrixhive.listofuser.utils;

import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;

public abstract class Listener<T> extends DisposableObserver<T> implements Observer<T> {


    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

}
