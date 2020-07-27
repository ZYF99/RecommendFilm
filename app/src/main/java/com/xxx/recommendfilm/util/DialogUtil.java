package com.xxx.recommendfilm.util;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.xxx.recommendfilm.R;

import javax.inject.Singleton;

@Singleton
public class DialogUtil {
    static DialogUtil dialogUtil;
    public AlertDialog progressDialog;

    private DialogUtil() {

    }

    public void showProgressDialog(Context context) {
        if (progressDialog == null)
            progressDialog = new AlertDialog.Builder(context).setView(R.layout.loading_layout).setCancelable(false).create();
        if (!progressDialog.isShowing()) progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.hide();
    }

    public static DialogUtil getInstance() {
        if (dialogUtil == null)
            dialogUtil = new DialogUtil();
        return dialogUtil;
    }

}
