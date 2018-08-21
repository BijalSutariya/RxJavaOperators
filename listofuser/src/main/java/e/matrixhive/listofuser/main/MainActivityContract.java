package e.matrixhive.listofuser.main;

public interface MainActivityContract {

    interface view {

        void showProgress();

        void hideProgress();

        void showErrorMsg(String s);
    }

    interface interactor {
        void login(IOnLoginFinishedListener loginFinishedListener);

        interface IOnLoginFinishedListener {

            void getUserData(MainModel user);

            void getErrorMsg(String errorMsg);
        }
    }

    interface presenter {

        void setOnButtonClick();
    }
}
