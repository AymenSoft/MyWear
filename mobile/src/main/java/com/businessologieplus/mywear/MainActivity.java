package com.businessologieplus.mywear;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.patloew.rxwear.GoogleAPIConnectionException;
import com.patloew.rxwear.RxWear;

public class MainActivity extends AppCompatActivity {

    private RxWear rxWear;

    LinearLayout llmain;
    EditText etmsg;
    Button btnsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rxWear = new RxWear(this);

        llmain=(LinearLayout)findViewById(R.id.ll_main);
        etmsg=(EditText)findViewById(R.id.et_text);
        btnsend=(Button)findViewById(R.id.btn_send);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxWear.message().sendDataMapToAllRemoteNodes("/message")
                        .putString("title", etmsg.getText().toString())
                        .putString("message", "My message")
                        .toObservable()
                        .subscribe(requestId -> Snackbar.make(llmain, "Sent message", Snackbar.LENGTH_LONG).show(),
                                throwable -> {
                                    Log.e("MainActivity", "Error on sending message", throwable);

                                    if(throwable instanceof GoogleAPIConnectionException) {
                                        Snackbar.make(llmain, "Android Wear app is not installed", Snackbar.LENGTH_LONG).show();
                                    } else {
                                        Snackbar.make(llmain, "Could not send message", Snackbar.LENGTH_LONG).show();
                                    }
                                });
            }
        });



    }


}
