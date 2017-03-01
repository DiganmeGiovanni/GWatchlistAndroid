package org.gwatchlist.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 *
 * Created by giovanni on 28/02/17.
 */
public class GraphicUtils {
    private static ProgressDialog progressDialog;

    public static void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public static void showAlert(Context mContext, String title, String message) {
        new AlertDialog.Builder(mContext)
                .setTitle(title)
                .setMessage(message)
                .show();
    }

    public static void showProgressDialog(Context mContext, String title, String message,
                                          boolean cancelable) {
        progressDialog = ProgressDialog.show(
                mContext,
                title,
                message
        );

        progressDialog.setCancelable(cancelable);
    }


}
