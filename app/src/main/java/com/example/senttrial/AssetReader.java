package com.example.senttrial;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AssetReader {
    private Context mContext;

    public AssetReader(Context context) {
        mContext = context;
    }

    public String readAsset(String fileName) {
        AssetManager assetManager = mContext.getAssets();
        try {
            InputStream is = assetManager.open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
