package e.matrixhive.listofuser.main;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import e.matrixhive.listofuser.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,MainActivityContract.view {

    private MainActivityPresenter presenter;
    private ProgressBar progressBar;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.layoutMain);
        Button userList = findViewById(R.id.listUser);
        progressBar = findViewById(R.id.progressBar);
        userList.setOnClickListener(this);

        presenter = new MainActivityPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.listUser:
                displayUserList();
                break;
        }
    }

    private void displayUserList() {
        presenter.setOnButtonClick();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMsg(String s) {
        Snackbar snackbar = Snackbar.make(view, s, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}
