package com.example.idea.myapplication.common;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;
/**
 * Created by idea on 16/4/13.
 */
public class BrightnessManager {
    public static final float MAX_BRIGHTNESS = 255.0f;
    public static final String SP_BRIGHTNESS = "Brightness";
    public static final String VALUE_USER_BRIGHTNESS = "value_user_brightness";

    private Context mContext;
    private int mScreenMode;
    private int mScreenBrightness;
    private int mUserBrightness;
    private Window mWindow;
    private WindowManager.LayoutParams mParams;

    public BrightnessManager(Context context) {
        mContext = context;
        try {
            mScreenMode = Settings.System.getInt(mContext.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE);
            mUserBrightness = mScreenBrightness = Settings.System.getInt(
                    mContext.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
            if (mScreenMode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                Settings.System.putInt(mContext.getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            }
            mWindow = ((Activity) mContext).getWindow();
            mParams = mWindow.getAttributes();
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        restoreUserBrightness();
    }

    private void restoreUserBrightness() {
        SharedPreferences sp = mContext.getSharedPreferences(SP_BRIGHTNESS,
                Context.MODE_PRIVATE);
        if (sp.contains(VALUE_USER_BRIGHTNESS)) {
            setScreenBrightness(sp.getInt(VALUE_USER_BRIGHTNESS,
                    mScreenBrightness));
        }
    }

    /**
     * 设置当前屏幕亮度值 0--255，并使之生效
     */
    public void setScreenBrightness(float value) {
        if (value > 255) {
            value = 255;
        }

        if (value < 0) {
            value = 1;
        }

        if (mUserBrightness != (int) value) {
            float f = value / MAX_BRIGHTNESS;
            mParams.screenBrightness = f;
            mWindow.setAttributes(mParams);
            Settings.System.putInt(mContext.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS, (int) value);
            mUserBrightness = (int) value;
        }
    }

    public void restoreSystemBrightness() {
        setScreenBrightness(mScreenBrightness);
        if (mScreenMode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
            Settings.System.putInt(mContext.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        }
    }

    public void saveUserScreenBrightness() {
        SharedPreferences sp = mContext.getSharedPreferences(SP_BRIGHTNESS,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(VALUE_USER_BRIGHTNESS, mUserBrightness);
        editor.commit();
    }

    public int getUserBrightness() {
        return mUserBrightness;
    }
}

