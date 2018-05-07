package cn.novate.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * Email: 2185134304@qq.com
 * Created by Novate 2018/5/7 13:40
 * Version 1.0
 * Params:
 * Description:    自定义的SurfaceView - 用于实现动画效果
*/

public class DemoSurfaceView extends SurfaceView implements Callback{

    LoopThread thread;


    public DemoSurfaceView(Context context) {
        super(context);
        //初始化,设置生命周期回调方法
        init();
    }


    public DemoSurfaceView(Context context,AttributeSet attrs){
        super(context , attrs);
        //初始化,设置生命周期回调方法
        init();
    }


    private void init(){
        SurfaceHolder holder = getHolder();
        //设置Surface生命周期回调
        holder.addCallback(this);

        // LoopThread线程用于绘制图形
        thread = new LoopThread(holder, getContext());
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.isRunning = true;
        thread.start();
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // 注意3：通过标志位关闭LoopThread线程
        thread.isRunning = false;
        try {
            // 关闭LoopThread线程，这里使用join()方法
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行绘制的绘制线程
     */
    class LoopThread extends Thread{

        SurfaceHolder surfaceHolder;
        Context context;
        boolean isRunning;
        float radius = 10f;
        Paint paint;

        public LoopThread(SurfaceHolder surfaceHolder,Context context){

            this.surfaceHolder = surfaceHolder;
            this.context = context;
            isRunning = false;

            paint = new Paint();
            paint.setColor(Color.GREEN);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth((float) 10.0); // 宽度
        }

        @Override
        public void run() {
            Canvas c = null;
            while(isRunning){
                try{
                    // 注意1：同步锁为了防止多个线程同时操作同一个Canvas的c
                    synchronized (surfaceHolder) {
                        c = surfaceHolder.lockCanvas(null);
                        doDraw(c);
                        //通过它来控制帧数执行一次绘制后休息50ms
                        Thread.sleep(50);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }

        public void doDraw(Canvas c){
            // 注意2：SurfaceView特点：会保留之前绘制的图像，这里需要首先清空上一次绘制的图形
            // 自定义View不用手动清理，自定义View在调用onDraw()方法就已经自动清除掉视图中的东西
            c.drawColor(Color.BLACK);
            c.translate(200, 200);
            c.drawCircle(0,0, radius++, paint);
            if(radius > 100){
                radius = 10f;
            }
        }
    }
}