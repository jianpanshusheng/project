package demo.checkdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

public class ThreeActivity extends AppCompatActivity {


    private LinearLayout ll;
    private TextView tv, tv2;
    private ImageView iv;
    private String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        ll = (LinearLayout) findViewById(R.id.ll);
        tv = (TextView) findViewById(R.id.tv);
        tv2 = (TextView) findViewById(R.id.tv2);
        iv = (ImageView) findViewById(R.id.iv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSaveToImage(ll);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("TAG", "onClick: " + imagePath);
                try {
                    if (!"".equals(imagePath)) {
                        FileInputStream in = new FileInputStream(imagePath);
                        Bitmap bitmap = BitmapFactory.decodeStream(in);
                        Log.e("TAG", "bitmap: " + bitmap);
                        iv.setImageBitmap(bitmap);
                    }
                } catch (FileNotFoundException e) {
                    Log.e("TAG", "ex: " + e.getMessage().toString());
                    e.printStackTrace();
                }
            }
        });
    }

    public void viewSaveToImage(View view) {
        view.setDrawingCacheEnabled(true);////禁用DrawingCahce否则会影响性能
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        view.setDrawingCacheBackgroundColor(Color.WHITE);

        //把此view转换为图片
        Bitmap bitmap = loadBitmapFromView(view);

        FileOutputStream fos;
        try {
            //判断是否有SD卡
            String externalStorageState = Environment.getExternalStorageState();
            if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
                //SD卡根目录
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                File file = new File(externalStorageDirectory,   "print.png");
                fos = new FileOutputStream(file);
                imagePath = file.getAbsolutePath();
            } else {
                throw new Exception("创建文件失败");
            }
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("TAG", "path: " + imagePath);
        view.destroyDrawingCache();
    }


    /**
     * 将view转换为图片
     */
    private Bitmap loadBitmapFromView(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);//如果不设置canvas画布为白色，则生成透明

        view.layout(0, 0, width, height);
        view.draw(canvas);
        return bitmap;
    }
}
