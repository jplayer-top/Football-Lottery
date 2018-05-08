package top.jplayer.baseprolibrary.live;

import android.content.Intent;
import android.view.View;

import top.jplayer.baseprolibrary.BuildConfig;
import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.live.service.BackgroundService;
import top.jplayer.baseprolibrary.live.service.GrayService;
import top.jplayer.baseprolibrary.live.service.WhiteService;
import top.jplayer.baseprolibrary.ui.SuperBaseActivity;

/**
 * Created by Obl on 2018/4/3.
 * top.jplayer.baseprolibrary.live
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class LiveStartActivity extends SuperBaseActivity implements View.OnClickListener {
    @Override
    protected int initRootLayout() {
        return R.layout.activity_live;
    }

    @Override
    public void initRootData(View view) {
        view.findViewById(R.id.btn_white).setOnClickListener(this);
        view.findViewById(R.id.btn_gray).setOnClickListener(this);
        view.findViewById(R.id.btn_black).setOnClickListener(this);
        view.findViewById(R.id.btn_background_service).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_white) { //系统正常的前台Service，白色保活手段
            Intent whiteIntent = new Intent(getApplicationContext(), WhiteService.class);
            startService(whiteIntent);

        } else if (viewId == R.id.btn_gray) {//利用系统漏洞，灰色保活手段（API < 18 和 API >= 18 两种情况）
            Intent grayIntent = new Intent(getApplicationContext(), GrayService.class);
            startService(grayIntent);

        } else if (viewId == R.id.btn_black) { //拉帮结派，黑色保活手段，利用广播唤醒队友
            Intent blackIntent = new Intent();
            blackIntent.setAction(BuildConfig.APPLICATION_ID);
            sendBroadcast(blackIntent);

            /*AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            PendingIntent operation = PendingIntent.getBroadcast(this, 123, blackIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis(), operation);*/

        } else if (viewId == R.id.btn_background_service) {//普通的后台进程
            Intent bgIntent = new Intent(getApplicationContext(), BackgroundService.class);
            startService(bgIntent);

        }
    }

}
