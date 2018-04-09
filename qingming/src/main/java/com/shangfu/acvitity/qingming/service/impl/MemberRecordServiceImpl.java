package com.shangfu.acvitity.qingming.service.impl;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import com.shangfu.acvitity.qingming.dao.MemberRecordDao;
import com.shangfu.acvitity.qingming.dao.ProductDao;
import com.shangfu.acvitity.qingming.dao.UserDao;
import com.shangfu.acvitity.qingming.entity.MD5;
import com.shangfu.acvitity.qingming.entity.Reciver;
import com.shangfu.acvitity.qingming.entity.Product;
import com.shangfu.acvitity.qingming.entity.User;
import com.shangfu.acvitity.qingming.service.MemberRecordService;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemberRecordServiceImpl implements MemberRecordService {
    @Autowired
    MemberRecordDao memberRecordDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Override
    public int findToDaySignCount(String today) {
        return memberRecordDao.findToDaySignCount(today);
    }

    @Override
    public int findMileageByTelphone(String telphone) {

       int sum=0;
        List<User> list=null;
        try {
            list=userDao.getTotal(telphone);

            for(User user:list){
                sum+=getstande(user.getPeriod(),user.getTotal_money());
            }

            int mileage=0;
            try {
                mileage=productDao.getmile(telphone);
            }catch (Exception e){

            }
            sum=sum-mileage;
            if(sum<0){
                sum=0;
            }
        }catch (Exception e){

        }
        return sum;

    }

    @Override
    public int insertProductAndMailage(Product product) {
        int flag = productDao.insertInto_q_product(product.getTelphone(),product.getProduct(),product.getMileage());
        return flag;
    }

    @Override
    public int insertReciver(Reciver receiver) {
        int flag=productDao.insertInto_q_rec(receiver.getName(),receiver.getTelphone(),receiver.getAddress());
        return flag;
    }

    @Override
    public List<String> findProduct()  {
        List<String> list=new ArrayList<String>();
        List<Product> shangping = productDao.getShangping();
        for(Product product:shangping){
            StringBuilder sb = new StringBuilder(product.getTelphone());
            sb.replace(3, 7, "****");
            String str = "恭喜用户"+sb+"成功兑换";
            list.add(str + product.getProduct());
        }
        return list;
    }

    @Override
    public boolean findPerson(String phone) {
        int  a = memberRecordDao.findPerson(phone);
        if(a>0){
            return true;
        }
        return false;
    }


    public Map sendMessage(String mobilePhone) {
        String url = "https://www.beejc.com/shangfu_api/api/memberCoupon/sendMemberCoupon";
        String key = "5a9e1ff5f75898f7a1351ad361f29047";
        //正式口令
        String keyWord="qingmignbh8sf";
        //测试口令
        /*String keyWord = "jaixiajdfdhfhvsf50";*/
        RestTemplate client = new RestTemplate();
        String signPain = new StringBuffer().append(mobilePhone.toString()).
                append(keyWord.toString()).append(key).toString();

        String sign= MD5.MD5Encode(signPain);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("mobilePhone",mobilePhone);
        map.put("keyWord",keyWord);
        map.put("sign",sign);
        String result = null;

        String json="param {\"mobilePhone\":"+mobilePhone+",\"keyWord\":"+keyWord+",\"sign\":"+sign+"}";

        String send= post(url,map);
        Map maps = (Map)JSON.parse(send);




        return maps;
    }

    @Override
    public int insertInto_mfjc_activity_qingming_member_record(String create_time, String telphone) {
        return productDao.insertInto_mfjc_activity_qingming_member_record(create_time,telphone);
    }



   public  String post(String url,Map map) {
        String a = null;
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        List formparams = new ArrayList();

        formparams.add(new BasicNameValuePair("mobilePhone",map.get("mobilePhone").toString()));
        formparams.add(new BasicNameValuePair("keyWord",map.get("keyWord").toString()));
        formparams.add(new BasicNameValuePair("sign",map.get("sign").toString()));


        //formparams.add(new BasicNameValuePair("sign","&Gydn3%xlx7V6UYxqopobPu7kpVyYZ7VcX#%XyHBiXtObkvM61Jo6nO4uIDqWLp85f2nr*&SUYv^q%#LemM#1lH0DhksNI4AGqU"));
        UrlEncodedFormEntity uefEntity;
        HttpEntity entity =null;

        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                 entity = response.getEntity();
                if (entity != null) {
                    System.out.println("--------------------------------------");
                    a=EntityUtils.toString(entity, "UTF-8");
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                    //entry=EntityUtils.toString(entity, "UTF-8");
                    System.out.println("--------------------------------------");
                }
            } finally {
                response.close();
                return a;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                return a;
            }
        }

    }


    public int getstande(int month,int money){
        double beishu=0;
        double money_100=money/100;
        switch (month){
            case 1: beishu=1;break;
            case 2: beishu=1.5;break;
            case 3: beishu=2;break;
            default: beishu=2;break;
        }
        return (int) (beishu*money_100);
    }

}
