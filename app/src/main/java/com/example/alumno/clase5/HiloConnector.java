package com.example.alumno.clase5;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

/**
 * Created by alumno on 28/04/2016.
 */
public class HiloConnector extends Thread
{
    Handler _handler;
    String _url;
    boolean _mostrarImagen;

    public HiloConnector(Handler handler,String url, boolean imagen)
    {
        this._handler = handler;
        this._url = url;
        this._mostrarImagen = imagen;
    }

    @Override
    public  void run()
    {
        String retorno ="";
        byte[] bytes = new byte[0];
        Message msg = new Message();
        HttpManager httpManager = new HttpManager(_url);

        if(_mostrarImagen) //Muestro una imagen
        {
            try {
                bytes = httpManager.getBytesDataByGET();
            } catch (IOException e) {
                e.printStackTrace();
            }
            msg.arg1 = 2;
            msg.obj = bytes;
        }
        else {
            try {
                retorno = httpManager.getStrDataByGET();
            } catch (IOException e) {
                e.printStackTrace();
            }
            msg.arg1 = 1;
            msg.obj = retorno;
        }
        _handler.sendMessage(msg);
    }
}
