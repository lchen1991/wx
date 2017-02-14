package com.alibaba.weex;

import android.util.Log;

import java.util.Arrays;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created on 16/1/11.
 *
 * @author aaron.huang
 * @version 1.0.0
 */
public class RequestParamsHelper {
    private static final String TAG = "RequestParamsHelper";
    // 1.1.1. appkey :sxpYGL1MMbnY7GXUfN0X 1.2.0 appkey:I3DMnKF4egsgUJ5V5k1m
    //1.3.0 appkey :Yc7T1ao8YnoH6ftAX2E4 // 1.3.1 appkey:K9hfTOCzxUUEleHgfvn7
    //1.4.0 appkey :Hb6uTQW9umy5T6mAzhGF
    public static String REQUEST_APP_KEY = "testCcmsIam500QiangA"; //app签名key

    //正式环境的支付公钥为(yGcNQznPTvusj7Y6rMI5)
    public static final String SET_PASSWORD_KEY = "vZr8m0erpGqLbLThv4ov";//测试环境支付公钥

    public static String getSignature(String timeStamp, String requestId) {
        StringBuffer stringBuffer = new StringBuffer();
        Log.d(TAG, "支付加密预埋: " + SET_PASSWORD_KEY + " app签名key: " + REQUEST_APP_KEY);
        Object[] commonKeys = {timeStamp, requestId, REQUEST_APP_KEY};
        Arrays.sort(commonKeys);
        for (int i = 0; i < commonKeys.length; i++) {
            stringBuffer.append(commonKeys[i]);
            Log.i(TAG, "key: " + commonKeys[i]);
        }
        String signature = stringBuffer.toString();
        Log.i(TAG, "signature: " + signature);
        return SHA1(signature);
    }

    /**
     * @param min
     * @param max
     * @return 密码的字符串
     */
    public static String genRandomNum(long min, long max) {
        int num = (int) (min + Math.random() * (max - min));
        return String.valueOf(num);
    }

    public static String SHA1(String str) {
        String sha1 = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte[] hash = digest.digest();
            sha1 = encodeHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sha1;
    }
    private static String encodeHex(byte[] bytes) {
        StringBuffer hex = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toString((int) bytes[i] & 0xff, 16));
        }

        return hex.toString();
    }

}
