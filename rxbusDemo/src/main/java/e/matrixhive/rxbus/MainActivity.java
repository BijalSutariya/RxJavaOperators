package e.matrixhive.rxbus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private TextView textView;
    private Button btnEventOne,btnEventTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEventOne = findViewById(R.id.btnEventOne);
        btnEventTwo = findViewById(R.id.btnEventTwo);
        textView = findViewById(R.id.main_receivedLabel);

        btnEventOne.setOnClickListener(this);
        btnEventTwo.setOnClickListener(this);

        textView = findViewById(R.id.main_receivedLabel);

        disposables.add(((MyApplication) getApplication())
                .bus()
                .toObservable()
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) {
                        if (object instanceof Events.AutoEvent) {
                            textView.setText(R.string.autoevent);
                        } else {
                            textView.setText(R.string.tapevent);
                        }
                    }
                }));

      /*  btnRxBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApplication) getApplication())
                        .bus()
                        .send(new Events.TapEvent());
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.btnEventOne:
                ((MyApplication) getApplication())
                        .bus()
                        .send(new Events.TapEvent());
                fragment = new OneFragment();
                break;
            case R.id.btnEventTwo:
                ((MyApplication) getApplication())
                        .bus()
                        .send(new Events.AutoEvent());
                fragment = new TwoFragment();
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.framelayout, fragment);
            ft.commit();
        }
    }
}
