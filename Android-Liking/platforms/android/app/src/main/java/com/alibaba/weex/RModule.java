package com.alibaba.weex;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;
import android.util.Log;
import com.taobao.weex.bridge.JSCallback;

/**
 * 说明:
 * Author: shaozucheng
 * Time: 上午11:30
 */

public class RModule extends WXModule {


    public long sTimestampOffset = 1;
    public long sRequestTimestamp = 0;

    @WXModuleAnno(runOnUIThread = true)
    public void performCommonRequestParams(final JSCallback callback) {
        Log.e("info","----------------RModule performCommonRequestParams---------------");
        sRequestTimestamp = System.currentTimeMillis() / 1000L + sTimestampOffset;
        String requestId = RequestParamsHelper.genRandomNum(100000000, 999999999);
        String params = "signature=" + RequestParamsHelper.getSignature(String.valueOf(sRequestTimestamp), requestId) + "&" +
                "timestamp=" + sRequestTimestamp + "&" + "request_id=" + requestId;
        Log.e("info","----------------RModule return :" + params );
        callback.invoke(params);
    }
}
