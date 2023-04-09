package kw.learn.androidbezier.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/6 9:40
 */
public class ImageLoaderHelper {


    /**
     * <li> 默认option配置 </li>,可设置默认显示图片
     */
    public static DisplayImageOptions buildDisplayImageOptionsDefault(int drawable) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1024*2;
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(drawable)
                .showImageForEmptyUri(drawable)
                .showImageOnFail(drawable)
                .decodingOptions(options)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    /**
     * <li> 默认option配置 </li>
     */
    public static DisplayImageOptions buildDisplayImageOptionsPager() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        return new DisplayImageOptions.Builder()
                .decodingOptions(options)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

}
