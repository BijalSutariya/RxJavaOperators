package e.matrixhive.listofuser.utils;

import e.matrixhive.listofuser.main.MainModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("users")
    Call<MainModel> getUserList(@Query("page") int page);
}
