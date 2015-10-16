package co.hackingedu.ro.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.hardware.*;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import co.hackingedu.ro.R;

public class CameraActivity extends Activity implements SurfaceHolder.Callback{
    /**
     * Logging tag
     */
    private static String TAG = "CameraActivity";

    /**
     * static context to use later!
     */
    private static Context context;

    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean previewing = false;
    LayoutInflater controlInflater = null;
    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();

        setContentView(R.layout.camera_watermark);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // login to Twitter
        TwitterAuthClient twitterAuthClient = new TwitterAuthClient();
        twitterAuthClient.authorize(this, new Callback<TwitterSession>() {
            @Override
            public void success(final Result<TwitterSession> result) {
                final TwitterSession sessionData = result.data;
                // Do something with the returned TwitterSession (contains the user token and secret)
            }

            @Override
            public void failure(final TwitterException e) {
                // Do something on fail
                // try logging in again!!
                // or not?
            }
        });

        // set up surfaceView
        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = (SurfaceView)findViewById(R.id.camerapreview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        controlInflater = LayoutInflater.from(getBaseContext());
        View viewControl = controlInflater.inflate(R.layout.control, null);
        LayoutParams layoutParamsControl
                = new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT);
        this.addContentView(viewControl, layoutParamsControl);

        // TODO: takepicture lol
        ImageView capture = (ImageView) findViewById(R.id.takepicture);
        capture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                camera.takePicture(myShutterCallback,
                        myPictureCallback_RAW, myPictureCallback_JPG);
            }
        });
    }

    ShutterCallback myShutterCallback = new ShutterCallback(){
        @Override
        public void onShutter() {
            // TODO Auto-generated method stub

        }};

    PictureCallback myPictureCallback_RAW = new PictureCallback(){
        @Override
        public void onPictureTaken(byte[] arg0, Camera arg1) {
            // TODO Auto-generated method stub
        }};

    PictureCallback myPictureCallback_JPG = new PictureCallback(){

        @Override
        public void onPictureTaken(byte[] arg0, Camera arg1) {

            // TODO Auto-generated method stub
            Bitmap bitmapPicture
                    = BitmapFactory.decodeByteArray(arg0, 0, arg0.length);
            Log.i(TAG, "byte count: " + bitmapPicture.getByteCount());

            // DCIM public directory
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath();
            File outputDir = new File(path);
            outputDir.mkdirs();

            // create image in directory
            File newFile = new File(path + "/" + "HackingEDU.png");
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(newFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            View rootView = findViewById(R.id.camerapreview).getRootView();
            rootView.setDrawingCacheEnabled(true);
            Bitmap bitmap2 = Bitmap.createBitmap(rootView.getDrawingCache());
            rootView.setDrawingCacheEnabled(false);
            // save image
            overlay(bitmapPicture,bitmap2).compress(Bitmap.CompressFormat.PNG, 100, out);



            // start Tweet Composer
            Intent intent = null;
            intent = new TweetComposer.Builder(context)
                    .text("Inventing the future at #HackingEDU!!!")
//                    .url(new URL("http://hackingedu.co"))
                    .image(Uri.fromFile(newFile))
                    .createIntent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }};

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        if (previewing)
        {
            camera.stopPreview();
        }

        Camera.Parameters parameters = camera.getParameters();
        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

        if(display.getRotation() == Surface.ROTATION_0)
        {
            parameters.setPreviewSize(height, width);
            camera.setDisplayOrientation(90);
        }

        if(display.getRotation() == Surface.ROTATION_90)
        {
            parameters.setPreviewSize(width, height);
        }

        if(display.getRotation() == Surface.ROTATION_180)
        {
            parameters.setPreviewSize(height, width);
        }

        if(display.getRotation() == Surface.ROTATION_270)
        {
            parameters.setPreviewSize(width, height);
            camera.setDisplayOrientation(180);
        }

        camera.setParameters(parameters);
        previewCamera();
    }

    public void previewCamera()
    {
        try
        {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
            previewing = true;
        }
        catch(Exception e)
        {
            Log.d("", "Cannot start preview", e);
        }
    }

    private Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, new Matrix(), null);
        return bmOverlay;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        camera = Camera.open();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        camera.stopPreview();
        camera.release();
        camera = null;
        previewing = false;
    }


}