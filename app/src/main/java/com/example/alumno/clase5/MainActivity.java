package com.example.alumno.clase5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  Handler.Callback {

    private Handler handler;
    private TextView tv;
    private ImageView imgView;
    private MiHilo mihilo;
    private HiloConnector hiloTexto;
    private HiloConnector hiloImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)this.findViewById(R.id.lblHola);
        imgView = (ImageView)this.findViewById(R.id.imageView);
        handler = new Handler(this);
        hiloImagen = new HiloConnector(handler,"http://192.168.2.130:8080/android/koala.png",true);
        hiloImagen.start();
        hiloTexto = new HiloConnector(handler,"http://192.168.2.130:8080/android/clase6.xml",false);
        hiloTexto.start();
        //mihilo = new MiHilo(handler);
        //Thread t = new Thread(mihilo);
        //mihilo.start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        byte[] bytes;
        switch (msg.arg1)
        {
            case 1:
                tv.setText(msg.obj.toString());
                break;
            case 2:
                // Convertir bytes a bitmap
                bytes = (byte[]) msg.obj;
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                //ImageView imageView = (ImageView)this.findViewById(R.id.imageView);
                // Set the Bitmap data to the ImageView
                imgView.setImageBitmap(bmp);
                break;
        }
        return false;
    }

}
