package com.chenmaunion.app.cmdriver.bean.Goods;

import java.io.Serializable;

/**
 * 货源详情类
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/9.
 */
public class SupplyInfo implements Serializable {
    private String IDX;//ID号
    private String SUPPLY_NO;//货源单号
    private String APP_USER_IDX;//发布人ID
    private String ORG_IDX;//发布公司ID
    private String ORG_NAME;//发布公司
    private String TMS_SHIPMENT_NO;// TMS装运编号
    private String TMS_IDX;//TMSID
    private String SUPPLY_VEHICLE_TYPE;// 要求车辆尺寸
    private String  SUPPLY_VEHICLE_SIZE;// 要求车辆类型
    private String  DISTRIBUTION_EXPERIENCE;// 配送经验要求
    private String  ROUTES_CITY;//线路城市
    private String  IS_RETURN_STORE;//是否返仓
    private String  TOTAL_ROUTES;//总公里数
    private String  TOTAL_DROP;//配送点个数
    private String  REQUEST_WAREHOUSE;//要求到仓时间
    private String  REQUEST_ISSUE;//要求交付时间
    private String  IS_RETURN;//是否需要回单
    private String  SHIPMENT_DATE_STRAT;//配送开始时间
    private String  SHIPMENT_DATE_END;//配送结束时间
    private String  SUPPLY_TYPE;//货物类型
    private String  TOTAL_WEIGHT;//整车体积
    private String  TOTAL_VOLUME;//整车重量
    private String  TOTAL_QTY;//总件数
    private String  TOTAL_AMOUNT;//运费金额
    private String  HANDLING_DEGREE;//司机搬运程度
    private String  IS_HANDLING;//是否需要自带搬运
    private String  HAVE_LOAD;//是否有人帮忙装货
    private String  HAVE_UNLOAD;//是否有人帮忙卸货
    private String  DRIVER_REQUEST;//司机要求
    private String  TASK_DESCRIPTION;//任务补充说明
    private String  TASK_DESCRIPTION_OTHER;//任务其他说明
    private String  SUPPLY_STATE;//货源状态
    private String  SUPPLY_WOKERFLOW;//货源流程
    private String  RECEIVING_IDX;//竞标人ID
    private String  USER_TEL;//联系方式
    private String  FLEET_IDX;//车队ID
    private String  FLEET_NAME;//车队名称
    private String  VEHICLE_IDX;//竞标车辆ID
    private String  PLATE_NUMBER;//车牌号
    private String  VEHICLE_TYPE;//车辆类型
    private String  VEHICLE_SIZE;//车辆尺寸
    private String  BRAND_MODEL;// 车辆品牌
    private String  DRIVER_IDX;//司机ID
    private String  DRIVER_NAME;//司机名
    private String  DRIVER_TEL;//司机手机
    private String  VERSION_NUMBER;// 版本号
    private String  ADD_DATE;//创建时间
    private String  EDIT_DATE;//修改时间
    private String  USERNAME;//操作人
    private String  REFERENCE01;//自定义1
    private String  REFERENCE02;
    private String  REFERENCE03;
    private String  REFERENCE04;
    private String  REFERENCE05;
    private String  REFERENCE06;
    private String  REFERENCE07;
    private String  REFERENCE08;
    private String  REFERENCE09;
    private String  REFERENCE10;

    public String getIDX() {
        return IDX;
    }

    public void setIDX(String IDX) {
        this.IDX = IDX;
    }

    public String getSUPPLY_NO() {
        return SUPPLY_NO;
    }

    public void setSUPPLY_NO(String SUPPLY_NO) {
        this.SUPPLY_NO = SUPPLY_NO;
    }

    public String getAPP_USER_IDX() {
        return APP_USER_IDX;
    }

    public void setAPP_USER_IDX(String APP_USER_IDX) {
        this.APP_USER_IDX = APP_USER_IDX;
    }

    public String getORG_IDX() {
        return ORG_IDX;
    }

    public void setORG_IDX(String ORG_IDX) {
        this.ORG_IDX = ORG_IDX;
    }

    public String getORG_NAME() {
        return ORG_NAME;
    }

    public void setORG_NAME(String ORG_NAME) {
        this.ORG_NAME = ORG_NAME;
    }

    public String getTMS_SHIPMENT_NO() {
        return TMS_SHIPMENT_NO;
    }

    public void setTMS_SHIPMENT_NO(String TMS_SHIPMENT_NO) {
        this.TMS_SHIPMENT_NO = TMS_SHIPMENT_NO;
    }

    public String getTMS_IDX() {
        return TMS_IDX;
    }

    public void setTMS_IDX(String TMS_IDX) {
        this.TMS_IDX = TMS_IDX;
    }

    public String getSUPPLY_VEHICLE_TYPE() {
        return SUPPLY_VEHICLE_TYPE;
    }

    public void setSUPPLY_VEHICLE_TYPE(String SUPPLY_VEHICLE_TYPE) {
        this.SUPPLY_VEHICLE_TYPE = SUPPLY_VEHICLE_TYPE;
    }

    public String getSUPPLY_VEHICLE_SIZE() {
        return SUPPLY_VEHICLE_SIZE;
    }

    public void setSUPPLY_VEHICLE_SIZE(String SUPPLY_VEHICLE_SIZE) {
        this.SUPPLY_VEHICLE_SIZE = SUPPLY_VEHICLE_SIZE;
    }

    public String getDISTRIBUTION_EXPERIENCE() {
        return DISTRIBUTION_EXPERIENCE;
    }

    public void setDISTRIBUTION_EXPERIENCE(String DISTRIBUTION_EXPERIENCE) {
        this.DISTRIBUTION_EXPERIENCE = DISTRIBUTION_EXPERIENCE;
    }

    public String getROUTES_CITY() {
        return ROUTES_CITY;
    }

    public void setROUTES_CITY(String ROUTES_CITY) {
        this.ROUTES_CITY = ROUTES_CITY;
    }

    public String getIS_RETURN_STORE() {
        return IS_RETURN_STORE;
    }

    public void setIS_RETURN_STORE(String IS_RETURN_STORE) {
        this.IS_RETURN_STORE = IS_RETURN_STORE;
    }

    public String getTOTAL_ROUTES() {
        return TOTAL_ROUTES;
    }

    public void setTOTAL_ROUTES(String TOTAL_ROUTES) {
        this.TOTAL_ROUTES = TOTAL_ROUTES;
    }

    public String getTOTAL_DROP() {
        return TOTAL_DROP;
    }

    public void setTOTAL_DROP(String TOTAL_DROP) {
        this.TOTAL_DROP = TOTAL_DROP;
    }

    public String getREQUEST_WAREHOUSE() {
        return REQUEST_WAREHOUSE;
    }

    public void setREQUEST_WAREHOUSE(String REQUEST_WAREHOUSE) {
        this.REQUEST_WAREHOUSE = REQUEST_WAREHOUSE;
    }

    public String getREQUEST_ISSUE() {
        return REQUEST_ISSUE;
    }

    public void setREQUEST_ISSUE(String REQUEST_ISSUE) {
        this.REQUEST_ISSUE = REQUEST_ISSUE;
    }

    public String getIS_RETURN() {
        return IS_RETURN;
    }

    public void setIS_RETURN(String IS_RETURN) {
        this.IS_RETURN = IS_RETURN;
    }

    public String getSHIPMENT_DATE_STRAT() {
        return SHIPMENT_DATE_STRAT;
    }

    public void setSHIPMENT_DATE_STRAT(String SHIPMENT_DATE_STRAT) {
        this.SHIPMENT_DATE_STRAT = SHIPMENT_DATE_STRAT;
    }

    public String getSHIPMENT_DATE_END() {
        return SHIPMENT_DATE_END;
    }

    public void setSHIPMENT_DATE_END(String SHIPMENT_DATE_END) {
        this.SHIPMENT_DATE_END = SHIPMENT_DATE_END;
    }

    public String getSUPPLY_TYPE() {
        return SUPPLY_TYPE;
    }

    public void setSUPPLY_TYPE(String SUPPLY_TYPE) {
        this.SUPPLY_TYPE = SUPPLY_TYPE;
    }

    public String getTOTAL_WEIGHT() {
        return TOTAL_WEIGHT;
    }

    public void setTOTAL_WEIGHT(String TOTAL_WEIGHT) {
        this.TOTAL_WEIGHT = TOTAL_WEIGHT;
    }

    public String getTOTAL_VOLUME() {
        return TOTAL_VOLUME;
    }

    public void setTOTAL_VOLUME(String TOTAL_VOLUME) {
        this.TOTAL_VOLUME = TOTAL_VOLUME;
    }

    public String getTOTAL_QTY() {
        return TOTAL_QTY;
    }

    public void setTOTAL_QTY(String TOTAL_QTY) {
        this.TOTAL_QTY = TOTAL_QTY;
    }

    public String getTOTAL_AMOUNT() {
        return TOTAL_AMOUNT;
    }

    public void setTOTAL_AMOUNT(String TOTAL_AMOUNT) {
        this.TOTAL_AMOUNT = TOTAL_AMOUNT;
    }

    public String getHANDLING_DEGREE() {
        return HANDLING_DEGREE;
    }

    public void setHANDLING_DEGREE(String HANDLING_DEGREE) {
        this.HANDLING_DEGREE = HANDLING_DEGREE;
    }

    public String getIS_HANDLING() {
        return IS_HANDLING;
    }

    public void setIS_HANDLING(String IS_HANDLING) {
        this.IS_HANDLING = IS_HANDLING;
    }

    public String getHAVE_LOAD() {
        return HAVE_LOAD;
    }

    public void setHAVE_LOAD(String HAVE_LOAD) {
        this.HAVE_LOAD = HAVE_LOAD;
    }

    public String getHAVE_UNLOAD() {
        return HAVE_UNLOAD;
    }

    public void setHAVE_UNLOAD(String HAVE_UNLOAD) {
        this.HAVE_UNLOAD = HAVE_UNLOAD;
    }

    public String getDRIVER_REQUEST() {
        return DRIVER_REQUEST;
    }

    public void setDRIVER_REQUEST(String DRIVER_REQUEST) {
        this.DRIVER_REQUEST = DRIVER_REQUEST;
    }

    public String getTASK_DESCRIPTION() {
        return TASK_DESCRIPTION;
    }

    public void setTASK_DESCRIPTION(String TASK_DESCRIPTION) {
        this.TASK_DESCRIPTION = TASK_DESCRIPTION;
    }

    public String getTASK_DESCRIPTION_OTHER() {
        return TASK_DESCRIPTION_OTHER;
    }

    public void setTASK_DESCRIPTION_OTHER(String TASK_DESCRIPTION_OTHER) {
        this.TASK_DESCRIPTION_OTHER = TASK_DESCRIPTION_OTHER;
    }

    public String getSUPPLY_STATE() {
        return SUPPLY_STATE;
    }

    public void setSUPPLY_STATE(String SUPPLY_STATE) {
        this.SUPPLY_STATE = SUPPLY_STATE;
    }

    public String getSUPPLY_WOKERFLOW() {
        return SUPPLY_WOKERFLOW;
    }

    public void setSUPPLY_WOKERFLOW(String SUPPLY_WOKERFLOW) {
        this.SUPPLY_WOKERFLOW = SUPPLY_WOKERFLOW;
    }

    public String getRECEIVING_IDX() {
        return RECEIVING_IDX;
    }

    public void setRECEIVING_IDX(String RECEIVING_IDX) {
        this.RECEIVING_IDX = RECEIVING_IDX;
    }

    public String getUSER_TEL() {
        return USER_TEL;
    }

    public void setUSER_TEL(String USER_TEL) {
        this.USER_TEL = USER_TEL;
    }

    public String getFLEET_IDX() {
        return FLEET_IDX;
    }

    public void setFLEET_IDX(String FLEET_IDX) {
        this.FLEET_IDX = FLEET_IDX;
    }

    public String getFLEET_NAME() {
        return FLEET_NAME;
    }

    public void setFLEET_NAME(String FLEET_NAME) {
        this.FLEET_NAME = FLEET_NAME;
    }

    public String getVEHICLE_IDX() {
        return VEHICLE_IDX;
    }

    public void setVEHICLE_IDX(String VEHICLE_IDX) {
        this.VEHICLE_IDX = VEHICLE_IDX;
    }

    public String getPLATE_NUMBER() {
        return PLATE_NUMBER;
    }

    public void setPLATE_NUMBER(String PLATE_NUMBER) {
        this.PLATE_NUMBER = PLATE_NUMBER;
    }

    public String getVEHICLE_TYPE() {
        return VEHICLE_TYPE;
    }

    public void setVEHICLE_TYPE(String VEHICLE_TYPE) {
        this.VEHICLE_TYPE = VEHICLE_TYPE;
    }

    public String getVEHICLE_SIZE() {
        return VEHICLE_SIZE;
    }

    public void setVEHICLE_SIZE(String VEHICLE_SIZE) {
        this.VEHICLE_SIZE = VEHICLE_SIZE;
    }

    public String getBRAND_MODEL() {
        return BRAND_MODEL;
    }

    public void setBRAND_MODEL(String BRAND_MODEL) {
        this.BRAND_MODEL = BRAND_MODEL;
    }

    public String getDRIVER_IDX() {
        return DRIVER_IDX;
    }

    public void setDRIVER_IDX(String DRIVER_IDX) {
        this.DRIVER_IDX = DRIVER_IDX;
    }

    public String getDRIVER_NAME() {
        return DRIVER_NAME;
    }

    public void setDRIVER_NAME(String DRIVER_NAME) {
        this.DRIVER_NAME = DRIVER_NAME;
    }

    public String getDRIVER_TEL() {
        return DRIVER_TEL;
    }

    public void setDRIVER_TEL(String DRIVER_TEL) {
        this.DRIVER_TEL = DRIVER_TEL;
    }

    public String getVERSION_NUMBER() {
        return VERSION_NUMBER;
    }

    public void setVERSION_NUMBER(String VERSION_NUMBER) {
        this.VERSION_NUMBER = VERSION_NUMBER;
    }

    public String getADD_DATE() {
        return ADD_DATE;
    }

    public void setADD_DATE(String ADD_DATE) {
        this.ADD_DATE = ADD_DATE;
    }

    public String getEDIT_DATE() {
        return EDIT_DATE;
    }

    public void setEDIT_DATE(String EDIT_DATE) {
        this.EDIT_DATE = EDIT_DATE;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getREFERENCE01() {
        return REFERENCE01;
    }

    public void setREFERENCE01(String REFERENCE01) {
        this.REFERENCE01 = REFERENCE01;
    }

    public String getREFERENCE02() {
        return REFERENCE02;
    }

    public void setREFERENCE02(String REFERENCE02) {
        this.REFERENCE02 = REFERENCE02;
    }

    public String getREFERENCE03() {
        return REFERENCE03;
    }

    public void setREFERENCE03(String REFERENCE03) {
        this.REFERENCE03 = REFERENCE03;
    }

    public String getREFERENCE04() {
        return REFERENCE04;
    }

    public void setREFERENCE04(String REFERENCE04) {
        this.REFERENCE04 = REFERENCE04;
    }

    public String getREFERENCE05() {
        return REFERENCE05;
    }

    public void setREFERENCE05(String REFERENCE05) {
        this.REFERENCE05 = REFERENCE05;
    }

    public String getREFERENCE06() {
        return REFERENCE06;
    }

    public void setREFERENCE06(String REFERENCE06) {
        this.REFERENCE06 = REFERENCE06;
    }

    public String getREFERENCE07() {
        return REFERENCE07;
    }

    public void setREFERENCE07(String REFERENCE07) {
        this.REFERENCE07 = REFERENCE07;
    }

    public String getREFERENCE08() {
        return REFERENCE08;
    }

    public void setREFERENCE08(String REFERENCE08) {
        this.REFERENCE08 = REFERENCE08;
    }

    public String getREFERENCE09() {
        return REFERENCE09;
    }

    public void setREFERENCE09(String REFERENCE09) {
        this.REFERENCE09 = REFERENCE09;
    }

    public String getREFERENCE10() {
        return REFERENCE10;
    }

    public void setREFERENCE10(String REFERENCE10) {
        this.REFERENCE10 = REFERENCE10;
    }
}
