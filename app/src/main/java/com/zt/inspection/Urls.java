package com.zt.inspection;

public class Urls {
    private static final String baseUrl = "http://192.168.2.15:6080";

    public static final String mapUrl = baseUrl+"/arcgis/rest/services/lixiamap1/MapServer";//LYG测试


    private static final String URL = "http://192.168.2.7:8087/";
    public static final String GETNOTICELIST = URL + "NoticeApi/GetNoticeList";
    public static final String LOGIN = URL + "loginApi/login";
    public static final String GETLEAVELIST = URL + "LeaveApi/GetleaveList";
    public static final String GETMODEL = URL + "LeaveApi/GetModel";
    public static final String ADOPT = URL + "LeaveApi/Adopt";
    public static final String REJECT = URL + "LeaveApi/Reject";
    public static final String GETCLOCKINLIST = URL + "ManualSignApi/GetCLOCKINList";
    public static final String ADDCLOCKIN = URL + "ManualSignApi/AddCLOCKIN";
    public static final String GETMANUALSIGNUMLIST = URL + "ManualSignApi/GetManualSigNumList";
    public static final String GETUSERSTATE = URL + "ManualSignApi/GetUserState";
    public static final String GETSIGNTJ = URL + "ManualSignApi/GetSignTJ";
    public static final String DEPTSIGN = URL + "ManualSignApi/DeptSign";
    public static final String ADDPATROLSECTION = URL + "PatrolApi/AddPatrolSection";
    public static final String EDITPATROLSECTION = URL + "PatrolApi/EditPatrolSection";
    public static final String ADDPATROLROUTE = URL + "PatrolApi/AddPatrolRoute";
    public static final String UPLOAD = URL + "UploadApi/Upload";

}
