package kw.learn.androidbezier;

import android.Manifest;
import android.app.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import kw.learn.androidbezier.adapter.ImageFolderAdapter;
import kw.learn.androidbezier.bean.ImageFolderBean;
import kw.learn.androidbezier.core.ImageSelectObservable;
import kw.learn.androidbezier.listener.OnRecyclerViewClickListener;
import kw.learn.androidbezier.utils.ImageUtils;
import kw.learn.androidbezier.view.TitleView;


/**
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　 ████━████     ┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　 　 ┗━━━┓
 * 　　　　　　　　　┃ 神兽保佑　　 ┣┓
 * 　　　　　　　　　┃ 代码无BUG   ┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 *
 * @Auther jian xian si qi
 * @Date 2023/4/6 9:32
 */
public class FolderListActivity extends Activity implements Callback
        , OnRecyclerViewClickListener, View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    public static void startFolderListActivity(Activity context,
                                               int REQUEST_CODE,
                                               ArrayList<ImageFolderBean> photos,
                                               int sMaxPicNum) {
        Intent addPhoto = new Intent(context, FolderListActivity.class);
        addPhoto.putExtra("list", photos);
        addPhoto.putExtra("max_num", sMaxPicNum);
        context.startActivityForResult(addPhoto, REQUEST_CODE);
    }

    public static void startSelectSingleImgActivity (Activity context, int REQUEST_CODE) {
        Intent addPhoto = new Intent(context, FolderListActivity.class);
        addPhoto.putExtra("single", true);
        context.startActivityForResult(addPhoto, REQUEST_CODE);
    }

    /** 图片所在文件夹适配器 */
    private ImageFolderAdapter mFloderAdapter;
    /** 图片列表 */
    ArrayList<ImageFolderBean> mImageFolderList;
    /** 消息处理 */
    private Handler mHandler;

    private final int MSG_PHOTO = 10;

    private static final int DEFAULT_MAX_PIC_NUM = 9;
    /** 可选择图片总数 */
    public static int sMaxPicNum = DEFAULT_MAX_PIC_NUM;

    private final int REQUEST_ADD_OK_CODE = 22;

    private RecyclerView mRecyclerView;

    /** 是否选择单张图片 */
    private boolean mIsSelectSingleImge = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler(this);
        mImageFolderList = new ArrayList<>();
        sMaxPicNum = getIntent().getIntExtra("max_num", DEFAULT_MAX_PIC_NUM);

        mIsSelectSingleImge = getIntent().getBooleanExtra("single", false);
        setContentView(R.layout.photo_folder_main);
        initView();
        // 检查是否拥有读取外部存储器的权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // 已经授权，执行读取媒体文件的操作
            // ...
        } else {
            // 未授权，请求读取外部存储器的权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }

        ImageUtils.loadLocalFolderContainsImage(this, mHandler, MSG_PHOTO);
        ImageSelectObservable.getInstance().addSelectImagesAndClearBefore((ArrayList<ImageFolderBean>) getIntent().getSerializableExtra("list"));

        mFloderAdapter = new ImageFolderAdapter(this, mImageFolderList);
        mRecyclerView.setAdapter(mFloderAdapter);
        mFloderAdapter.setOnClickListener(this);
    }

    private void initView () {
        mRecyclerView = (RecyclerView) findViewById(R.id.lv_photo_folder);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        TitleView titleView = (TitleView) findViewById(R.id.tv_photo_title);
        titleView.getLeftBackImageTv().setOnClickListener(this);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_ADD_OK_CODE) {
                Intent intent = getIntent();
                ArrayList<ImageFolderBean> list = new ArrayList<>();
                list.addAll(ImageSelectObservable.getInstance().getSelectImages());
                intent.putExtra("list", list);
                setResult(RESULT_OK, intent);
                this.finish();
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_PHOTO:
                mImageFolderList.clear();
                mImageFolderList.addAll((Collection<? extends ImageFolderBean>) msg.obj);
                mFloderAdapter.notifyDataSetChanged();
                break;
        }
        return false;
    }


    @Override
    public void onItemClick(View view, int position) {
        File file = new File(mImageFolderList.get(position).path);
        ImageSelectActivity.startPhotoSelectGridActivity(this, file.getParentFile().getAbsolutePath(), mIsSelectSingleImge, sMaxPicNum, REQUEST_ADD_OK_CODE);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left_image:
                this.finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageSelectObservable.getInstance().clearSelectImgs();
        ImageSelectObservable.getInstance().clearFolderImages();
    }
}
