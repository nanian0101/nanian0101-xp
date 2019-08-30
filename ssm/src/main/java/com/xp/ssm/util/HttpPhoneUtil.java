package com.xp.ssm.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;


/**
 * http请求工具
 */
public class HttpPhoneUtil {

	/**
	 * 请求地址url 
	 */
	public static final String BASE_URL = "https://openapi.miaodiyun.com/distributor/sendSMS";
 
	/**
	 * 开发者注册后系统自动生成的账号，可在官网登录后查看
	 */
	public static final String ACCOUNT_SID = "8035cc1595397f59f8012d39edd24e23";
 
	/**	
	 * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
	 */
	public static final String AUTH_TOKEN = "26f0426c811a4d6e792d511b1937638d";
 
	/**
	 * 响应数据类型, JSON或XML
	 */
	public static final String RESP_DATA_TYPE = "json";
 
	 /**
     * 构造通用参数timestamp、sig和respDataType
     * 
     * @return
     */
    public static String createCommonParam(String sid,String token) {
        // 时间戳
        long timestamp = System.currentTimeMillis();
        // 签名
        String sig = DigestUtils.md5Hex(sid + token + timestamp);

        return "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=" + RESP_DATA_TYPE;
    }

    /**
     * post请求
     * 
     * @param url
     *            功能和操作
     * @param body
     *            要post的数据
     * @return
     * @throws IOException
     */
    public static String post(String url, String body) {
        System.out.println("body:" + System.lineSeparator() + body);

        String result = "";
        try {
            OutputStreamWriter out = null;
            BufferedReader in = null;
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();

            // 设置连接参数
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(20000);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 提交数据
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(body);
            out.flush();

            // 读取返回数据
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = "";
            boolean firstLine = true; // 读第一行不加换行符
            while ((line = in.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                } else {
                    result += System.lineSeparator();
                }
                result += line;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 回调测试工具方法
     * 
     * @param url
     * @return
     */
    public static String postHuiDiao(String url, String body) {
        String result = "";
        try {
            OutputStreamWriter out = null;
            BufferedReader in = null;
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();

            // 设置连接参数
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(20000);

            // 提交数据
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(body);
            out.flush();

            // 读取返回数据
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = "";
            boolean firstLine = true; // 读第一行不加换行符
            while ((line = in.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                } else {
                    result += System.lineSeparator();
                }
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    /**
     * 随机生成6位随机验证码
     *
     * @return 验证码
     */
    public static String getRandNum() {
        String randNum = new Random().nextInt(1000000) + "";
        //如果生成的不是6位数随机数则返回该方法继续生成
        int num = 6;
        if (randNum.length() != num) {
            return getRandNum();
        }
        return randNum;
    }
    
 
    /**
	 * 短信发送(验证码通知，会员营销)
	 * 接口文档地址：http://www.miaodiyun.com/doc/https_sms.html
	 */
	public static void execute() throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("accountSid").append("=").append(ACCOUNT_SID);
		sb.append("&to").append("=").append("13627259460");
		sb.append("&param").append("=").append(URLEncoder.encode(getRandNum(),"UTF-8"));
		sb.append("&templateid").append("=").append("170153");
		/*sb.append("&smsContent").append("=").append( URLEncoder.encode("【秒嘀科技】您的验证码为123456，" +
                "该验证码5分钟内有效。请勿泄漏于他人。","UTF-8"));*/
		String body = sb.toString() + HttpPhoneUtil.createCommonParam(ACCOUNT_SID, AUTH_TOKEN);
		String result = HttpPhoneUtil.post(BASE_URL, body);
		System.out.println(result);

	}
	
	public static void main(String[] args) {
		try {
			execute();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
