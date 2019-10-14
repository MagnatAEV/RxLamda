package reactive.android.albul.ru.rxlamda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import reactive.android.albul.ru.rxlamda.presenter.Presenter;

public class RxActivity extends AppCompatActivity {

    private String TAG = "RX_PRESENTER";
    private Presenter rxPresenter;
    private Observable<String> observable;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        rxPresenter = new Presenter();
        observable = rxPresenter.getData();

        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_sign})
    public void subscribe(View v){
        disposable = observable.observeOn(AndroidSchedulers.mainThread()).subscribe(
                s -> Log.d(TAG, "onNext: " + Thread.currentThread().getName() + ": " + s),
                throwable -> Log.d(TAG, "onError: " + throwable),
                ()-> Log.d(TAG, "onComplete: " + Thread.currentThread().getName()));
    }


    @OnClick(R.id.btn_unsign)
    public void unsubscribe(View v){
        disposable.dispose();
    }

}

