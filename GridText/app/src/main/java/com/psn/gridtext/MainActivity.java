package com.psn.gridtext;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button btStart;
    private GridView noscrollGridView;
    private GridAdapter gridAdapter;
    private int count=6;
    private int position=0;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btStart = (Button) findViewById(R.id.bt_start);
        btStart.setOnClickListener(this);
        noscrollGridView = (GridView) findViewById(R.id.noScrollgridview);

        gridAdapter = new GridAdapter(this,count);
        noscrollGridView.setAdapter(gridAdapter);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        gridAdapter.notifyDataSetChanged();
                        break;
                }
                super.handleMessage(msg);
            }
        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start:
                new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            gridAdapter.setChangId(position++);
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(position==count+1){
                                position=0;
                                break;
                            }

                        }

                    }
                }.start();
        }
    }
}
