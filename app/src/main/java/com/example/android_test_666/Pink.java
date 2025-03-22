package com.example.android_test_666;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Size;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.media.Image;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import com.google.common.util.concurrent.ListenableFuture;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;


public class Pink extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CAMERA = 83854;

    //private int angle = 0;
    //private int angle2 = 0;

    private ImageView preview;
    private ImageView preview2;

    ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    YUVtoRGB translator = new YUVtoRGB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        preview = findViewById(R.id.preview);
        preview2 = findViewById(R.id.preview2);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CAMERA);
        } else {
            initializeCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CAMERA && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initializeCamera();
        }
    }
    private void initializeCamera(){
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                    //Preview preview = new Preview.Builder().build();

                    //ImageCapture imageCapture = new ImageCapture.Builder().build();

                    ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                            .setTargetResolution(new Size(1024, 1024)) //comeback later
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .build();

                    CameraSelector cameraSelector = new CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build();

                    imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(Pink.this),
                            new ImageAnalysis.Analyzer() {
                                //@androidx.camera.core.ExperimentalGetImage  //???
                                //@OptIn(markerClass = ExperimentalGetImage.class) //???
                                @Override
                                public void analyze(@NonNull ImageProxy image) {
                                    Image img = image.getImage();
                                    Bitmap bitmap = translator.translateYUV(img, Pink.this);

                                    int size = bitmap.getWidth() * bitmap.getHeight();//test
                                    int[] pixels = new int[size];//test
                                    bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0,//test
                                            bitmap.getWidth(), bitmap.getHeight());//test
//test
                                    for (int i = 0; i < size; i++) {//test
                                        int color = pixels[i];//test
                                        int r = color >> 16 & 0xff;//test
                                        int g = color >> 8 & 0xff;//test
                                        int b = color & 0xff;//test
                                        //int gray = (r + g + b) / 3;//test
                                        if (r < 230) {
                                            pixels[i] = 0xff000000 | r+r/10 << 16 | g/20 << 8 | b/20;//test
                                        } else {
                                            pixels[i] = 0xff000000 | r << 16 | g/20 << 8 | b/20;//test
                                        }
                                    }//test
                                    bitmap.setPixels(pixels, 0, bitmap.getWidth(), 0, 0,//test
                                            bitmap.getWidth(), bitmap.getHeight());//test

                                    preview.setRotation(image.getImageInfo().getRotationDegrees()); //image.getImageInfo().getRotationDegrees()
                                    //angle2++; //test
                                    //preview.setRotationX(180); // test
                                    preview.setImageBitmap(bitmap);
                                    image.close();
                                }
                            });
                    cameraProvider.bindToLifecycle(Pink.this, cameraSelector, imageAnalysis);

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                    //Preview preview = new Preview.Builder().build();

                    //ImageCapture imageCapture = new ImageCapture.Builder().build();

                    ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                            .setTargetResolution(new Size(1024, 1024)) //comeback later
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .build();

                    CameraSelector cameraSelector = new CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build();

                    imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(Pink.this),
                            new ImageAnalysis.Analyzer() {
                                //@androidx.camera.core.ExperimentalGetImage  //???
                                //@OptIn(markerClass = ExperimentalGetImage.class) //???
                                @Override
                                public void analyze(@NonNull ImageProxy image) {
                                    Image img = image.getImage();
                                    Bitmap bitmap = translator.translateYUV(img, Pink.this);

                                    int size = bitmap.getWidth() * bitmap.getHeight();//test
                                    int[] pixels = new int[size];//test
                                    bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0,//test
                                            bitmap.getWidth(), bitmap.getHeight());//test
//test
                                    for (int i = 0; i < size; i++) {//test
                                        int color = pixels[i];//test
                                        int r = color >> 16 & 0xff;//test
                                        int g = color >> 8 & 0xff;//test
                                        int b = color & 0xff;//test
                                        //int gray = (r + g + b) / 3;//test
                                        if (r < 230) {
                                            pixels[i] = 0xff000000 | r+r/10 << 16 | g/20 << 8 | b/20;//test
                                        } else {
                                            pixels[i] = 0xff000000 | r << 16 | g/20 << 8 | b/20;//test
                                        }
                                        //test
                                    }
                                    bitmap.setPixels(pixels, 0, bitmap.getWidth(), 0, 0,//test
                                            bitmap.getWidth(), bitmap.getHeight());//test

                                    preview2.setRotation(image.getImageInfo().getRotationDegrees()); //image.getImageInfo().getRotationDegrees()
                                    //angle++; //test
                                    //preview2.setRotationX(180); // test
                                    preview2.setImageBitmap(bitmap);
                                    image.close();
                                }
                            });
                    cameraProvider.bindToLifecycle(Pink.this, cameraSelector, imageAnalysis);

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, ContextCompat.getMainExecutor(this));
    }
}