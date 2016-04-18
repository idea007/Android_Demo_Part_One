package com.example.idea.myapplication;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.idea.myapplication.common.BrightnessManager;
import com.example.idea.myapplication.view.MyVideoView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class VideoPlayerActivity extends AppCompatActivity {


    @Bind(R.id.mvv_video)
    MyVideoView mVideoView;

    @Bind(R.id.iv_play)
    ImageView mIvPlay;

    @Bind(R.id.tv_current_time)
    TextView mTvCurrentTime;
    @Bind(R.id.tv_surplus_time)
    TextView mTvSurplusTime;

    @Bind(R.id.sb_seekbar)
    SeekBar sb_seekbar;

    @Bind(R.id.sb_seekbar_sound)
    SeekBar sb_seekbar_sound;
    @Bind(R.id.sb_seekbar_brightness)
    SeekBar sb_seekbar_brightness;


    /**
     * 刷新播放时间和拖动条位置消息
     */
    private final static int PROGRESS_CHANGED = 0;
    /**
     * 在播放列表是发的消息
     */
    private final static int PAUSE = 3;

    private long lastTimemPlayListener;
    private boolean isPaused = false;

    private static long CLICK_INTERVAL = 800;   //两次点击 间隔时间

    /**
     * 手机或模拟器的屏幕宽
     */
    private static int screenWidth = 0;
    /**
     * 手机或模拟器的屏幕高
     */
    private static int screenHeight = 0;


    /**
     * 具体的数据的路径或网络地址
     */
    private Uri uri;

    /**
     * 声音管理
     */
    private AudioManager mAudioManager = null;

    /**
     * 当前媒体音量
     */
    private int currentVolume = 0;
    /** 当前亮度 */
    private float mBrightness = -1f;

    private BrightnessManager mBrightnessManager;




    /**
     * 用于处理拖动调进度更新、播放时间进度更新、隐藏控制面板消息
     */
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case PROGRESS_CHANGED:       //刷新进度条的消息

                    //得到当前播放位置
                    int curTime = mVideoView.getCurrentPosition();
                    int totalTime = mVideoView.getDuration();

                    //更新播放进度
                    sb_seekbar.setProgress(curTime);

                    //   播放网络视频
                    int j = mVideoView.getBufferPercentage();
                    int secondaryProgress = (j * sb_seekbar.getMax() / 100);
                    //更新缓冲了多少，通常播放网络资源时候用到
                    sb_seekbar.setSecondaryProgress(secondaryProgress);


                    updateTime(curTime, totalTime);

                    sendEmptyMessageDelayed(PROGRESS_CHANGED, 1000);
//                    dismissDialogFragment(BaseFullLoadingFragment.class);
                    break;

                case PAUSE:   //暂停播放消息
                    if (mVideoView != null) {
                        mVideoView.pause();
                    }
                    break;

            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();


        setContentView(R.layout.act_player);
        ButterKnife.bind(this);
        getScreenSize();
        loadPlayData();
        initController();
        initVideoView();
        ininSound();
        ininBrightness();

        startPlay();

    }

    private void ininBrightness() {
        mBrightnessManager = new BrightnessManager(this);
        sb_seekbar_brightness.setMax(255);
        sb_seekbar_brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setBrightness(sb_seekbar_brightness.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }



    private void ininSound() {
// 音频管理服务
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // 得到视频当前音量
        currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        sb_seekbar_sound.setMax(15);

        int progress=mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        sb_seekbar_sound.setProgress(progress);

        sb_seekbar_sound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setSound(Math.round(sb_seekbar_sound.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    /**
     * 设置屏幕亮度
     * @param progress
     */
    public void setBrightness(int progress){
        mBrightnessManager.setScreenBrightness(progress);

    }

    /**
     * 设置声音大小
     * @param value
     */
    public void setSound(int value){
        if (mAudioManager != null) {
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, value,
                    0);
        }
    }

    /**
     * 获得手机或模拟器屏蔽高和宽，并计算好控制面板的高度
     */
    private void getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        screenHeight = display.getHeight();
        screenWidth = display.getWidth();

    }


    private void initController() {
        mIvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //防止频繁点击导致错误
                long time = System.currentTimeMillis();
                if (time - lastTimemPlayListener < CLICK_INTERVAL) {
                    return;
                }
                lastTimemPlayListener = time;

                if (isPaused) {
                    mVideoView.start();
                    mIvPlay.setImageDrawable(getResources().getDrawable(R.drawable.selector_controller_play));

                } else {
                    mVideoView.pause();
                    mIvPlay.setImageDrawable(getResources().getDrawable(R.drawable.selector_controller_pause));
                }
                isPaused = !isPaused;

            }
        });

        sb_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            //当拖动发生改变的时候执行
            public void onProgressChanged(SeekBar seekbar, int progress,
                                          boolean fromUser) {

                updateTime(progress, sb_seekbar.getMax());
            }

            //当拖动刚触动的时候执行
            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            //当拖动刚触动的时候执行
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                showDialogFragment(BaseFullLoadingFragment.class);
                mVideoView.seekTo(sb_seekbar.getProgress());

            }
        });


    }

    private void initVideoView() {

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

//                setVideoScale(0);

                sb_seekbar.setMax(mVideoView.getDuration());
                mVideoView.start();
                mIvPlay.setImageDrawable(getResources().getDrawable(R.drawable.selector_controller_play));
                mHandler.sendEmptyMessage(PROGRESS_CHANGED);

            }
        });

        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        mIvPlay.setImageDrawable(getResources().getDrawable(R.drawable.selector_controller_pause));
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        mIvPlay.setImageDrawable(getResources().getDrawable(R.drawable.selector_controller_play));
                        if (!mVideoView.isPlaying()) {
                            mVideoView.start();
                        }
                        break;

                    default:
                        break;
                }

                return true;
            }
        });

        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if (mVideoView != null) {
                    mVideoView.stopPlayback();
                }
                return true;
            }
        });

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (uri != null) {
                    if (mVideoView != null) {
                        mVideoView.stopPlayback();
                    }

                }
            }
        });


    }

    private void setVideoScale(int flag) {

        switch (flag) {
            case 1:

                mVideoView.setVideoScale(screenWidth, screenHeight);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

                break;

            case 0:

                int videoWidth = mVideoView.getVideoWidth();
                int videoHeight = mVideoView.getVideoHeight();
                int mWidth = screenWidth;
                int mHeight = screenHeight - 25;

                if (videoWidth > 0 && videoHeight > 0) {
                    if (videoWidth * mHeight > mWidth * videoHeight) {

                        mHeight = mWidth * videoHeight / videoWidth;
                    } else if (videoWidth * mHeight < mWidth * videoHeight) {

                        mWidth = mHeight * videoWidth / videoHeight;
                    } else {

                    }
                }

                mVideoView.setVideoScale(mWidth, mHeight);

                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

                break;
        }
    }


    private void startPlay() {
        if (uri != null && mVideoView != null) {
            mVideoView.stopPlayback();
            mVideoView.setVideoURI(uri);

            mIvPlay.setImageDrawable(getResources().getDrawable(R.drawable.selector_controller_pause));
        }

    }

    private void initWindow() {
        // 设置没有标题
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 保持背光常亮的设置--必须要有的
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    private void loadPlayData() {
        uri = Uri.parse("http://kj.coe9.com/mp4/640w/9082/9082-104-1-2015-52361797_640w.mp4");
    }

    private void updateTime(int curTime, int totalTime) {
        int minutes = curTime / (60 * 1000);
        int seconds = (curTime / 1000) % 60;
        int totalMinutes = totalTime / (60 * 1000);
        int totalSeconds = (totalTime / 1000) % 60;
        String currentTime = String.format("%02d:%02d", minutes, seconds);
        String afterTotalTime = String.format("%02d:%02d", totalMinutes, totalSeconds);
        mTvCurrentTime.setText(currentTime);
        mTvSurplusTime.setText(afterTotalTime);
    }

}
