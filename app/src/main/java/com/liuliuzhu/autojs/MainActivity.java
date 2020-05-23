package com.liuliuzhu.autojs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liuliuzhu.autojs.utils.OpenFileUtils;
import com.liuliuzhu.autojs.utils.ShareFileUtils;

import java.io.File;

/**
 * @author 溜溜猪
 * @date 2020/05/23
 * @desc 微信公众号：AI小子
 */
public class MainActivity extends AppCompatActivity implements Config {
    private Button mBtnOpen;
    private Button mBtnShare;
    private TextView mViewFilePath;

    private final int REQUEST_EXTERNAL_STORAGE = 1;

    private String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnOpen = findViewById(R.id.openFile);
        mBtnShare = findViewById(R.id.shareFile);
        mViewFilePath = findViewById(R.id.filePath);
        verifyStoragePermissions(this);
        init();
    }

    private void init() {
        final Boolean aBoolean = checkFile(FILEPATH);
        if (aBoolean) {
            mViewFilePath.setText(FILEPATH);
        }
        // 打开文件
        mBtnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aBoolean) {
                    openFile(FILEPATH);
                } else {
                    Toast.makeText(MainActivity.this, "文件不存在", Toast.LENGTH_LONG).show();
                }
            }
        });

        // 分享文件
        mBtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aBoolean) {
                    ShareFileUtils.shareFile(MainActivity.this, FILEPATH);
                } else {
                    Toast.makeText(MainActivity.this, "文件不存在", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * 打开文件
     * @param path
     */
    public void openFile(String path) {
        File file = new File(path);
        try {
            OpenFileUtils.openFile(this, file);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "无可用打开方式", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    /**
     * 检查文件是否存在
     * @param path
     * @return
     */
    private Boolean checkFile(String path) {
        File file = new File(path);
        return file.exists();
    }
}
