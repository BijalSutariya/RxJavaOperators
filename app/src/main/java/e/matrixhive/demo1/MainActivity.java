package e.matrixhive.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;


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
        Button reduce = findViewById(R.id.btnReduce);
        Button filter = findViewById(R.id.btnFilter);
        Button buffer = findViewById(R.id.btnBuffer);
        Button skip = findViewById(R.id.btnSkip);
        Button merge = findViewById(R.id.btnMerge);
        Button concat = findViewById(R.id.btnConcat);
        Button replay = findViewById(R.id.btnReplay);
        Button switchMap = findViewById(R.id.btnSwitchMap);
        Button combineLatest = findViewById(R.id.btnCombineLatest);

        create.setOnClickListener(this);
        differ.setOnClickListener(this);
        just.setOnClickListener(this);
        map.setOnClickListener(this);
        flatmap.setOnClickListener(this);
        interval.setOnClickListener(this);
        timer.setOnClickListener(this);
        zip.setOnClickListener(this);
        reduce.setOnClickListener(this);
        filter.setOnClickListener(this);
        buffer.setOnClickListener(this);
        skip.setOnClickListener(this);
        merge.setOnClickListener(this);
        concat.setOnClickListener(this);
        replay.setOnClickListener(this);
        switchMap.setOnClickListener(this);
        combineLatest.setOnClickListener(this);
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
            case R.id.btnReduce:
                reduceObservable();
                break;
            case R.id.btnFilter:
                filterObservacble();
                break;
            case R.id.btnBuffer:
                bufferObservable();
                break;
            case R.id.btnSkip:
                skipObservable();
                break;
            case R.id.btnMerge:
                mergeObservable();
                break;
            case R.id.btnConcat:
                concatObservable();
                break;
            case R.id.btnReplay:
                replayObservable();
                break;
            case R.id.btnSwitchMap:
                switchMapObservable();
                break;
            case R.id.btnCombineLatest:
                combineLatestObservable();
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

    /**
     * reduceObservable : apply a function to each item emitted by an Observable sequentially and emit the final value
     */
    private void reduceObservable() {
        Maybe<Integer> reduce = Observable.just(0, 1, 2, 3, 4).reduce(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2) {
                return t1 + t2;
            }
        });

        MaybeObserver<Integer> observer = new MaybeObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer integer) {
                Log.d("TAG", "onSuccess: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("TAG", "onComplete: ");
            }
        };

        reduce.subscribe(observer);
    }

    /**
     * filterObservacble : emit only those items from an Observable that pass a predicate test
     */
    private void filterObservacble() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG", "onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("TAG", "onNext: " + integer);
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
        observable.subscribe(observer);
    }

    /**
     * bufferObservable : emited value store in buffer and get this bundle rather then single value at atime
     */
    private void bufferObservable() {
        Observable<List<String>> observable = Observable.just("A", "B", "C", "D", "E").buffer(3000, TimeUnit.SECONDS);

        Observer<List<String>> observer = new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG", "onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(List<String> strings) {
                Log.d("TAG", "onNext: " + strings);
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
        observable.subscribe(observer);
    }

    /**
     * skipObservable : skip the first n items
     */
    private void skipObservable() {
        Observable<String> observable = Observable.just("A", "B", "C", "D", "E").skip(1);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG", "onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                Log.d("TAG", "onNext: " + s);
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
        observable.subscribe(observer);
    }

    /**
     * mergeObservable : combine multiple observables
     */
    private void mergeObservable() {
        Observable<String> aObservable = Observable.fromArray("A1", "A2", "A3", "A4");
        Observable<String> bObservable = Observable.fromArray("B1", "B2", "B3");

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG", "onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                Log.d("TAG", "onNext: " + s);
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
        Observable.merge(aObservable, bObservable).subscribe(observer);
    }

    /**
     * concatObservable : concat the value
     */
    private void concatObservable() {
        Observable<String> aObservable = Observable.fromArray("A1", "A2", "A3", "A4");
        Observable<String> bObservable = Observable.fromArray("B1", "B2", "B3");

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG", "onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                Log.d("TAG", "onNext: " + s);
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

        Observable.concat(aObservable, bObservable).subscribe(observer);
    }

    /**
     * replayObservable : replay ensure that all observers see the same sequence
     */
    private void replayObservable() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        ConnectableObservable<Integer> replay = observable.replay(3);
        replay.connect(); //connecting connectableObservable

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG", "onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("TAG", "onNext: " + integer);
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
        replay.subscribe(observer);


    }

    /**
     * switchMapObservable : transform the item emitted by observable into observable && mirror most recenty transformed emite item
     */
    private void switchMapObservable() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG", "onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(String string) {
                Log.d("TAG", "onNext: " + string);
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

        observable.switchMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) {
                int delay = new Random().nextInt(1);
                return Observable.just(integer.toString())
                        .delay(delay, TimeUnit.SECONDS, Schedulers.io());

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * combineLatestObservable : combine the latest item emitted by each Observable via a specified function
     */
    private void combineLatestObservable() {
        Observable<Long> newsRefreshes = Observable.interval(2, TimeUnit.SECONDS).take(15);
        Observable<Long> weatherRefreshes = Observable.interval(5, TimeUnit.SECONDS).take(20);
        Observable.combineLatest(newsRefreshes, weatherRefreshes, new BiFunction<Long, Long, Object>() {
            @Override
            public Object apply(Long aLong, Long aLong2) throws Exception {
                Log.d("TAG", "apply: "+aLong + " " + aLong2);
                return aLong + " " + aLong2;
            }
        }).subscribe();

    }
}
