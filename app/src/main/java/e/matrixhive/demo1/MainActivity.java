package e.matrixhive.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button create = findViewById(R.id.btnCreate);
        Button differ = findViewById(R.id.btnDefer);
        Button just = findViewById(R.id.btnJust);
        Button map = findViewById(R.id.btnMap);
        Button flatmap = findViewById(R.id.btnFlatMap);
        Button interval = findViewById(R.id.btnInterval);
        Button timer = findViewById(R.id.btnTimer);
        Button zip = findViewById(R.id.btnZip);

        create.setOnClickListener(this);
        differ.setOnClickListener(this);
        just.setOnClickListener(this);
        map.setOnClickListener(this);
        flatmap.setOnClickListener(this);
        interval.setOnClickListener(this);
        timer.setOnClickListener(this);
        zip.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreate:
                createObservable();
                break;
            case R.id.btnDefer:
                deferObservable();
                break;
            case R.id.btnJust:
                justObservable();
                break;
            case R.id.btnMap:
                mapObservable();
                break;
            case R.id.btnFlatMap:
                flatmapObservable();
                break;
            case R.id.btnInterval:
                intervalObservable();
                break;
            case R.id.btnTimer:
                timerObservable();
                break;
            case R.id.btnZip:
                zipObservable();
                break;
        }
    }

    /**
     * createObservable : create an Observable by calling observer methods
     */
    private void createObservable() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                emitter.onNext("Hello");
                emitter.onNext("Welcome");
                emitter.onNext("This is your first RxJava example");
                emitter.onComplete();
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG", "onSubscribe:");
            }

            @Override
            public void onNext(String str) {
                Log.d("TAG", "onNext: " + str);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG", "onError: " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d("TAG", "onComplete: ");
            }

        };
        observable.subscribe(observer);
    }

    /**
     * deferObservable : when observer subscribe that time create observable
     */
    private void deferObservable() {
        Observable<String> observable1 = Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() {
                movie = "ABCD";
                return Observable.just(movie);
            }
        });
        observable1.subscribe();
        Log.d("TAG", "deferObservable: " + movie);
    }

    /**
     * justObservable : convert an object into an Observable
     */
    private void justObservable() {
        Observable<Integer> observable2 = Observable.just(1, 2, 3, 4);


        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG", "onSubscribe:");
            }

            @Override
            public void onNext(Integer movieModel) {
                Log.d("TAG", "onNext: " + movieModel);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG", "onError: " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d("TAG", "onComplete: ");
            }

        };
        observable2.subscribe(observer);
    }

    /**
     * mapObservable : transferm data value by applying function
     */
    private void mapObservable() {
        Observable<Integer> observable3 = Observable.just(0, 1, 2, 3, 4).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                integer = integer * 10;
                return integer;
            }
        });
        observable3.subscribe();
        Log.d("TAG", "mapObservable: " + observable3.blockingLast());
    }

    /**
     * flatmapObservable : transforms data value by an Observable into Observables.
     */
    private void flatmapObservable() {
        Observable<Integer> observable4 = Observable.just(0, 1, 2, 3, 4).flatMap(new Function<Integer, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(Integer integer) {
                integer = integer * 100;
                return Observable.just(integer);
            }
        });
        observable4.subscribe();
        Log.d("TAG", "flatmapObservable: " + observable4.blockingLast());
    }

    /**
     * intervalObservable : in particular time interval observable is created
     */
    private void intervalObservable() {
        Observable<Long> observable5 = Observable.interval(2, TimeUnit.SECONDS).take(10);

        Observer<Long> observer1 = new Observer<Long>() {


            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.d("TAG", "onNext: " + aLong);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("TAG", "onComplete: ");
            }
        };
        observable5.subscribe(observer1);
    }

    /**
     * timerObservable : emits a only one item after a given delay
     */
    private void timerObservable() {
        Observable<Long> timer = Observable.timer(5, TimeUnit.SECONDS).take(10);
        Observer<Long> observer2 = new Observer<Long>() {


            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.d("TAG", "onNext: " + aLong);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("TAG", "onComplete: ");
            }
        };
        timer.subscribe(observer2);

    }

    /**
     * zipObservable : combine the multiple Observables together via a specified function
     */
    private void zipObservable() {

        Observable<String> stringObservable1 = Observable.just("Hello", "World");
        Observable<String> stringObservable2 = Observable.just("Bye", "Friends");

        Disposable tag = Observable.zip(stringObservable1, stringObservable2, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                return s + " " + s2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                Log.d("TAG", "accept: " + s);
            }
        });
    }

}
