package papayacoders.instastory.api;

import android.app.Activity;

import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import papayacoders.instastory.models.FullDetailModel;
import papayacoders.instastory.models.StoryModel;

public class CommonAPI  {
    private static CommonAPI commonAPI;
    private static Activity activity;

    public static CommonAPI getInstance(Activity activity) {
        if (commonAPI == null) {
            commonAPI = new CommonAPI();
        }
        CommonAPI.activity = activity;
        return commonAPI;
    }

    public static boolean isNullOrEmpty(String s) {
        return (s == null) || (s.length() == 0) || (s.equalsIgnoreCase("null")) || (s.equalsIgnoreCase("0"));
    }


    public void Result(final DisposableObserver disposableObserver, String str, String str2) {
        if (isNullOrEmpty(str2)) {
            str2 = "";
        }
        RestClient.getInstance(activity).getService().getResult(str, str2, str.contains("/tv/") ? "Instagram 128.0.0.19.128 (Linux; Android 8.0; ANE-LX1 Build/HUAWEIANE-LX1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.109 Mobile Safari/537.36" : "Instagram 9.5.2 (iPhone7,2; iPhone OS 9_3_3; en_US; en-US; scale=2.00; 750x1334) AppleWebKit/420+").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<JsonObject>() {

            @Override
            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(JsonObject jsonObject) {
                disposableObserver.onNext(jsonObject);
            }

            @Override
            public void onError(Throwable th) {
                disposableObserver.onError(th);
            }

            @Override
            public void onComplete() {
                disposableObserver.onComplete();
            }
        });
    }

    public void getStories(final DisposableObserver disposableObserver, String str) {
        if (isNullOrEmpty(str)) {
            str = "";
        }
        RestClient.getInstance(activity).getService()
                .getStories("https://i.instagram.com/api/v1/feed/reels_tray/", str,
                        "\"Instagram 9.5.2 (iPhone7,2; iPhone OS 9_3_3; en_US; en-US; scale=2.00; 750x1334) AppleWebKit/420+\"").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<StoryModel>() {

            @Override
            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(StoryModel storyModel) {
                disposableObserver.onNext(storyModel);
            }

            @Override
            public void onError(Throwable th) {
                disposableObserver.onError(th);
            }

            @Override
            public void onComplete() {
                disposableObserver.onComplete();
            }
        });
    }


    public void getFullFeed(final DisposableObserver disposableObserver, String str, String str2) {
        APIServices service = RestClient.getInstance(activity).getService();
        service.getFullApi("https://i.instagram.com/api/v1/users/" + str + "/full_detail_info?max_id=", str2, "\"Instagram 9.5.2 (iPhone7,2; iPhone OS 9_3_3; en_US; en-US; scale=2.00; 750x1334) AppleWebKit/420+\"").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<FullDetailModel>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(FullDetailModel fullDetailModel) {
                disposableObserver.onNext(fullDetailModel);
            }

            @Override
            public void onError(Throwable th) {
                disposableObserver.onError(th);
            }

            @Override
            public void onComplete() {
                disposableObserver.onComplete();
            }
        });
    }



}
