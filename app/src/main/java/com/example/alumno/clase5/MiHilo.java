package com.example.alumno.clase5;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.security.MessageDigest;

/**
 * Created by alumno on 28/04/2016.
 */
public class MiHilo extends Thread
{
    Handler _handler;

    public MiHilo(Handler handler)
    {
        this._handler = handler;
    }

    @Override
    public void run()
    {
        Message msg = new Message();
        for(int i = 0; i< 5; i++)
        {
            msg.arg1 = 1;
            msg.obj = "Hola" + i;
            _handler.sendMessage(msg);
            Log.d("Logg","Hola" + i);
            try {
                this.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

