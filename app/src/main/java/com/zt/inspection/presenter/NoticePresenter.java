package com.zt.inspection.presenter;


import android.text.TextUtils;

import com.zt.inspection.Urls;
import com.zt.inspection.contract.NoticeContract;
import com.zt.inspection.model.entity.request.NoticeEntity;
import com.zt.inspection.model.entity.response.NoticeBean;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class NoticePresenter extends BaseMVPPresenter<NoticeContract.View> implements NoticeContract.Presenter {

}
