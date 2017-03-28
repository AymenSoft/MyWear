package com.businessologieplus.mywear;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageApi;
import com.patloew.rxwear.RxWear;
import com.patloew.rxwear.transformers.MessageEventGetDataMap;

import rx.subscriptions.CompositeSubscription;

public class MainActivity extends WearableActivity {

    private CompositeSubscription subscription = new CompositeSubscription();

    private RxWear rxWear;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rxWear = new RxWear(this);

        mTextView = (TextView) findViewById(R.id.text);

        subscription.add(rxWear.message().listen("/message", MessageApi.FILTER_LITERAL)
                .compose(MessageEventGetDataMap.noFilter())
                .subscribe(dataMap -> mTextView.setText(dataMap.getString("title", "no msg")), throwable -> Toast.makeText(this, "Error on message listen", Toast.LENGTH_LONG).show()));
    }



}
