package e.matrixhive.listofuser.main;

import e.matrixhive.listofuser.utils.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityInteractor implements MainActivityContract.interactor {

    @Override
    public void login(final IOnLoginFinishedListener loginFinishedListener) {
        Call<MainModel> request = ApiClient.getRetrofit().getUserList(2);
        request.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                if (response.body() != null && response.isSuccessful()) {
                    loginFinishedListener.getUserData(response.body());
                } else {
                    loginFinishedListener.getErrorMsg("Problem Create user !! Try again later.");
                }
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                loginFinishedListener.getErrorMsg("Problem Create user !! Try again later.");
            }
        });
    }
}
