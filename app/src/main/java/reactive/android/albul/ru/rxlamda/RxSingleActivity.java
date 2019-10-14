package reactive.android.albul.ru.rxlamda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import reactive.android.albul.ru.rxlamda.presenter.Presenter;

public class RxSingleActivity extends AppCompatActivity {

    private String TAG = "RX_PRESENTER";
    private Presenter rxPresenter;
    private Single<String> singleObserver;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_single);

        rxPresenter = new Presenter();
        singleObserver = rxPresenter.getMsg();

        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_get_msg})
    public void subscribeSingle(View v){
        disposable = singleObserver.observeOn(AndroidSchedulers.mainThread()).subscribe(
                string -> Log.d(TAG, "onComplete: " + Thread.currentThread().getName() + " " + string),
                throwable -> Log.d(TAG, "onError: " + throwable));
    }

}
