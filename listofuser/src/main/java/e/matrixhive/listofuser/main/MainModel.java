package e.matrixhive.listofuser.main;

import java.util.List;

public class MainModel {
    private List<UsersModel> usersModel;
    private List<PostsModel> postsModel;
    private List<CommentsModel> commentsModel;
    private List<AlbumsModel> albumsModel;

    public List<UsersModel> getUsersModel() {
        return usersModel;
    }

    public void setUsersModel(List<UsersModel> usersModel) {
        this.usersModel = usersModel;
    }

    public List<PostsModel> getPostsModel() {
        return postsModel;
    }

    public void setPostsModel(List<PostsModel> postsModel) {
        this.postsModel = postsModel;
    }

    public List<CommentsModel> getCommentsModel() {
        return commentsModel;
    }

    public void setCommentsModel(List<CommentsModel> commentsModel) {
        this.commentsModel = commentsModel;
    }

    public List<AlbumsModel> getAlbumsModel() {
        return albumsModel;
    }

    public void setAlbumsModel(List<AlbumsModel> albumsModel) {
        this.albumsModel = albumsModel;
    }

    @Override
    public String toString() {
        return "MainModel{" +
                "usersModel=" + usersModel +
                ", postsModel=" + postsModel +
                ", commentsModel=" + commentsModel +
                ", albumsModel=" + albumsModel +
                '}';
    }
}
