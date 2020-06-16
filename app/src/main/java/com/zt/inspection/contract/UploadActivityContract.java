package com.zt.inspection.contract;


import java.util.List;

public class UploadActivityContract {
    public interface View {

    }

    public interface Presenter {
        void uploadFile(List<String> photos,List<String> videos);
    }

    public interface Model {
    }
}
