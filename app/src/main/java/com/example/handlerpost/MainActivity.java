package com.example.handlerpost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView textView;
    private MyThread myThread;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.threadtext);
        btn = (Button)findViewById(R.id.threadbtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myThread = new MyThread();
                myThread.start();
            }
        });
        handler = new Handler();
    }

    class MyThread extends Thread{

        @Override
        public void run(){
            for(int i= 0 ; i<100; i++){

                Message message = handler.obtainMessage();
                message.what = i;

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       textView.setText(message.what+ " values");
                    }
                });
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}