package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar seek;
    TextView time;
    Button control;
    CountDownTimer countDownTimer;
    boolean active=false;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seek = (SeekBar) findViewById(R.id.seekBar);
        time = (TextView) findViewById(R.id.txt);
        control = (Button) findViewById(R.id.controlbutton);


        seek.setMax(600);
        seek.setProgress(30);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    update(i);
                }


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(active==false)
                {
                    active=true;
                    seek.setEnabled(false);
                    control.setText("STOP");


                    countDownTimer =new CountDownTimer(seek.getProgress() * 1000, 1000) {
                        @Override
                        public void onTick(long l) {
                            update((int) l / 1000);
                        }

                        @Override
                        public void onFinish()
                        {
                                mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.horn);
                                  mediaPlayer.start();
                            Toast.makeText(MainActivity.this, "Time Up", Toast.LENGTH_SHORT).show();
                        }
                    }.start();
                }
                else
                {
                    active=false;
                    seek.setEnabled(true);
                    seek.setProgress(30);
                    time.setText("0:30");
                    countDownTimer.cancel();
                    mediaPlayer.stop();
                }

            }
        });


    }
    public void update(int progress)
    {
        String secString;
        int min=(int)progress/60;
        int sec=(int)progress-min*60;
        secString=String.valueOf(sec);
        if(sec<=9)
        {
            secString="0"+secString;
        }
        time.setText(String.valueOf(min)+":"+secString);

    }


}

