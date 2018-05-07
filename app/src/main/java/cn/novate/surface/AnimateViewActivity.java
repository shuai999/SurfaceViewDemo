package cn.novate.surface;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Email: 2185134304@qq.com
 * Created by Novate 2018/5/7 13:25
 * Version 1.0
 * Params:
 * Description:    自定义View - 实现动画效果
*/

public class AnimateViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 這邊傳入的this代表這個對象，因為Activity是繼承自Content類的，因此該對象也
        // 可向上轉型為Content類型作為AnimateView的構造方法的參數
        setContentView(new AnimateView(this));

    }

    class AnimateView extends View {

        float radius = 10;
        Paint paint;

        public AnimateView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.GREEN);  // 圆圈颜色
            paint.setStyle(Paint.Style.STROKE); // 线条模式  文字、图像只显示边界
            paint.setStrokeWidth((float) 10.0); // 宽度
        }

        @Override
        protected void onDraw(Canvas canvas) {

            canvas.translate(200, 200);
            canvas.drawCircle(0, 0, radius++, paint);

            if(radius > 100){
                radius = 10;
            }

            invalidate();//通过调用这个方法让系统自动刷新视图

        }

    }
}
