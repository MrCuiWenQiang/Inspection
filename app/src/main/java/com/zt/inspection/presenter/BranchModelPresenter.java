package com.zt.inspection.presenter;


import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.BranchModelContract;
import com.zt.inspection.model.entity.response.BranchBean;
import com.zt.inspection.util.WordUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class BranchModelPresenter extends BaseMVPPresenter<BranchModelContract.View> implements BranchModelContract.Presenter {
    private  HashMap<String,Integer> indexmap = new HashMap();
    @Override
    public void queryData() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("DEPARTID", "");
//        params.put("DEPARTID", MyApplication.loginUser.getDEPARTID());
        HttpHelper.get(Urls.GETBRANCHLIST, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                List<BranchBean> datas = JsonUtil.fromList(datajson, BranchBean.class);
                if (datas==null||datas.size()<=0){
                    getView().queryDataFail("暂无通讯人员信息");
                }else {
                    List<BranchBean> orderDatas = orderByName(datas);
                    getView().queryDataSuccess(orderDatas,indexmap);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                getView().queryDataFail(message);
            }
        });
    }

    /**
     * 排序两种方式  1.利用ascII 编码表排序 优劣： 清晰可控 但后期需要归类 取出相应索引值 步骤复杂
     * 2.利用事先定好的首字母表 归类  优劣： 数据树层多 但对业务来说一步到位
     *
     * @param datas
     */

    public static final String[] INDEXES = new String[]{"A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

    private List<BranchBean> orderByName(List<BranchBean> datas) {
        indexmap.clear();
        List<HashMap<Character, Integer>> params = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            BranchBean data = datas.get(i);
            char nameOne = WordUtil.getPinyin(data.getPATROLNAME()).toUpperCase().toCharArray()[0];

            HashMap<Character, Integer> itemMap = new HashMap<>();
            itemMap.put(nameOne, i);
            params.add(itemMap);
        }
        List<BranchBean> nowDatas = new ArrayList<>();
        for (String flvalue : INDEXES) {
            for (HashMap<Character, Integer> mapp : params) {
                for (Character v : mapp.keySet()) {
                    if (v.toString().equals(flvalue)) {
                        BranchBean d =datas.get(mapp.get(v));
                        nowDatas.add(d);
                       if (!indexmap.containsKey(flvalue)){
                           indexmap.put(flvalue,nowDatas.indexOf(d));
                       }
                        mapp.remove(v);
                    }
                }
            }
        }

        /**
         * 因list的 delete特殊性 仍对hashmap 检索
         */
        for (HashMap<Character, Integer> mapp : params) {
            if (mapp.size() <= 0) {
                continue;
            }
            for (Character v : mapp.keySet()) {
                BranchBean d =datas.get(mapp.get(v));
                nowDatas.add(d);
                if (!indexmap.containsKey("#")){
                    indexmap.put("#",nowDatas.indexOf(d));
                }
            }
        }
        return nowDatas;
    }
}
