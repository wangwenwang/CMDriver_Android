package com.chenmaunion.app.cmdriver.constants;

import android.os.Environment;

/**
 * 存储一些关于服务器地址的常量信息
 *
 */
public class Constants {
 /**
  *  发起定位请求的间隔时间
  */
 public static int scanSpan = 1000 * 60 * 10;
 //   public static int scanSpan = 1000 * 60 * 1;
 // 提交坐标的时间间隔
 public final static int submitSpan = 10 * 60 * 1000;
 /**
  * 验证码发送时间间隔
  */
 public final static long verifyInterval = 60 * 1000;
 /**
  * 错误信息保存路径
  */
 public static final String LOG_SAVE_PATH = Environment
         .getExternalStorageDirectory().toString() + "/com.kdy/";
 /**
  * 版本更新
  */
 public static final String VERSINO_UPDATE_ACTION = "com.kdy.VersionUpdateAction";
 public static final long ENT_IDX = 9008;
 public static final long BUSINESS_IDX = 7;
 /**
  * MainActivity 中传给 TrackingService 对象用的 key
  */
 public static final String APPCONTEXT_KEY = "com.kaidongyuan.driver.APPCONTEXT_KEY";
 /**
  *@auther: Tom
  *created at 2016/6/2 13:57
  *app基本信息数据
  */
 public static final String BasicInfo="dataBases.BasicInfo";
 public static final String IsUsedApp="isusedapp";
 public static final String UserName="userName";
 public static final String UserPWD="userPwd";
 public static final String UserCode="useCode";
 public static final String UserType="useType";
 public class URL {
  //        public static final String Base_Url = "http://192.168.11.19/api/";
  public static final String Test_Url = "http://192.168.11.13/api/";
  //		public static final String Load_Url = "http://192.168.11.19/";
  //   public static final String LoadVersion_Url = "http://oms.kaidongyuan.com:8888/";
//  public static final String Load_Url="http://oms.kaidongyuan.com:13500/";
//  public static final String Base_Url = "http://oms.kaidongyuan.com:13500/api/";
  public static final String Load_Url="http://120.77.168.185:13500/";
  public static final String Base_Url="http://120.77.168.185:13500/api/";
  public static final String Login = Base_Url + "Login";//登录
  public static final String ModifyPassword = Base_Url + "modifyPassword";//修改密码
  public static final String GetPartyList = Base_Url + "GetPartyList";//获取客户列表
  public static final String GetAddress = Base_Url + "GetAddress";//获取地址列表
  public static final String GetProductList = Base_Url + "GetProductList";//获取产品列表
  public static final String SubmitOrder = Base_Url + "SubmitOrder";//最终提交订单	需要传客户地址的 IDX，产品的 IDX
  public static final String GetOrderList = Base_Url + "GetOrderList";//获取订单列表
  public static final String GetOrderDetail = Base_Url + "GetOrderDetail";//获取订单详情
  public static final String SubmitOrder1 = Base_Url + "SubmitOrder1";//提交获取促销信息
  public static final String ConfirmOrder = Base_Url + "ConfirmOrder";//最终提交订单
  public static final String GetPaymentType = Base_Url + "GetPaymentType";//获取付款方式 post strLicense  过来就行了
  // 提交订单时 把 KEY 值 送过来
  public static final String GetPartySalePolicy = Base_Url + "GetPartySalePolicy";
//		  public static final String Information = Base_Url + "Information";
//        public static final String Information = "http://oms.kaidongyuan.com:8088/api/" + "Information";
//        public static final String Information = "http://192.168.11.13/api/" + "Information";
//        public static final String GetNewDetail = "http://192.168.11.13/api/" + "GetNewDetail";
  public static final String Information = Base_Url + "Information";
  //获取用户推送消息列表和内容
  public static final String GetMessage=Base_Url+"GetPushMessage";
  // public static final String GetMessageDetils=Base_Url+"GetMessageDetils";
  public static final String GetNewDetail = Base_Url + "GetNewDetail";
  // GetNewDetail
  // 获取定位轨迹：GetPosition
  // 参数strPhone, strLicense
  public static final String GetPosition = "http://192.168.11.13/api/" + "GetPosition";
  // 开启定位：AddPosition
  // 参数 strUserId， strStatus， strPosition， strLicense
  /**
   *
   // <param name="userId">用户ID</param>
   /// <param name="status">状态</param>
   /// <param name="positionlng">经度</param>
   /// <param name="positionlat">纬度</param>
   Position(string userId, string status, string positionlng, string positionlat)
   */
//        public static final String UploadPosition = "http://192.168.11.13/api/" + "UploadPosition";

  /**
   * strUserIdx
   * cordinateX
   * cordinateY
   * address
   * strLicense
   */
//        public static final String CurrentLocaltion = Test_Url + "CurrentLocaltion";
  public static final String CurrentLocaltion = Base_Url + "CurrentLocaltion";
  public static final String CurrentLocationList = Base_Url + "CurrentLocaltionList";
  /**
   * 获取报表
   * string strUserId = Request["strUserId"].ToString();用户ID
   string strLicense = Request["strLicense"].ToString();strLicense
   */
//        public static final String CustomerCount = "http://192.168.11.13/api/" + "CustomerCount";
  public static final String CustomerCount = Base_Url + "CustomerCount";
  //        public static final String ProductCount = "http://192.168.11.13/api/" + "ProductCount";
  public static final String ProductCount = Base_Url + "ProductCount";
  /**
   * 获取物流信息列表
   * strOrderId
   */
  public static final String GetOrderTmsList = Base_Url + "GetOrderTmsList";
//        public static final String GetOrderTmsList = Test_Url + "GetOrderTmsList";
  /**
   * 获取物流信息详情
   */
//        public static final String GetOrderTmsInfo = Test_Url + "GetOrderTmsInfo";
  public static final String GetOrderTmsInfo = Base_Url + "GetOrderTmsInfo";

  /**
   * 获取司机订单列表
   */
//        public static final String GetDriverOrderList = Test_Url + "GetDriverOrderList";
  public static final String GetDriverOrderList = Base_Url + "GetDriverOrderList";
  public static final String GetDriverDateOrderList=Base_Url+"GetDriverDateOrderList";

  /**
   *@auther: Tom
   *created at 2016/11/3 16:23
   * 交付 新加strDeliveNo 回单单号字段
   */
  public static final String DelivePay=Base_Url+"DelivePay";
  /**
   * 获取订单位置信息
   * strOrderIdx  strLicense
   */
//        public static final String GetLocaltion = Test_Url + "GetLocaltion";
  public static final String GetLocaltion = Base_Url + "GetLocaltion";
  /**
   * 获取最新版本 app 信息
   */
  public static final String CheckVersion = Base_Url + "GetVersion";
  /**
   * 获取货物轨迹信息
   */
  public static final String OrderTrackCheck = Base_Url + "GetLocaltionForOrdNo";
  /**
   * 获取电子签名和交货现场图片
   */
  public static final String GETAUTOGRAPH = Base_Url + "GetAutograph";

  /**
   * 1.1 用户注册接口
   */
  public static final String Register=Base_Url+"register";

  /**
   * 1.3 上传定位接口
   */
  public static final String UploadPosition=Base_Url+"UploadPosition";
  /**
   * 1.6 根据订单编号获取定位
   */
  public static final String GetOrderLocaltion=Base_Url+"GetOrderLocaltion";
  /**
   * 1.9 获取APP推送消息
   */
  public static final String GetPushMessage=Base_Url+"GetPushMessage";

  /**
   * 1.10 保存APP推送所需token值，上传CID UserID
   */
  public static final String SavaPushToken=Base_Url+"SavaPushToken";
  /**
   * 1.11 获取APP推送消息详情
   */
  public static final String GetMessageDetils=Base_Url+"GetMessageDetils";
  /**
   * 补充接口
   * 1.1 修改密码
   */
  public static final String UpdatePassword=Base_Url+"UpdatePassword";
  /**
   * 补充接口
   * 1.2 获取个人信息
   */
  public static final String GetUserInfo=Base_Url+"GetUserInfo";


  /**
   * 2.1 获取车队列表
   */
  public static final String GetFleetList=Base_Url+"GetFleetList";
  /**
   * 2.2 获取司机列表
   */
  public static final String GetDriverList=Base_Url+"GetDriverList";
  /**
   * 2.3 获取车辆列表
   */
  public static final String GetVehicleList=Base_Url+"GetVehicleList";
  /**
   * 2.4.0 创建车队
   */
  public static final String AddFleet=Base_Url+"AddFleet";
  /**
   * 2.4 添加司机
   */
  public static final String AddDriver=Base_Url+"AddDriver";
  /**
   * 2.4.1 根据手机号码获取司机
   */
  public static final String GetDriverInfo=Base_Url+"GetDriverInfo";

  /**
   * 2.5 添加车辆
   */
  public static final String AddVehicle=Base_Url+"AddVehicle";
  /**
   * 2.6 删除车辆
   */
  public static final String DeleteVehicle=Base_Url+"DeleteVehicle";
  /**
   * 2.7 删除司机
   */
  public static final String DeleteDriver=Base_Url+"DeleteDriver";
  /**
   * 2.8 授权司机
   */
  public static final String InfoDriver=Base_Url+"InfoDriver";
  /**
   * 3.1 获取货源列表
   */
  public static final String GetSupplyList=Base_Url+"GetSupplyList";
  /**
   * 3.2 获取货源详情
   */
  public static final String GetSupplyInfo=Base_Url+"GetSupplyInfo";
  /**
   * 3.3 竞标货源
   */
  public static final String ReceivingSupply=Base_Url+"ReceivingSupply";
  /**
   * 3.1.1公司信息
   */
  public static final String GetTmsOrgList=Base_Url+"GetTmsOrgList";
  /**
   * 3.1.2车辆尺寸/类型
   */
  public static final String GetItemList=Base_Url+"GetItemList";

  /**
   * 3.1.4交付订单
   * strOrderIdx  strLicense
   */
//        public static final String DriverPay = Test_Url + "DriverPay";
  public static final String DriverPay = Base_Url + "DriverPay";

  /**
   * 4.1 获取装运列表
   */
  public static final String GetShipmentList=Base_Url+"GetShipmentList";
  /**
   * 4.2 获取装运信息
   */
  public static final String GetShipmentInfo=Base_Url+"GetShipmentInfo";
  /**
   * 4.3 获取订单信息
   */
  public static final String GetOrderInfo=Base_Url+"GetOrderInfo";
  /**
   * 5.1修改个人信息
   */
  public static final String ModifyUserInfo=Base_Url+"ModifyUserInfo";

  /**
   * 5.5意见反馈
   */
  public static final String UserFeedback=Base_Url+"UserFeedback";
  /**
   * 5.6查询车辆信息
   */
  public static final String SearchVehicle=Base_Url+"SearchVehicle";
  /**
   * 5.7修改车辆信息（半年一次机会）
   */
  public static final String ModifyVehicle=Base_Url+"ModifyVehicle";

 }

}
