package reactive.android.albul.ru.rxlamda.presenter;

import android.util.Log;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;


public class Presenter {

    private String TAG = "Presenter";

    public Observable<String> getData(){

        Observable<String> observable = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            for (int i = 0; i < 100; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    String data = "pakage: " + i;
                    Log.d(TAG, "subscribe: ");
                    emitter.onNext(data);
                } catch (Exception e){
                    Log.d(TAG, "subscribe: " + e);
                }
            }
        }).subscribeOn(Schedulers.io());

        return observable;
    }

    public Single<String> getMsg(){
        Single<String> single = Single.create((SingleOnSubscribe<String>) emitter -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    String msg = "msg: bugagaga";
                    Log.d(TAG, "subscribe: " + msg);
                    emitter.onSuccess(msg);
                } catch (Exception e){
                    Log.d(TAG, "subscribe: " + e);
                }
        }).subscribeOn(Schedulers.io());

        return single;
    }

}

