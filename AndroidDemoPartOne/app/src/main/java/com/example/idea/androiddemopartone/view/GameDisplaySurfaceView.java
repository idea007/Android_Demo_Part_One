//package com.example.idea.androiddemopartone.view;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.ImageFormat;
//import android.graphics.Rect;
//import android.graphics.SurfaceTexture;
//import android.hardware.Camera;
//import android.hardware.Camera.Size;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * Created by idea on 16/4/26.
// */
//public class GameDisplaySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Camera.PreviewCallback {
//
//    public SurfaceHolder gHolder;
//    public SurfaceTexture gSurfaceTexture;
//
//    public int previewWidth, previewHeight;
//    public int screenWidth, screenHeight;
//
//    private Rect gRect;
//
//    public Camera gCamera;
//
//    private Camera.Parameters parameters;
//
//    public Bitmap gBitmap;
//
//    private int bufferSize;
//
//    public byte gBuffer[];
//    public int textureBuffer[];
//
//    public ProcessThread gProcessThread;
//
//
//
//    public GameDisplaySurfaceView(Context context, int screenWidth, int screenHeight) {
//        super(context);
//        gHolder = this.getHolder();
//        gHolder.addCallback(this);
//        gHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//        gSurfaceTexture = new SurfaceTexture(10);
//        this.screenHeight = screenHeight;
//        this.screenWidth = screenWidth;
//        gRect = new Rect(0, 0, screenWidth, screenHeight);
//
//
//    }
//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        if (gCamera == null) {
//            gCamera = Camera.open();
//        }
//        try {
//            gCamera.setPreviewTexture(gSurfaceTexture);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//        parameters = gCamera.getParameters();
//        List<Size> preSize = parameters.getSupportedPreviewSizes();
//        previewWidth = preSize.get(0).width;
//        previewHeight = preSize.get(0).height;
//        for (int i = 1; i < preSize.size(); i++) {
//            double similarity = Math
//                    .abs(((double) preSize.get(i).height / screenHeight)
//                            - ((double) preSize.get(i).width / screenWidth));
//            if (similarity < Math.abs(((double) previewHeight / screenHeight)
//                    - ((double) previewWidth / screenWidth))) {
//                previewWidth = preSize.get(i).width;
//                previewHeight = preSize.get(i).height;
//            }
//        }
//        gBitmap = Bitmap.createBitmap(previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
//        parameters.setPictureSize(previewWidth, previewHeight);
//        gCamera.setParameters(parameters);
//
//        bufferSize = previewWidth * previewHeight;
//        textureBuffer = new int[bufferSize];
//        bufferSize = bufferSize * ImageFormat.getBitsPerPixel(parameters.getPreviewFormat());
//
//        gBuffer = new byte[bufferSize];
//        gCamera.addCallbackBuffer(gBuffer);
//        gCamera.setPreviewCallbackWithBuffer(this);
//        gCamera.startPreview();
//
//
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//        gCamera.stopPreview();
//        gCamera.release();
//    }
//
//    @Override
//    public void onPreviewFrame(byte[] data, Camera camera) {
//        camera.addCallbackBuffer(gBuffer);
//        for (int i = 0; i < textureBuffer.length; i++)
//            textureBuffer[i] = 0xff000000 | data[i];
//        gBitmap.setPixels(textureBuffer, 0, previewWidth, 0, 0, previewWidth, previewHeight);
//        synchronized (gHolder) {
//            Canvas canvas = this.getHolder().lockCanvas();
//            canvas.drawBitmap(gBitmap, null, gRect, null);
//            //canvas.drawBitmap(textureBuffer, 0, screenWidth, 0, 0, screenWidth, screenHeight, false, null);
//            this.getHolder().unlockCanvasAndPost(canvas);
//
//        }
//    }
//
//    // timer
//       private Timer sampleTimer;
//       private TimerTask sampleTask;
//
//    public void sampleStart(){
//        sampleTimer=new Timer(false);
//        sampleTask=new TimerTask() {
//            @Override
//            public void run() {
//                gProcessThread.timer=true;
//
//            }
//        };
//        sampleTimer.schedule(sampleTask,0,80);
//    }
//}
