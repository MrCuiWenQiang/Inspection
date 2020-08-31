package com.zt.inspection;

import com.zt.inspection.util.HttpUtil;

public class Urls {
    private static final String baseUrl = "http://192.168.2.15:6080";

    public static final String mapUrl = baseUrl + "/arcgis/rest/services/lixiamap1/MapServer";
    //    public static final String addressURL = baseUrl+"/arcgis/rest/services/社区卫生院_CreateAddressLocator/GeocodeServer";//地理编码
    public static final String addressURL = baseUrl + "/arcgis/rest/services/road_AddressLocator/GeocodeServer";//地理编码

    private static final String URL = HttpUtil.getHttpSetting();
    //    public static final String ip = "http://60.208.82.3";
//    public static final int port = 8011;
//public static final String ip = "http://192.168.2.7";
//    public static final int port = 8011;
    public static final String ip = "http://60.208.82.3";//正式
//    public static final String ip = "http://119.3.165.194";//测试
    public static final int port = 8011;

    public static final String msgip = "60.208.82.3";
    public static final int msgPort = 8012;
    //    private static final String URL = "http://192.168.2.7:8087/";
    public static final String GETNOTICELIST = URL + "/NoticeApi/GetNoticeList";
    public static final String LOGIN = URL + "/loginApi/login";
    public static final String GETLEAVELIST = URL + "/LeaveApi/GetleaveList";
    public static final String GETMODEL = URL + "/LeaveApi/GetModel";
    public static final String ADOPT = URL + "/LeaveApi/Adopt";
    public static final String REJECT = URL + "/LeaveApi/Reject";
    public static final String GETCLOCKINLIST = URL + "/ManualSignApi/GetCLOCKINList";
    public static final String ADDCLOCKIN = URL + "/ManualSignApi/AddCLOCKIN";
    public static final String GETMANUALSIGNUMLIST = URL + "/ManualSignApi/GetManualSigNumList";
    public static final String GETUSERSTATE = URL + "/ManualSignApi/GetUserState";
    public static final String GETSIGNTJ = URL + "/ManualSignApi/GetSignTJ";
    public static final String DEPTSIGN = URL + "/ManualSignApi/DeptSign";
    public static final String ADDPATROLSECTION = URL + "/PatrolApi/AddPatrolSection";
    public static final String EDITPATROLSECTION = URL + "/PatrolApi/EditPatrolSection";
    public static final String ADDPATROLROUTE = URL + "/PatrolApi/AddPatrolRoute";
    public static final String UPLOAD = URL + "/UploadApi/Upload";
    public static final String GETCASEINFOLIST = URL + "/CaseInfoApi/GetCaseInfoList";
    public static final String GETPATROLSECTION = URL + "/PatrolApi/GetPatrolSection";
    public static final String GETPATROLROUTE = URL + "/PatrolApi/GetPatrolRoute";
    public static final String GETLEIXIN = URL + "/CaseInfoApi/GetLeiXin";
    public static final String ADDINFO = URL + "/CaseInfoApi/AddInfo";
    public static final String AddHandleInfo = URL + "/CaseInfoApi/AddHandleInfo";
    public static final String GETBRANCHLIST = URL + "/UserApi/GetBranchList";
    public static final String GETQUERYINFO = URL + "/CaseInfoApi/GetQueryInfo";
    public static final String UPDATEDELTYPE = URL + "/CaseInfoApi/UpdateDelType";
    public static final String GETPATROLSECTIONLIST = URL + "/PatrolApi/GetPatrolSectionList";
    public static final String NOTICEDETAILS = URL + "/NoticeDetails/Index?NID=";
    public static final String MODIFYPOW = URL + "/UserApi/ModifyPOW";
    public static final String APPHOME = URL + "/MainDPApi/AppHome";
    public static final String LOCATION = URL + "/UserApi/LOCATION";
    public static final String EDITION = URL + "/UserApi/Edition";
    public static final String BAIDUREVERSE_GEOCODING = "http://api.map.baidu.com/reverse_geocoding/v3";

}
