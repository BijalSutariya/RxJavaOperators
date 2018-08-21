package e.matrixhive.listofuser.main;

import e.matrixhive.listofuser.utils.Listener;

public interface MainActivityContract {

    interface view {

        void showProgress();

        void hideProgress();

        void showErrorMsg(String s);
    }

    interface interactor {
        void login(Listener<MainModel> model);
    }

    interface presenter {

        void setOnButtonClick();

        // void displayUserList();
    }
}
