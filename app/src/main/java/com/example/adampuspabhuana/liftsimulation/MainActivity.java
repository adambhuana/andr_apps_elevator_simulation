package com.example.adampuspabhuana.liftsimulation;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView hsl;
    RadioGroup p1;
    RadioGroup p2;
    Button cek;
    public int pl1,pl2,i;
    public TextView mLink;
    public Handler timeHandler= new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1=(RadioGroup) findViewById(R.id.pemain1);
        p1.setOnCheckedChangeListener(cek1);
        p2=(RadioGroup) findViewById(R.id.pemain2);
        p2.setOnCheckedChangeListener(cek2);
        cek=(Button) findViewById(R.id.btnhasil);
        hsl=(TextView) findViewById(R.id.hasilsuit);
        cek.setOnClickListener(hasil1);
        //mLink = (TextView) findViewById(R.id.link);
        //if (mLink != null) {
          //  mLink.setMovementMethod(LinkMovementMethod.getInstance());
        //}

    }
    RadioGroup.OnCheckedChangeListener cek1= new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId)
            {
                case R.id.jempol1:
                    pl1=1;
                    break;
                case R.id.telunjuk1:
                    pl1=2;
                    break;
                case R.id.kelingking1:
                    pl1=3;
                    break;

            }
        }
    };
    RadioGroup.OnCheckedChangeListener cek2= new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId2) {
            switch(checkedId2)
            {
                case R.id.jempol2:
                    pl2=1;
                    break;
                case R.id.telunjuk2:
                    pl2=2;
                    break;
                case R.id.kelingking2:
                    pl2=3;
                    break;

            }
        }
    };
    Button.OnClickListener hasil1= new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            try {
                pemainsatu(pl1, pl2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private void pemainsatu(final int pl1, final int pl2) throws InterruptedException {

        final int sum,sum1;
        Handler textNextHandler = new Handler();
        if((pl1==pl2))
        {
            hsl.setText("Lift Tidak Akan Bergerak, Karena Berada di Posisi yang sama");
        }
        else {
            if (pl1<pl2) {
                hsl.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hsl.setText("Lift Akan Naik, setiap lantai akan bergerak selama 3 Detik");

                                    hsl.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            hsl.setText("Tiba di lantai Tujuan, yaitu Lantai: "+ String.valueOf(pl2));
                                        }
                                    }, 3000);
                                    hsl.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            for(i=pl1+1;i<pl2;i++) {
                                                hsl.setText("Lantai: " + String.valueOf(i));
                                            }

                                        }
                                    }, 1000);



                    }
                }, 500);
            }
            else
            {
                if(pl1>pl2){
                    hsl.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hsl.setText("Lift Akan Turun, setiap lantai akan bergerak selama 3 Detik");

                            hsl.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    hsl.setText("Tiba di lantai Tujuan, yaitu Lantai: "+ String.valueOf(pl2));
                                }
                            }, 3000);
                            hsl.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    for(i=pl1-1;i>pl2;i--) {
                                        hsl.setText("Lantai: " + String.valueOf(i));
                                    }

                                }
                            }, 1000);



                        }
                    }, 500);


                }
                else {

                }
            }
        }

    };
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuapps, menu);
        return  true;
    }
    public boolean  onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.option1:
                exit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Kamu Benar-Benar ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.dismiss();
                        ((Activity) MainActivity.this).finish();

                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int arg1) {
                        dialog.cancel();}

                })
                .show();


    }
}
