package com.wenju.promptmessagemode.util;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wenju.promptmessagemode.R;

/**
 * @author wenju
 * Date 2018/12/10，Time 14:09
 * Toast 如果两个同时调用 Android5.0时先弹出第一个再弹出第二个，android9.0只弹出最后一个，其他未测试
 */
public class WenJuPromptMessageUtil {

    private static WenJuPromptMessageUtil wenJuPromptMessageUtil;
    private Activity activity;
    private String toastMessage = "默认Toast";
    private int durationTime = Toast.LENGTH_SHORT;
    private int layoutResource = R.layout.custom_toast;
    private int layoutId = R.id.custom_toast_container;
    private Snackbar snackbar;
    private Class<?> jumpCls;
    private View.OnClickListener snackBarActionListener;
    private String snackBarOnclickText;

    private WenJuPromptMessageUtil(Activity activity) {
        this.activity = activity;
    }

    public static WenJuPromptMessageUtil getInstance(Activity activity) {
        if (wenJuPromptMessageUtil == null) {
            wenJuPromptMessageUtil = new WenJuPromptMessageUtil(activity);
        }
        return wenJuPromptMessageUtil;
    }

    public WenJuPromptMessageUtil setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        return this;
    }

    public WenJuPromptMessageUtil setToastDurationTime(int durationTime) {
        this.durationTime = durationTime;
        return this;
    }

    public WenJuPromptMessageUtil setUserDefinedLayout(int layoutResource) {
        this.layoutResource = layoutResource;
        return this;
    }

    public WenJuPromptMessageUtil setUserDefinedLayoutId(int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public WenJuPromptMessageUtil setJumpActivity(Class<?> cls) {
        this.jumpCls = cls;
        return this;
    }

    public WenJuPromptMessageUtil setSnackBarActionListener(View.OnClickListener snackBarActionListener) {
        this.snackBarActionListener = snackBarActionListener;
        return this;
    }

    public WenJuPromptMessageUtil setSnackBarOnclickText(String snackBarOnclickText) {
        this.snackBarOnclickText = snackBarOnclickText;
        return this;
    }


    public WenJuPromptMessageUtil userDefinedToastShow() {
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(layoutResource,
                (ViewGroup) activity.findViewById(layoutId));

        TextView text = layout.findViewById(R.id.text);
        text.setText(toastMessage);

        Toast toast = new Toast(activity);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(durationTime);
        toast.setView(layout);
        toast.show();
        return this;
    }

    public WenJuPromptMessageUtil simpleToastShow() {
        Toast.makeText(activity, toastMessage, durationTime).show();
        return this;
    }

    public WenJuPromptMessageUtil simpleSnackBarShow() {
        if (snackbar == null) {
            snackbar = Snackbar.make(activity.findViewById(R.id.myCoordinatorLayout), toastMessage, durationTime);
            snackbar.show();
            if(snackBarActionListener!=null){
                snackbar.setAction(snackBarOnclickText,snackBarActionListener);
            }
            snackbar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                    if (jumpCls != null) {
                        Intent intent = new Intent(activity, jumpCls);
                        activity.startActivity(intent);
                    }
                    snackbar = null;
                }
            });
        }
        return this;
    }


}
