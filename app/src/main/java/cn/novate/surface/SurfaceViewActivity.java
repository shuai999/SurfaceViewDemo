package cn.novate.surface;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2018/5/7.
 */

public class SurfaceViewActivity extends Activity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_surface_view);

        DemoSurfaceView surface_view = (DemoSurfaceView) findViewById(R.id.surface_view);
    }
}
