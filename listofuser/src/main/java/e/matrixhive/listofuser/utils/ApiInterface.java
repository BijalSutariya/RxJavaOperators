package e.matrixhive.listofuser.utils;

import e.matrixhive.listofuser.main.MainModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("users")
    Observable<MainModel> getUserList(@Query("page") int page);
}
