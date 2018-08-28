package com.chenmaunion.app.cmdriver.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.bean.order.CustomerAutographAndPicture;
import com.chenmaunion.app.cmdriver.bean.order.Order;
import com.chenmaunion.app.cmdriver.bean.order.SmOrder;
import com.chenmaunion.app.cmdriver.bean.order.SmOrderDetails;
import com.chenmaunion.app.cmdriver.bean.order.StateTack;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.databinding.ActivityOrderdetailBinding;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.activity.OrderDetailActivity;
import com.chenmaunion.app.cmdriver.ui.activity.OrderTrackActivity;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.interfaces.BaseActivityListener;
import com.kaidongyuan.app.basemodule.ui.fragmentactivity.BaseToastDialogFragmentActivity;
import com.kaidongyuan.app.basemodule.utils.nomalutils.DensityUtil;
import com.kaidongyuan.app.basemodule.utils.nomalutils.StringUtils;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/18.
 */
public class DBModel_OrderDetail implements AsyncHttpCallback{
    public ObservableField<String> TMS_ORD_NO;
    public ObservableField<String> ORD_NO_CLIENT;
    public ObservableField<String> ORD_NO_CONSIGNEE;
    public ObservableField<String> ORD_REQUEST_DELIVERY;
    public  ObservableField<String> ORD_PROJECTED_DELIVERY;
    public ObservableField<String> ORD_VEHICLE_TYPE;
    public ObservableField<String> ORD_VEHICLE_SIZE;
    public ObservableField<String> ORD_TYPE_CLIENT;
    public ObservableField<String> ORD_FROM_ADDRESS;
    public ObservableField<String> ORD_TO_CTEL;
    public ObservableField<String> OMS_REMARK_AUDIT;
    public ObservableField<String> OTS_REMARK_DELIVERY;
    public ObservableField<String> OTS_REMARK_RETURN;
    public ObservableField<String> ORD_FROM_CNAME;
    public ObservableField<String> ORD_FROM_CTEL;
    /****************************20170308 添加上面 的字段*****************************************/
    public ObservableField<String> IDX;
    public ObservableField<String> ORD_NO;
    public ObservableField<String> ORD_TO_NAME;
    public ObservableField<String> ORD_TO_CNAME;
    public ObservableField<String> ORD_TO_ADDRESS;
    public ObservableField<String> ORD_ISSUE_QTY;
    public ObservableField<String> ORD_ISSUE_WEIGHT;
    public ObservableField<String> ORD_ISSUE_VOLUME;
    public ObservableField<String> OTS_RETURN_DATE;
    public ObservableField<String> OTS_DELIVERY_ACTUAL;
    public ObservableField<String> ORD_DATE_ADD;
    public ObservableField<String> ORD_REQUEST_ISSUE;
    public ObservableField<String> ORD_FROM_NAME;
    public ObservableField<String> ORD_REMARK_CLIENT;
    public ObservableField<String> ORD_REMARK_CONSIGNEE;
    public ObservableField<String> DRIVER_PAY;
    public ObservableField<Integer>FAB1_ViSiBLE;
    public ObservableField<Integer>FAB1_ICON;
    public ObservableField<Boolean>ISPAY;
    public ObservableArrayList<DBModel_OrderDetailSimple> OrderDetails;
    public ObservableField<String> AutographURL;
    public ObservableField<String> Picture1URL;
    public ObservableField<String> Picture2URL;
    public ObservableField<Integer>ll_pictures_isVisible;
    private DecimalFormat decimalFormat;
    private OrderDetailActivity mactivity;
    private String TAG_Orderid,TAG_Ispay;
    public DriverAsyncHttpClient mClient;
    private final String Tag_GetOrderInfo="GetOrderInfo";
    private final String Tag_GetAutograph="GetAutograph";
    private List<CustomerAutographAndPicture> customerAutographAndPictures;//订单电子回单图片集合
    private FloatingActionButton floatingActionButton1,floatingActionButton2;

    public DBModel_OrderDetail(OrderDetailActivity activity, String order_id, String ispay) {
        mactivity=activity;
        floatingActionButton1 = (FloatingActionButton) mactivity.findViewById(R.id.fab_payorder);
        floatingActionButton2 = (FloatingActionButton) mactivity.findViewById(R.id.fab_orderTrack);
        TAG_Orderid=order_id;
        TAG_Ispay=ispay;
        ll_pictures_isVisible=new ObservableField<>(View.GONE);
        mClient=new DriverAsyncHttpClient(mactivity,this);
        getOrder();
        TMS_ORD_NO=new ObservableField<>();
        ORD_NO_CLIENT=new ObservableField<>();
        ORD_NO_CONSIGNEE=new ObservableField<>();
        ORD_REQUEST_DELIVERY=new ObservableField<>();
        ORD_PROJECTED_DELIVERY=new ObservableField<>();
        ORD_VEHICLE_TYPE=new ObservableField<>();
        ORD_VEHICLE_SIZE=new ObservableField<>();
        ORD_TYPE_CLIENT=new ObservableField<>();
        ORD_FROM_ADDRESS=new ObservableField<>();
        ORD_TO_CTEL=new ObservableField<>();
        OMS_REMARK_AUDIT=new ObservableField<>();
        OTS_REMARK_DELIVERY=new ObservableField<>();
        OTS_REMARK_RETURN=new ObservableField<>();
        ORD_FROM_CNAME=new ObservableField<>();
        ORD_FROM_CTEL=new ObservableField<>();
        DRIVER_PAY=new ObservableField<>();
        IDX=new ObservableField<>();
        ORD_NO=new ObservableField<>();
        ORD_TO_NAME=new ObservableField<>();
        ORD_TO_CNAME=new ObservableField<>();
        ORD_TO_ADDRESS=new ObservableField<>();//剔除约定的以*代替无法查询行政级别名称的地址字段
        ORD_ISSUE_QTY=new ObservableField<>();
        ORD_ISSUE_WEIGHT=new ObservableField<>();
        ORD_ISSUE_VOLUME=new ObservableField<>();
        OTS_RETURN_DATE=new ObservableField<>();
        OTS_DELIVERY_ACTUAL=new ObservableField<>();
        ORD_DATE_ADD=new ObservableField<>();
        ORD_REQUEST_ISSUE=new ObservableField<>();
        ORD_FROM_NAME=new ObservableField<>();
        ORD_REMARK_CLIENT=new ObservableField<>();
        ORD_REMARK_CONSIGNEE=new ObservableField<>();
        OrderDetails=new ObservableArrayList<>();
        AutographURL=new ObservableField<>();
        Picture1URL=new ObservableField<>();
        Picture2URL=new ObservableField<>();
        FAB1_ViSiBLE=new ObservableField<>();
        FAB1_ICON=new ObservableField<>();
        ISPAY=new ObservableField<>(false);
    }

    public void order2DBModelOrderDeTail(SmOrder order){
        TMS_ORD_NO.set(order.getTMS_ORD_NO());
        ORD_NO_CLIENT.set(order.getORD_NO_CLIENT());
        ORD_NO_CONSIGNEE.set(order.getORD_NO_CONSIGNEE());
        ORD_REQUEST_DELIVERY.set(order.getORD_REQUEST_DELIVERY());
        ORD_PROJECTED_DELIVERY.set(order.getORD_PROJECTED_DELIVERY());
        ORD_VEHICLE_TYPE.set(order.getORD_VEHICLE_TYPE());
        ORD_VEHICLE_SIZE.set(order.getORD_VEHICLE_SIZE());
        ORD_TYPE_CLIENT.set(order.getORD_TYPE_CLIENT());
        ORD_FROM_ADDRESS.set(order.getORD_FROM_ADDRESS());
        ORD_TO_CTEL.set(order.getORD_TO_CTEL());
        OMS_REMARK_AUDIT.set(order.getOMS_REMARK_AUDIT());
        OTS_REMARK_DELIVERY.set(order.getOTS_REMARK_DELIVERY());
        OTS_REMARK_RETURN.set(order.getOTS_REMARK_RETURN());
        ORD_FROM_CNAME.set(order.getORD_FROM_CNAME());
        ORD_FROM_CTEL.set(order.getORD_FROM_CTEL());
        DRIVER_PAY.set(order.getUPDATE03());
        /***************************20170308 添加上面 的字段***********************************/
        IDX.set(order.getIDX());
        ORD_NO.set(order.getORD_NO());
        ORD_TO_NAME.set(order.getORD_TO_NAME());
        ORD_TO_CNAME.set(order.getORD_TO_CNAME());
        ORD_TO_ADDRESS.set(StringUtils.subAllTargetCharSequence(order.getORD_TO_ADDRESS(),"*"));//剔除约定的以*代替无法查询行政级别名称的地址字段
        decimalFormat = new DecimalFormat("0.00");//将double类型转换成String保留两位小数，不四舍五入
        ORD_ISSUE_QTY.set(decimalFormat.format(Double.parseDouble(order.getORD_ISSUE_QTY()))+ "件");
        ORD_ISSUE_WEIGHT.set(decimalFormat.format(Double.parseDouble(order.getORD_ISSUE_WEIGHT()))+ "吨");
        ORD_ISSUE_VOLUME.set(decimalFormat.format(Double.parseDouble(order.getORD_ISSUE_VOLUME()))+ "m³");
        OTS_RETURN_DATE.set(order.getOTS_RETURN_DATE());
        OTS_DELIVERY_ACTUAL.set(order.getOTS_DELIVERY_ACTUAL());
        ORD_DATE_ADD.set(order.getORD_DATE_ADD());
        ORD_REQUEST_ISSUE.set(order.getORD_REQUEST_ISSUE());
        ORD_FROM_NAME.set(order.getORD_FROM_NAME());
        ORD_REMARK_CLIENT.set(order.getORD_REMARK_CLIENT());
        ORD_REMARK_CONSIGNEE.set(order.getORD_REMARK_CONSIGNEE());
        if (!DRIVER_PAY.get().isEmpty()&&DRIVER_PAY.get().equals("Y")){
            FAB1_ViSiBLE.set(View.VISIBLE);
            FAB1_ICON.set(R.drawable.fab_checkpicture);
            floatingActionButton1.setIcon(R.drawable.fab_checkpicture);
            floatingActionButton2.setIcon(R.drawable.fab_ordertrack);
            ISPAY.set(true);
        }else {
            FAB1_ViSiBLE.set(View.VISIBLE);
            FAB1_ICON.set(R.drawable.fab_payorder);
            floatingActionButton1.setIcon(R.drawable.fab_payorder);
            floatingActionButton2.setIcon(R.drawable.fab_ordernavi);
            ISPAY.set(false);
        }
    }
    private void setOrderDetails(List<SmOrderDetails> smOrderDetailsList) {
        for (int i=0;i<smOrderDetailsList.size();i++) {
            DBModel_OrderDetailSimple dbmodel=new DBModel_OrderDetailSimple();
            dbmodel.orderdetail2DBOrderDetailSimple(smOrderDetailsList.get(i));
            OrderDetails.add(dbmodel);
        }
    }
    public void OnFAB1Click(View view) {

        if (ISPAY.get()) {//已交付订单 查看图片
            if (!IDX.get().isEmpty()) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("OrderId", IDX.get().trim());
                params.put("strLicense", "");
                mClient.sendRequest(Constants.URL.GETAUTOGRAPH, params, Tag_GetAutograph);
                ll_pictures_isVisible.set(View.VISIBLE);
            } else {
                mactivity.showToastMsg("订单信息重载");
                getOrder();
            }
        }else {//未交付订单 交单
            Intent intent=new Intent("com.chenmaunion.app.cmdriver.ui.activity.OrderPayActivity");
            intent.putExtra("order_id",IDX.get());
            mactivity.startActivity(intent);
        }
    }
    public void OnFAB2Click(View view) {
        if (ISPAY.get()){
            if (!IDX.get().isEmpty()){
               Intent intent=new Intent(mactivity, OrderTrackActivity.class);
                intent.putExtra("order_id",IDX.get());
                mactivity.startActivity(intent);
            }
        }else {//未交付订单 导航
            mactivity.showToastMsg("改进开发中...");
        }

    }

    private void getOrder() {
        Map<String, String> params = new HashMap<String, String>();
        if (TAG_Orderid!=null&&!TAG_Orderid.isEmpty()) {
            params.put("IDX", TAG_Orderid);
        } else {
            mactivity.showToastMsg("货单ID丢失，请重选~");
            mactivity.finish();
            return;
        }
        params.put("strLicense", "");
        mClient.sendRequest(Constants.URL.GetOrderInfo, params, Tag_GetOrderInfo);
    }

    @BindingAdapter({"fabDrawableId"})
    public static void fabDrawbleId(FloatingActionButton view, int drawableid){
        view.setIcon(drawableid);
    }
    @BindingAdapter({"imageUrl"})
    public static void imageUrl(ImageView iv,String pictureUrl){
        if (pictureUrl!=null&&!pictureUrl.isEmpty()) {
            Glide.with(iv.getContext()).load(pictureUrl).error(R.drawable.ic_imageview_default_bg).override(DensityUtil.dip2px(400), DensityUtil.dip2px(400))
                    .diskCacheStrategy(DiskCacheStrategy.NONE).crossFade().fitCenter().into(iv);
        }else {
            iv.setImageResource(R.drawable.ic_imageview_default_bg);
        }
    }

    /**
     * 获取图片的 url
     * @param remarkInt 标记
     *                  0 客户签名
     *                  1 现场图片1
     *                  2 现场图片2
     * @return 图片的url 路径
     */
    private String getPictureUrl(int remarkInt) {
        try {
            if (customerAutographAndPictures == null) {
                return "";
            }
            int i = 1;
            int j=1;
            for (CustomerAutographAndPicture customerAutographAndPicture : customerAutographAndPictures) {
                try {
                    if (customerAutographAndPicture != null) {
                        String remark = customerAutographAndPicture.getREMARK();
                        if ("Autograph".equals(remark) && remarkInt == 0) {//为客户签名图片
                            return Constants.URL.Load_Url + "Uploadfile/" + customerAutographAndPicture.getPRODUCT_URL();
                        } else if ("pricture".equals(remark)) {
                            String pictureUrl = Constants.URL.Load_Url + "Uploadfile/" + customerAutographAndPicture.getPRODUCT_URL();
                            if (i == 1 && remarkInt == 1) {
                                return pictureUrl;
                            }
                            if (i == 2 && remarkInt == 2) {
                                return pictureUrl;
                            }
                            i++;
                        }else if ("prictureS".equals(remark)){
                            String picturesUrl=Constants.URL.Load_Url+"Uploadfile/"+customerAutographAndPicture.getPRODUCT_URL();
                            if (j==1&&remarkInt==3){
                                return picturesUrl;
                            }
                            if (j==2&&remarkInt==4){
                                return picturesUrl;
                            }
                            j++;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")){
            switch (request_tag){
                case Tag_GetOrderInfo:
                    mactivity.showToastMsg("货单加载失败，请重选~");
                    mactivity.finish();
                    return;
                case Tag_GetAutograph:
                    mactivity.showToastMsg("图片加载失败");
                    return;
                default:
                    mactivity.showToastMsg("网络请求失败，请稍后重试");
                    return;
            }
        }else if (request_tag.equals(Tag_GetOrderInfo)){
            try {
                JSONObject jos= JSON.parseObject(msg);
                JSONObject jo=JSON.parseObject(jos.getString("result"));
                SmOrder morder=JSON.parseObject(jo.getString("Order"),SmOrder.class);
                List<SmOrderDetails>smOrderDetailsList=JSON.parseArray(jo.getString("OrderDetail"),SmOrderDetails.class);
                order2DBModelOrderDeTail(morder);
                setOrderDetails(smOrderDetailsList);
                mactivity.orderDetailSimpleAdapter.setData(OrderDetails);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else if (request_tag.equals(Tag_GetAutograph)){
            JSONObject jos=JSON.parseObject(msg);
            int type=jos.getInteger("type");
            if (type==1){
                customerAutographAndPictures=JSON.parseArray(jos.getString("result"),CustomerAutographAndPicture.class);
                AutographURL.set(getPictureUrl(0));
                Picture1URL.set(getPictureUrl(1));
                Picture2URL.set(getPictureUrl(2));
            }
        }
    }


}
