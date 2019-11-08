package com.kingleoners.mycinema.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.kingleoners.mycinema.R;
import com.kingleoners.mycinema.helper.AlarmHelper;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener{

    private AlarmHelper alarm;
    private Switch dailyNotif, releaseNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        dailyNotif = findViewById(R.id.daily_notification);
        releaseNotif = findViewById(R.id.release_notification);
        alarm = new AlarmHelper();

        if(alarm.isAlarmHasSet(this,AlarmHelper.DAILY_REMINDER_MOVIE)){
            dailyNotif.setChecked(true);
        }else{
            dailyNotif.setChecked(false);
        }
        if(alarm.isAlarmHasSet(this,AlarmHelper.DAILY_REMINDER_RELEASE)){
            releaseNotif.setChecked(true);
        }else{
            releaseNotif.setChecked(false);
        }

        dailyNotif.setOnClickListener(this);
        releaseNotif.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.daily_notification:
                if(dailyNotif.isChecked()){
                    alarm.setDailyAlarm(this,AlarmHelper.DAILY_REMINDER_MOVIE);
                    Toast.makeText(this,getString(R.string.daily_has_on),Toast.LENGTH_SHORT).show();
                }else{
                    alarm.cancelAlarm(this, AlarmHelper.DAILY_REMINDER_MOVIE);
                    Toast.makeText(this, getString(R.string.daily_has_off),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.release_notification:
                if(releaseNotif.isChecked()){
                    alarm.setDailyRelease(this, AlarmHelper.DAILY_REMINDER_RELEASE);
                    Toast.makeText(this, getString(R.string.release_has_on),Toast.LENGTH_SHORT).show();
                }else{
                    alarm.cancelAlarm(this, AlarmHelper.DAILY_REMINDER_RELEASE);
                    Toast.makeText(this, getString(R.string.release_has_off),Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
