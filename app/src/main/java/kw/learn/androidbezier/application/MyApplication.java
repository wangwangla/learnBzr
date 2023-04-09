package kw.learn.androidbezier.application;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;
import java.io.IOException;

import kw.learn.androidbezier.constant.Constant;
import kw.learn.androidbezier.utils.SDCardUtils;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/5 7:44
 */
public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = null;
        instance = this;
        initImageLoader(this);
    }

    private void initImageLoader(Context context) {
        try {
            SDCardUtils.createFolder(Constant.DEFAULT_IMAGE_CACHE_PATH);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(new UnlimitedDiskCache(new File(Constant.DEFAULT_IMAGE_CACHE_PATH)))
//				.discCache(DiscCacheFactory.create(context, SDCardStoragePath.DEFAULT_IMAGE_CACHE_PATH))
                .memoryCacheSizePercentage(8)
                .memoryCacheExtraOptions(480, 800)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .imageDownloader(new BaseImageDownloader(context))
                .writeDebugLogs()
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }
}
