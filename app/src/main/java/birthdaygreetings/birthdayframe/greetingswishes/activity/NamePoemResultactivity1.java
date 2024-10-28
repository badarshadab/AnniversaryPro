package birthdaygreetings.birthdayframe.greetingswishes.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;


import birthdaygreetings.birthdayframe.greetingswishes.R;
import birthdaygreetings.birthdayframe.greetingswishes.database.DataBaseHelperNamePoem;
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils;
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

@SuppressLint("WrongConstant")
public class NamePoemResultactivity1 extends AppCompatActivity {
    //    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 111;
    public static int height;
    public static int width;
    String align;
    Bundle b;
    int backgroundimgName;
    ImageView bgimg;
    Bitmap bitmap;
    Typeface bold;
    String color;
    DataBaseHelperNamePoem dataBaseHelper;
    Dialog dialogR;
    DisplayMetrics displayMetrics;
    Editor editor;
    String f;
    String[] fontarray = {"fonts/ACaslonPro-Regular.otf", "fonts/HappyMonkey-Regular.ttf", "fonts/Merienda-Regular.ttf", "fonts/Satisfy-Regular.ttf", "fonts/Gabriola.ttf", "fonts/Artifika-Regular.ttf", "fonts/beau___r.ttf", "fonts/ChaparralPro-Regular.otf", "fonts/GlassAntiqua-Regular.ttf", "fonts/Sail-Regular.ttf"};
    int imgheight;
    String imgname;
    int imgwidth;
    String line;
    int max = 10;
    Typeface medium;
    String message;
    int min = 0;
    String name;
    int number;
    Random r;
    int random;
    Typeface regular;
    TextView save;
    TextView share;
    SharedPreferences sharedPreferences;
    int spSize;
    Typeface tf;
    int x;
    int y;
    MyCanvas myCanvas;
    View view1;

    public class MyCanvas extends View {
        public MyCanvas(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint(1);
            if (NamePoemResultactivity1.this.color.equals("white")) {
                paint.setColor(-1);
            } else {
                paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            }
            if (NamePoemResultactivity1.this.align.equals("left")) {
                NamePoemResultactivity1.this.x = 12;
            } else if ((getResources().getConfiguration().screenLayout & 15) == 4) {
                NamePoemResultactivity1 NamePoemResultactivity1 = NamePoemResultactivity1.this;
                double d = (double) NamePoemResultactivity1.height;
                Double.isNaN(d);
                NamePoemResultactivity1.x = (int) (d * 0.3d);
            } else if ((getResources().getConfiguration().screenLayout & 15) == 3) {
                NamePoemResultactivity1 NamePoemResultactivity12 = NamePoemResultactivity1.this;
                double d2 = (double) NamePoemResultactivity1.height;
                Double.isNaN(d2);
                NamePoemResultactivity12.x = (int) (d2 * 0.41d);
            } else {
                NamePoemResultactivity1 NamePoemResultactivity13 = NamePoemResultactivity1.this;
                double d3 = (double) NamePoemResultactivity1.width;
                Double.isNaN(d3);
                NamePoemResultactivity13.x = (int) (d3 * 0.39d);
            }
            paint.setTextSize((((float) NamePoemResultactivity1.this.spSize) * getResources().getDisplayMetrics().scaledDensity) + 0.5f);
            paint.setTypeface(NamePoemResultactivity1.this.tf);
            canvas.drawText(NamePoemResultactivity1.this.name.toUpperCase(), (float) NamePoemResultactivity1.this.x, (float) NamePoemResultactivity1.this.y, paint);
            NamePoemResultactivity1.this.y += 10;
            String str = ", ";
            String str2 = "";
            String str3 = " ";
            String str4 = "   ";
            int i = 0;
            if (NamePoemResultactivity1.this.name.length() <= 4) {
                while (i < NamePoemResultactivity1.this.name.length()) {
                    paint.setTypeface(NamePoemResultactivity1.this.tf);
                    NamePoemResultactivity1 NamePoemResultactivity14 = NamePoemResultactivity1.this;
                    int i2 = NamePoemResultactivity14.y;
                    double d4 = (double) NamePoemResultactivity1.this.imgheight;
                    Double.isNaN(d4);
                    NamePoemResultactivity14.y = i2 + (((int) (d4 * 0.4d)) / NamePoemResultactivity1.this.name.length()) + 2;
                    NamePoemResultactivity1 NamePoemResultactivity15 = NamePoemResultactivity1.this;
                    NamePoemResultactivity15.message = NamePoemResultactivity15.dataBaseHelper.getMessage(NamePoemResultactivity1.this.name.toLowerCase().charAt(i));
                    StringBuilder sb = new StringBuilder();
                    sb.append(NamePoemResultactivity1.this.name.toUpperCase().charAt(i));
                    sb.append(str4);
                    sb.append(NamePoemResultactivity1.this.message);
                    canvas.drawText(sb.toString(), (float) NamePoemResultactivity1.this.x, (float) NamePoemResultactivity1.this.y, paint);
                    if (i == NamePoemResultactivity1.this.name.length() - 1) {
                        paint.setTypeface(NamePoemResultactivity1.this.tf);
                        NamePoemResultactivity1 NamePoemResultactivity16 = NamePoemResultactivity1.this;
                        int i3 = NamePoemResultactivity16.y;
                        double d5 = (double) NamePoemResultactivity1.this.imgheight;
                        Double.isNaN(d5);
                        NamePoemResultactivity16.y = i3 + (((int) (d5 * 0.4d)) / NamePoemResultactivity1.this.name.length()) + 2;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(NamePoemResultactivity1.this.name.replaceAll(str3, str2).toUpperCase());
                        sb2.append(str);
                        sb2.append(NamePoemResultactivity1.this.dataBaseHelper.getLines());
                        canvas.drawText(sb2.toString(), (float) NamePoemResultactivity1.this.x, (float) NamePoemResultactivity1.this.y, paint);
                    }
                    i++;
                }
            } else if (NamePoemResultactivity1.this.name.length() > 7) {
                while (i < NamePoemResultactivity1.this.name.length()) {
                    paint.setTypeface(NamePoemResultactivity1.this.tf);
                    NamePoemResultactivity1 NamePoemResultactivity17 = NamePoemResultactivity1.this;
                    int i4 = NamePoemResultactivity17.y;
                    double d6 = (double) NamePoemResultactivity1.this.imgheight;
                    Double.isNaN(d6);
                    NamePoemResultactivity17.y = i4 + (((int) (d6 * 0.57d)) / NamePoemResultactivity1.this.name.length()) + 2;
                    NamePoemResultactivity1 NamePoemResultactivity18 = NamePoemResultactivity1.this;
                    NamePoemResultactivity18.message = NamePoemResultactivity18.dataBaseHelper.getMessage(NamePoemResultactivity1.this.name.toLowerCase().charAt(i));
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(NamePoemResultactivity1.this.name.toUpperCase().charAt(i));
                    sb3.append(str4);
                    sb3.append(NamePoemResultactivity1.this.message);
                    canvas.drawText(sb3.toString(), (float) NamePoemResultactivity1.this.x, (float) NamePoemResultactivity1.this.y, paint);
                    if (i == NamePoemResultactivity1.this.name.length() - 1) {
                        paint.setTypeface(NamePoemResultactivity1.this.tf);
                        NamePoemResultactivity1 NamePoemResultactivity19 = NamePoemResultactivity1.this;
                        int i5 = NamePoemResultactivity19.y;
                        double d7 = (double) NamePoemResultactivity1.this.imgheight;
                        Double.isNaN(d7);
                        NamePoemResultactivity19.y = i5 + (((int) (d7 * 0.57d)) / NamePoemResultactivity1.this.name.length()) + 2;
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(NamePoemResultactivity1.this.name.replaceAll(str3, str2).toUpperCase());
                        sb4.append(str);
                        sb4.append(NamePoemResultactivity1.this.dataBaseHelper.getLines());
                        canvas.drawText(sb4.toString(), (float) NamePoemResultactivity1.this.x, (float) NamePoemResultactivity1.this.y, paint);
                    }
                    i++;
                }
            } else {
                while (i < NamePoemResultactivity1.this.name.length()) {
                    paint.setTypeface(NamePoemResultactivity1.this.tf);
                    NamePoemResultactivity1 NamePoemResultactivity110 = NamePoemResultactivity1.this;
                    int i6 = NamePoemResultactivity110.y;
                    double d8 = (double) NamePoemResultactivity1.this.imgheight;
                    Double.isNaN(d8);
                    NamePoemResultactivity110.y = i6 + (((int) (d8 * 0.55d)) / NamePoemResultactivity1.this.name.length());
                    NamePoemResultactivity1 NamePoemResultactivity111 = NamePoemResultactivity1.this;
                    NamePoemResultactivity111.message = NamePoemResultactivity111.dataBaseHelper.getMessage(NamePoemResultactivity1.this.name.toLowerCase().charAt(i));
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(NamePoemResultactivity1.this.name.toUpperCase().charAt(i));
                    sb5.append(str4);
                    sb5.append(NamePoemResultactivity1.this.message);
                    canvas.drawText(sb5.toString(), (float) NamePoemResultactivity1.this.x, (float) NamePoemResultactivity1.this.y, paint);
                    if (i == NamePoemResultactivity1.this.name.length() - 1) {
                        paint.setTypeface(NamePoemResultactivity1.this.tf);
                        NamePoemResultactivity1 NamePoemResultactivity112 = NamePoemResultactivity1.this;
                        int i7 = NamePoemResultactivity112.y;
                        double d9 = (double) NamePoemResultactivity1.this.imgheight;
                        Double.isNaN(d9);
                        NamePoemResultactivity112.y = i7 + (((int) (d9 * 0.55d)) / NamePoemResultactivity1.this.name.length()) + 2;
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append(NamePoemResultactivity1.this.name.replaceAll(str3, str2).toUpperCase());
                        sb6.append(str);
                        sb6.append(NamePoemResultactivity1.this.dataBaseHelper.getLines());
                        canvas.drawText(sb6.toString(), (float) NamePoemResultactivity1.this.x, (float) NamePoemResultactivity1.this.y, paint);
                    }
                    i++;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.activity_name_poem_resultactivity);
        AdUtils.INSTANCE.showBanner(this,findViewById(R.id.nativeAdContainer));
        this.r = new Random();
        this.number = this.r.nextInt(this.max - this.min) + this.min;
        view1 = findViewById(R.id.myscreen);
        this.sharedPreferences = getSharedPreferences("MYPREFERENCE", 0);
        this.editor = this.sharedPreferences.edit();
        this.random = (int) ((Math.random() * 10000.0d) + 1.0d);
        if (bundle != null) {
            this.random = bundle.getInt("random");
        } else {
            this.random = (int) ((Math.random() * 10000.0d) + 1.0d);
        }
        this.bgimg = (ImageView) findViewById(R.id.bgimg);
        this.save = findViewById(R.id.save);
        this.share = findViewById(R.id.share);
        this.b = getIntent().getExtras();
        this.backgroundimgName = this.b.getInt("bgimg");
        this.name = this.b.getString("name");
        this.color = this.b.getString("color");
        this.align = this.b.getString("align");
        this.imgname = this.b.getString("imgname");
        this.bgimg.setImageResource(this.backgroundimgName);
        StringBuilder sb = new StringBuilder();
        sb.append("....");
        sb.append(this.backgroundimgName);
        Log.e("ImageName", sb.toString());
        this.bgimg.setDrawingCacheEnabled(true);
        this.bgimg.buildDrawingCache();
        this.displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(this.displayMetrics);
        height = this.displayMetrics.heightPixels;
        width = this.displayMetrics.widthPixels;
        this.dataBaseHelper = new DataBaseHelperNamePoem(getApplicationContext());
        this.bold = Typeface.createFromAsset(getAssets(), "fonts/Neuton-Bold.ttf");
        this.medium = Typeface.createFromAsset(getAssets(), "fonts/Neuton-Light.ttf");
        this.regular = Typeface.createFromAsset(getAssets(), "fonts/Neuton-Regular.ttf");
        this.tf = Typeface.createFromAsset(getAssets(), this.fontarray[this.number]);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(">>>>>>");
        sb2.append(this.fontarray[this.number]);
        sb2.append("...");
        sb2.append(this.number);
        Log.e("font", sb2.toString());
        int i = height;
        double d = (double) i;
        Double.isNaN(d);
        int i2 = (int) (d * 0.09d);
        double d2 = (double) i;
        Double.isNaN(d2);
        LayoutParams layoutParams = new LayoutParams(i2, (int) (d2 * 0.09d));
        double d3 = (double) width;
        Double.isNaN(d3);
        layoutParams.setMargins((int) (d3 * 0.02d), 0, 0, 0);
        this.save.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = this.share.getLayoutParams();
        double d4 = (double) height;
        Double.isNaN(d4);
        layoutParams2.height = (int) (d4 * 0.09d);
        ViewGroup.LayoutParams layoutParams3 = this.share.getLayoutParams();
        double d5 = (double) height;
        Double.isNaN(d5);
        layoutParams3.width = (int) (d5 * 0.09d);
        try {
            this.dataBaseHelper.createDatabse();
        } catch (IOException unused) {
        }
        HashMap hashMap = new HashMap();
        String str = "Birthday Name Poem Generated";
        hashMap.put(str, this.imgname);
//        FlurryAgent.logEvent(str, (Map<String, String>) hashMap);
        String str2 = this.imgname;
        this.bgimg.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            public boolean onPreDraw() {
                NamePoemResultactivity1.this.bgimg.getViewTreeObserver().removeOnPreDrawListener(this);
                if ((NamePoemResultactivity1.this.getResources().getConfiguration().screenLayout & 15) == 4) {
                    ViewGroup.LayoutParams layoutParams = NamePoemResultactivity1.this.bgimg.getLayoutParams();
                    double d = (double) NamePoemResultactivity1.height;
                    Double.isNaN(d);
                    layoutParams.height = (int) (d * 0.9d);
                    ViewGroup.LayoutParams layoutParams2 = NamePoemResultactivity1.this.bgimg.getLayoutParams();
                    double d2 = (double) NamePoemResultactivity1.width;
                    Double.isNaN(d2);
                    layoutParams2.width = (int) (d2 * 0.9d);
                    if (NamePoemResultactivity1.this.name.length() < 7) {
                        NamePoemResultactivity1.this.spSize = 24;
                    } else {
                        NamePoemResultactivity1.this.spSize = 13;
                    }
                }
                if ((NamePoemResultactivity1.this.getResources().getConfiguration().screenLayout & 15) == 3) {
                    ViewGroup.LayoutParams layoutParams3 = NamePoemResultactivity1.this.bgimg.getLayoutParams();
                    double d3 = (double) NamePoemResultactivity1.height;
                    Double.isNaN(d3);
                    layoutParams3.height = (int) (d3 * 0.9d);
                    ViewGroup.LayoutParams layoutParams4 = NamePoemResultactivity1.this.bgimg.getLayoutParams();
                    double d4 = (double) NamePoemResultactivity1.width;
                    Double.isNaN(d4);
                    layoutParams4.width = (int) (d4 * 0.9d);
                    if (NamePoemResultactivity1.this.name.length() < 7) {
                        NamePoemResultactivity1.this.spSize = 13;
                    } else {
                        NamePoemResultactivity1.this.spSize = 12;
                    }
                } else if (NamePoemResultactivity1.this.name.length() < 7) {
                    NamePoemResultactivity1.this.spSize = 12;
                } else {
                    NamePoemResultactivity1.this.spSize = 11;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("resolution---height");
                double d5 = (double) NamePoemResultactivity1.height;
                Double.isNaN(d5);
                sb.append((int) (d5 * 0.9d));
                String str = "device";
                Log.e(str, sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append("resolution---width");
                double d6 = (double) NamePoemResultactivity1.width;
                Double.isNaN(d6);
                sb2.append((int) (d6 * 0.9d));
                Log.e(str, sb2.toString());
                NamePoemResultactivity1 NamePoemResultactivity1 = NamePoemResultactivity1.this;
                NamePoemResultactivity1.imgwidth = NamePoemResultactivity1.bgimg.getMeasuredWidth();
                NamePoemResultactivity1 NamePoemResultactivity12 = NamePoemResultactivity1.this;
                NamePoemResultactivity12.imgheight = NamePoemResultactivity12.bgimg.getMeasuredHeight();
                StringBuilder sb3 = new StringBuilder();
                String str2 = ">>>>>";
                sb3.append(str2);
                sb3.append(NamePoemResultactivity1.this.imgheight);
                Log.e("imgheight", sb3.toString());
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str2);
                sb4.append(NamePoemResultactivity1.this.imgwidth);
                Log.e("imgwidth", sb4.toString());
                NamePoemResultactivity1 NamePoemResultactivity13 = NamePoemResultactivity1.this;
                double d7 = (double) NamePoemResultactivity13.imgheight;
                Double.isNaN(d7);
                NamePoemResultactivity13.y = (int) (d7 * 0.25d);
                NamePoemResultactivity1 NamePoemResultactivity14 = NamePoemResultactivity1.this;
                myCanvas = new MyCanvas(NamePoemResultactivity14.getApplicationContext());
                NamePoemResultactivity1 NamePoemResultactivity15 = NamePoemResultactivity1.this;
                NamePoemResultactivity15.bitmap = Bitmap.createBitmap(
                        Bitmap.createBitmap(NamePoemResultactivity15.bgimg.getDrawingCache(), 0, 0,
                                imgwidth, imgheight));
                StringBuilder sb5 = new StringBuilder();
                sb5.append(NamePoemResultactivity1.this.bitmap.getWidth());
                sb5.append(" ");
                sb5.append(NamePoemResultactivity1.this.bitmap.getHeight());
                Log.e("****Bitmap", sb5.toString());
                myCanvas.draw(new Canvas(NamePoemResultactivity1.this.bitmap));
                NamePoemResultactivity1.this.bgimg.setImageBitmap(NamePoemResultactivity1.this.bitmap);
                StringBuilder sb6 = new StringBuilder();
                sb6.append("");
                sb6.append(NamePoemResultactivity1.this.bgimg.getLayoutParams().height);
                Log.e("....", sb6.toString());
                return true;
            }
        });
        this.save.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                View view1 = findViewById(R.id.myscreen);
                Bitmap bmp = AppUtils.INSTANCE.captureScreen1(view1);
                AppUtils.INSTANCE.saveBitmap(bmp , "" , NamePoemResultactivity1.this);
            }
        });
        this.share.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = findViewById(R.id.myscreen);
                Bitmap bmp = AppUtils.INSTANCE.captureScreen1(view1);
                AppUtils.INSTANCE.shareBitmap(NamePoemResultactivity1.this, bmp);
            }
        });
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 111 && iArr.length > 0) {
            int i2 = iArr[0];
        }
    }

    public void AddRateClicks() {
        String str = "rateagain";
        if (this.sharedPreferences.getInt(str, 8) < 8) {
            int i = this.sharedPreferences.getInt(str, 8) + 1;
            this.editor.putInt(str, i);
            this.editor.commit();
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(i);
            Log.e("Clicks: ", sb.toString());
        }
    }

    public void onBackPressed() {
        String str = "rateagain";
        if (this.sharedPreferences.getInt(str, 8) == 8) {
            this.editor.putInt(str, 0);
            this.editor.commit();
//            RATE_DIALOG();
            return;
        }
        super.onBackPressed();
    }

}
