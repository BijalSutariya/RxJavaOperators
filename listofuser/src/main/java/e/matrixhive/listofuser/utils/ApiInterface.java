package e.matrixhive.listofuser.utils;

import java.util.List;

import e.matrixhive.listofuser.main.AlbumsModel;
import e.matrixhive.listofuser.main.CommentsModel;
import e.matrixhive.listofuser.main.PostsModel;
import e.matrixhive.listofuser.main.UsersModel;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("users")
    Observable<List<UsersModel>> getUserList();

    @GET("posts")
    Observable<List<PostsModel>> getPostList();

    @GET("comments")
    Observable<List<CommentsModel>> getCommentstList();

    @GET("albums")
    Observable<List<AlbumsModel>> getAlbumstList();


}
