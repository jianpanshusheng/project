package demo.checkdemo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.VideoView;

import demo.checkdemo.view.RippleLayout;
import demo.checkdemo.view.WaveView;

public class SecondActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private RippleLayout rippleLayout;
    private VideoView videoview;
    private String path = "http://yun.it7090.com/video/XHLaunchAd/video01.mp4";
    private Handler handler;
    private static final int Nou = 512;


    private WaveView waveView;
    private Button btnOpen, btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        initAnimator();
        startAnimator();
    }

    private void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//保持屏幕常亮
        videoview = (VideoView) findViewById(R.id.videoview);
//        rippleLayout = (RippleLayout) findViewById(R.id.rp);

        //设置网络地址
        videoview.setVideoURI(Uri.parse(path));
        videoview.start();


        //设置监听
        videoview.setOnCompletionListener(this);
        videoview.setOnErrorListener(this);
        videoview.setOnPreparedListener(this);
        resetWidth();


        //扫描蓝牙的动画布局
        waveView = (WaveView) findViewById(R.id.wave);
        btnOpen = (Button) findViewById(R.id.btOpen);
        btnClose = (Button) findViewById(R.id.btClose);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waveView.start();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waveView.stop();
            }
        });


    }

    /**
     * 重新设置播放屏幕的宽度和高度;
     */
    public void resetWidth() {
        int dw = getWindowManager().getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams lp = videoview.getLayoutParams();
        lp.width = dw;
        lp.height = 768;
        videoview.setLayoutParams(lp);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        videoview.stopPlayback();//如果有错的话别弹出“无法播放该视频”阻止用户操作，直接就不让播了吧
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        mp.setLooping(true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoview.stopPlayback();

        stopAnimator();
    }


    private void initAnimator() {
        rippleLayout.post(new Runnable() {
            public void run() {
                rippleLayout.init(rippleLayout.getWidth() / 2,//中心点x
                        rippleLayout.getHeight() / 2,//中心点y
                        10,//波纹的初始半径
                        Math.min(rippleLayout.getWidth(), rippleLayout.getHeight()) / 2,//波纹的结束半径
                        2100,//duration
                        getResources().getColor(R.color.colorBlue),//颜色..
                        new DecelerateInterpolator());//开始快,后来慢
            }
        });


        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                if (Nou == msg.what) {
                    rippleLayout.doRipple();
                    handler.sendEmptyMessageDelayed(Nou, 700);
                }
            }

        };

    }

    private void startAnimator() {
        handler.sendEmptyMessage(Nou);
    }

    private void stopAnimator() {
        if (handler != null) {
            handler.removeMessages(Nou);
        }

    }
}
