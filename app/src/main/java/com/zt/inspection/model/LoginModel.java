package com.zt.inspection.model;

import android.text.TextUtils;

import com.zt.inspection.contract.LoginContract;
import com.zt.inspection.model.entity.db.UserDBEntity;
import com.zt.inspection.model.entity.response.LoginBean;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

import cn.faker.repaymodel.util.db.DBThreadHelper;
import cn.faker.repaymodel.util.db.litpal.LitPalUtils;
import cn.faker.repaymodel.util.error.ErrorUtil;

public class LoginModel implements LoginContract.Model{

    @Override
    public void saveUser(final String userName,final String pw, final LoginBean data) {

        DBThreadHelper.startThreadInPool(new DBThreadHelper.ThreadCallback() {
            @Override
            protected Object jobContent() throws Exception {
                UserDBEntity entity = new UserDBEntity();
                entity.setName(userName);
                entity.setPassword(pw);
                entity.setLogTimer(new Date());
                int count = LitPalUtils.selectCount(UserDBEntity.class, "name =?", userName);
                if (count > 0) {
                    entity.updateAll("name =?", userName);
                } else {
                    entity.save();
                }
                return null;
            }

            @Override
            protected void jobEnd(Object o) {

            }
        });
    }

    @Override
    public void findUserByLimit(final OnFindUser onFindUser) {
        DBThreadHelper.startThreadInPool(new DBThreadHelper.ThreadCallback<UserDBEntity>() {

            @Override
            protected UserDBEntity jobContent() throws Exception {
                List<UserDBEntity> datas = null;
                try {
                    datas = DataSupport.limit(1).order("logtimer desc").find(UserDBEntity.class);
                }catch (Exception e){
                    ErrorUtil.showError(e);
                }
                if (datas != null &&datas.size()>0) {
                    return datas.get(0);
                }
                return null;
            }

            @Override
            protected void jobEnd(UserDBEntity data) {
                if (data!=null) {
                    onFindUser.onSuccess(data);
                    return;
                } else {
                    onFindUser.onfail();
                }
            }
        });
    }

    @Override
    public void cleanAll() {
        DataSupport.deleteAll(UserDBEntity.class);
    }

/*    @Override
    public String findTeleByToken() {
        List<UserDBEntity> datas = null;
        try {
            datas = DataSupport.limit(1).order("logtimer desc").find(UserDBEntity.class);
        } catch (Exception e) {
            ErrorUtil.showError(e);
        }
        if (datas != null && datas.size() > 0) {
            return datas.get(0).getToken();
        }
        return null;
    }*/
}
