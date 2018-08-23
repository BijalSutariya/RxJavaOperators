package e.matrixhive.busapplication;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.anadeainc.rxbus.Bus;
import com.anadeainc.rxbus.BusProvider;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {

    private Bus eventBus = BusProvider.getInstance();
    private View view;


    private Button sendToActivity;
    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sendToActivity = view.findViewById(R.id.btnSendToActivity);
        sendToActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventBus.post(FragmentEvent.FRAGMENT_ONE);
            }
        });
    }
}
