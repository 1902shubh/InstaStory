package papayacoders.instastory.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefs {

    public static String PREFERENCE = "VIDEODOWNLOADER";
    private SharedPreferences sharedPreferences;
    private static SharePrefs instance;
    public static String SESSIONID = "session_id";
    public static String USERID = "user_id";
    public static final String COOKIES = "Cookies";
    public static final String CSRF = "csrf";
    public static final String IS_INSTAGRAM_LOGIN = "Is_Instagram_Login";

    public SharePrefs(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCE, 0);
    }

    public static SharePrefs getInstance(Context context) {
        if (instance == null) {
            instance = new SharePrefs(context);
        }
        return instance;
    }

    public void putString(String str, String str2) {
        this.sharedPreferences.edit().putString(str, str2).apply();
    }

    public String getString(String str) {
        return this.sharedPreferences.getString(str, "");
    }

    public void putInt(String str, Integer num) {
        this.sharedPreferences.edit().putInt(str, num).apply();
    }

    public void putBoolean(String str, Boolean bool) {
        this.sharedPreferences.edit().putBoolean(str, bool).apply();
    }

    public Boolean getBoolean(String str) {
        return this.sharedPreferences.getBoolean(str, false);
    }

    public int getInt(String str) {
        return this.sharedPreferences.getInt(str, 0);
    }

    public void clearSharePrefs() {
        this.sharedPreferences.edit().clear().apply();
    }

}
