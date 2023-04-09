package kw.learn.androidbezier.theme;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/9 12:09
 */
public class ThemeUtils {
    public static void setStatusBarColor(Activity activity,boolean isBlack){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final View decorView = activity.getWindow().getDecorView();
            final int systemUiVisibility = decorView.getSystemUiVisibility();
//          黑
            if (isBlack) {
                decorView.setSystemUiVisibility(systemUiVisibility | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decorView.setSystemUiVisibility(systemUiVisibility & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }
}
