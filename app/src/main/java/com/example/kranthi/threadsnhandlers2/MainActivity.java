package com.example.kranthi.threadsnhandlers2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Thread thread;
    ProgressBar progressBar;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        thread = new Thread(new MyThread());
        thread.start();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progressBar.setProgress(msg.arg1);
                if (progressBar.getProgress()==10) {
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"Download Successfull", Toast.LENGTH_LONG).show();
                    //Log.i("progress is", String.valueOf(progressBar.getProgress()));
                }
            }
        };
    }


    public class MyThread implements Runnable {


        @Override
        public void run() {
            for (int i = 0; i <=10; i++) {

                try {
                    Message message = Message.obtain();
                    message.arg1=i;
                    handler.sendMessage(message);
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
