package com.zt.inspection.contract;


public class SelectPointContract {
    public interface View {


        void getAddress_Success(String formatted_address);

        void getAddress_Fail(String msg);
    }

    public interface Presenter {
        void queryAddress(double x,double y);
    }

    public interface Model {
    }
}
