package com.testphase.materialtest.logging;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Helper class used for logging and debugging
 */
public class L {

    public static void m(String message) {
        Log.d("DEBUG: ", "" + message);
    }

    public static void t(Context context, String message) {
        Toast.makeText(context, message + "", Toast.LENGTH_SHORT).show();
    }
    public static void T(Context context, String message) {
        Toast.makeText(context, message + "", Toast.LENGTH_LONG).show();
    }
}
