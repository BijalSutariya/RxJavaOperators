package e.matrixhive.busapplication;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anadeainc.rxbus.Bus;
import com.anadeainc.rxbus.BusProvider;
import com.anadeainc.rxbus.Subscribe;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Bus eventBus = BusProvider.getInstance();
    private Button eventOneButton;
    private Button eventTwoButton;
    private Button eventMessageButton;
    private TextView receivedLabel;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventBus.register(this);

        eventOneButton = findViewById(R.id.btnEventOne);
        eventTwoButton = findViewById(R.id.btnEventTwo);
        eventMessageButton = findViewById(R.id.btnEventMessage);
        eventOneButton.setOnClickListener(this);
        eventTwoButton.setOnClickListener(this);
        eventMessageButton.setOnClickListener(this);

        receivedLabel = findViewById(R.id.main_receivedLabel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
        eventBus = null;
    }

    @Subscribe
    public void onEvent(FragmentEvent event) {
        receivedLabel.setText(getString(R.string.received_from));
        receivedLabel.append(event.name());
    }
    @Override
    public void onClick(View v) {
        Fragment fragment = null;

        switch (v.getId()){
            case R.id.btnEventOne:
                eventBus.post(ExampleEvent.ONE);
                fragment = new FragmentOne();
                break;
            case R.id.btnEventTwo:
                eventBus.post(ExampleEvent.TWO);
                fragment = new FragmentTwo();
                break;
            case R.id.btnEventMessage:
                eventBus.post("Message");
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, fragment);
            ft.commit();
        }

    }
}
