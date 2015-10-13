package co.hackingedu.ro.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
import java.util.Date;

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
    LinearLayout Camera_Watermark;
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

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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
                Date now = new Date();
                android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

                    // image naming and path  to include sd card  appending name you choose for file
                    String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

                    // create bitmap screen capture

                    //joseph I need some help, the bitmap is always black :(
                    Bitmap bitmap;
                    surfaceView.setDrawingCacheEnabled(true);
                    bitmap = Bitmap.createBitmap(surfaceView.getDrawingCache());
                    surfaceView.setDrawingCacheEnabled(false);

                    File imageFile = new File(mPath);

                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(imageFile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                int quality = 100;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);


                // start Tweet Composer
                Intent intent = null;
                intent = new TweetComposer.Builder(context)
                        .text("Inventing the future at #HackingEDU!!!")
//                    .url(new URL("http://hackingedu.co"))
                        .image(Uri.fromFile(imageFile))
                        .createIntent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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




    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub
        if(previewing){
            camera.stopPreview();
            previewing = false;
        }

        if (camera != null){
            try {
                camera.setPreviewDisplay(surfaceHolder);
                camera.setDisplayOrientation(90);
                camera.startPreview();
                previewing = true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}