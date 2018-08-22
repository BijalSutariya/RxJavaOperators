package e.matrixhive.listofuser.main;

import java.util.List;
import e.matrixhive.listofuser.utils.ApiClient;
import e.matrixhive.listofuser.utils.Listener;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function4;
import io.reactivex.schedulers.Schedulers;


public class MainActivityInteractor implements MainActivityContract.interactor {

    @Override
    public void usersList(Listener<MainModel> listener) {
        Observable<List<UsersModel>> usersObservable = ApiClient.getRetrofit().getUserList();
        Observable<List<PostsModel>> postsObservable = ApiClient.getRetrofit().getPostList();
        Observable<List<CommentsModel>> commentsObservable = ApiClient.getRetrofit().getCommentstList();
        Observable<List<AlbumsModel>> albumsObservable = ApiClient.getRetrofit().getAlbumstList();

        Observable.zip(usersObservable, postsObservable, commentsObservable, albumsObservable,
                new Function4<List<UsersModel>, List<PostsModel>, List<CommentsModel>, List<AlbumsModel>, MainModel>() {
                    @Override
                    public MainModel apply(List<UsersModel> usersModels, List<PostsModel> postsModels, List<CommentsModel> commentsModels,
                                           List<AlbumsModel> albumsModels) throws Exception {
                        MainModel model = new MainModel();
                        model.setPostsModel(postsModels);
                        model.setUsersModel(usersModels);
                        model.setCommentsModel(commentsModels);
                        model.setAlbumsModel(albumsModels);
                        return model;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listener);

    }
}
