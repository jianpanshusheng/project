package demo.checkdemo;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import demo.checkdemo.util.DensityUtil;
import demo.checkdemo.util.MathUtil;
import demo.checkdemo.util.TransformUtils;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "main";
    private SurfaceView surfaceview;
    private SurfaceHolder holder;

    private int mVideoHeight;
    private int mVideoWidth;
    private int mVideoVisibleHeight;
    private int mVideoVisibleWidth;
    private int mSarNum;
    private int mSarDen;

    private static final int HANDLER_SIZE = 512;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_SIZE:
                    changeSurfaceSize();
                    break;
            }
        }
    };

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

//        startVideo();
        String str = "55 AA 30 00 22 00 30 31 44 47 35 30 4B 58 59 41 56 51 45 46 44 67 4D 47 44 41 45 2F 37 6B 47 46 4A 74 6F 31 78 69 61 72 ".trim();
        String check = MathUtil.getCheck(str);
        Log.e(TAG, "onCreate: " + check);
//        01DG50KXYAVQEFDgMGDAE/7kGFJto1xiar

        String s = hexStringToString("30 31 44 47 35 30 4B 58 59 41 56 51 45 46 44 67 4D 47 44 41 45 2F 37 6B 47 46 4A 74 6F 31 78 69 61 72".trim());
        String s1 = hexStringToString("AA");
        Log.e(TAG, "onCreate: s:" + s + "   s1:" + s1);


        //px dp 转换
        int i = DensityUtil.px2dip(this, 30);
        int i1 = DensityUtil.px2dip(this, 50);
        int i2 = DensityUtil.px2dip(this, 70);
        int i3 = DensityUtil.px2dip(this, 80);
        int i4 = DensityUtil.px2dip(this, 100);
        int i5 = DensityUtil.px2dip(this, 200);
        int i6 = DensityUtil.px2dip(this, 300);
        int i7 = DensityUtil.px2dip(this, 345);
        int i8 = DensityUtil.px2dip(this, 350);

        Log.e(TAG, "onCreate: i:"+i );
        Log.e(TAG, "onCreate: i1:"+i1 );
        Log.e(TAG, "onCreate: i2:"+i2 );
        Log.e(TAG, "onCreate: i3:"+i3 );
        Log.e(TAG, "onCreate: i4:"+i4 );
        Log.e(TAG, "onCreate: i5:"+i5);
        Log.e(TAG, "onCreate: i6:"+i6 );
        Log.e(TAG, "onCreate: i7:"+i7 );
        Log.e(TAG, "onCreate: i8:"+i8 );


        int s0 = DensityUtil.px2dip(this, 30);
        int s2 = DensityUtil.px2dip(this, 35);
        int s3 = DensityUtil.px2dip(this, 40);
        int s4 = DensityUtil.px2dip(this, 45);
        int s5 = DensityUtil.px2dip(this, 50);
        int s6 = DensityUtil.px2dip(this, 55);
        int s7 = DensityUtil.px2dip(this, 60);
        int s8 = DensityUtil.px2dip(this, 65);
        int s9 = DensityUtil.px2dip(this, 70);
        int s10 = DensityUtil.px2dip(this, 75);
        int s11 = DensityUtil.px2dip(this, 80);
        int s12 = DensityUtil.px2dip(this, 85);
        int s13 = DensityUtil.px2dip(this, 90);


        Log.e(TAG, "onCreate: s0--->"+s0 );
        Log.e(TAG, "onCreate: s2--->"+s2 );
        Log.e(TAG, "onCreate: s3--->"+s3 );
        Log.e(TAG, "onCreate: s4--->"+s4 );
        Log.e(TAG, "onCreate: s5--->"+s5 );
        Log.e(TAG, "onCreate: s6--->"+s6 );
        Log.e(TAG, "onCreate: s7--->"+s7 );
        Log.e(TAG, "onCreate: s8--->"+s8 );
        Log.e(TAG, "onCreate: s9--->"+s9 );
        Log.e(TAG, "onCreate: s10--->"+s10 );
        Log.e(TAG, "onCreate: s11--->"+s11 );
        Log.e(TAG, "onCreate: s11--->"+s11 );

    }


    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }


    private void changeSurfaceSize() {
        // get screen size
        int dw = getWindowManager().getDefaultDisplay().getWidth();
        holder.setFixedSize(mVideoWidth, mVideoHeight);
        ViewGroup.LayoutParams lp = surfaceview.getLayoutParams();
        lp.width = dw;
        lp.height = 787;
        surfaceview.setLayoutParams(lp);
        surfaceview.invalidate();
    }


    private void initView() {
        surfaceview = (SurfaceView) findViewById(R.id.surface_view);
        tv = (TextView) findViewById(R.id.tv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, demo.checkdemo.SecondActivity.class));
//                startActivity(new Intent(MainActivity.this, ThreeActivity.class));
//                startActivity(new Intent(MainActivity.this, KeyActivity.class));
//                String[] strings = TransformUtils.encode(str);
//                Log.e(TAG, "onClick:encode " + encode);


//                String s = strTo16(str2);
//                byte[] bytes = TransformUtils.hexStringToBytes(s);
//                int i = Integer.parseInt("0x3c");
//                Log.e(TAG, "onClick: " + i);


//                0x25b2 16进制
//                String hex = hexStringToString("0x3c");
//                Log.e(TAG, "onClick:hex " + hex);


//                TransformUtils.hexStringToBytes(str2);
            }
        });


    }

    String[] str = {"0x0a", "0x1B", "0x61", "0x01", "0x1D", "0x28", "0x6B", "0x0A", "0x00", "0x31", "0x50", "0x31"};

    int[] in = {0x0a, 0x1B, 0x61, 0x01, 0x1D, 0x28, 0x6B, 0x0A, 0x00, 0x31, 0x50, 0x31};


    String str1 = "<LHT656,432,0,40><F50><HW2,5><RC265,40><RL>▲<F50><HW2,1><RC330,104><RL>太古汇地下停车场<F5><HW1,1><RC30,176><NR>2014-12-19 15:31:47<F50><HW1,1><RC65,176><NR>入口一<X3,H><RC95,186><B2Dn,P,8,12345678><F5><HW1,1><RC330,266><NR>4963401<F50><HW1,1><RC360,176><NR>HLD <F50><HW1,1><RC376,472><RL>出口回收：正面朝上塞卡 <F14><HW1,1><RC350,504><RL>Insert the ticket <F14><HW1,1><RC370,536><RL>into the card reader,<F14><HW1,1><RC345,568><RL>with the face up,<F14><HW1,1><RC330,600><RL>in order to exit <F50><HW1,1><RC408,632><RL>先到收费处交费，再取车出场<P>";

    String str2 = "<LHT656,432,0,40>";
    String str3 = "<F50><HW2,5><RC265,40><RL>▲<F50>";

    public String strTo16(String s) {
        String str = "";
        String[] array = new String[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            Log.e(TAG, "strTo16: " + ch);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
            array[i] = s4;
        }
        return str;
    }
}
