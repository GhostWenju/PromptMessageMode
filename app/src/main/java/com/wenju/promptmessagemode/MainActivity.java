package com.wenju.promptmessagemode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wenju.promptmessagemode.util.WenJuPromptMessageUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnOnclick(View view){
        WenJuPromptMessageUtil
                .getInstance(this)
                .setToastMessage("登录成功")
                .setSnackBarOnclickText("取消")
                .setSnackBarActionListener(new MyUndoListener())
                .simpleSnackBarShow();
    }

    public class MyUndoListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
        }
    }

}
