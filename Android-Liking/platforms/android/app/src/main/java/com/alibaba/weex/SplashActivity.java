package com.alibaba.weex;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.util.Log;
import com.alibaba.weex.commons.util.AppConfig;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

  private Handler handler = new Handler();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    Log.e("info","----------------SplashActivity---------------");
//    View textView = findViewById(R.id.fullscreen_content);
//    ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//    RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

//    AnimationSet animationSet = new AnimationSet(false);
//    animationSet.addAnimation(scaleAnimation);
//    animationSet.addAnimation(rotateAnimation);
//    animationSet.setDuration(1500);
//
//    animationSet.setAnimationListener(new Animation.AnimationListener() {
//      @Override
//      public void onAnimationStart(Animation animation) {
//      }
//
//      @Override
//      public void onAnimationEnd(Animation animation) {
//
//      }
//
//      @Override
//      public void onAnimationRepeat(Animation animation) {
//      }
//    });
//    textView.startAnimation(animationSet);

    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        String url;
        if (AppConfig.isLaunchLocally()) {
          url = AppConfig.getLocalUrl();
        } else {
          url = AppConfig.getLaunchUrl();
        }
        if (TextUtils.isEmpty(url)) {
          return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String scheme = Uri.parse(url).getScheme();
        StringBuilder builder = new StringBuilder();
        if (TextUtils.equals("file", scheme)) {
          intent.putExtra("isLocal", true);
        } else if (!TextUtils.equals("http", scheme) && !TextUtils.equals("https", scheme)) {
          builder.append("http:");
        }
        builder.append(url);

        Uri uri = Uri.parse(builder.toString());
        intent.setData(uri);
        intent.addCategory("com.taobao.android.intent.category.WEEX");
        intent.setPackage(getPackageName());
        startActivity(intent);
        finish();
      }
    },1500);
  }
}
