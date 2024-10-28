package birthdaygreetings.birthdayframe.greetingswishes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.HashMap;
import java.util.Map;

import birthdaygreetings.birthdayframe.greetingswishes.R;
import birthdaygreetings.birthdayframe.greetingswishes.adapter.GridAdapter;
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils;

public class NamePoemMainActivity extends AppCompatActivity {

    Integer[] bdaytempimg = {
            Integer.valueOf(R.drawable.template7), Integer.valueOf(R.drawable.template8),
            Integer.valueOf(R.drawable.template9), Integer.valueOf(R.drawable.template10),
            Integer.valueOf(R.drawable.template11), Integer.valueOf(R.drawable.template12),
            Integer.valueOf(R.drawable.template13), Integer.valueOf(R.drawable.template14)};

    Integer[] bdaythumb = {
            Integer.valueOf(R.drawable.thumb7), Integer.valueOf(R.drawable.thumb8),
            Integer.valueOf(R.drawable.thumb9), Integer.valueOf(R.drawable.thumb10),
            Integer.valueOf(R.drawable.thumb11), Integer.valueOf(R.drawable.thumb12),
            Integer.valueOf(R.drawable.thumb13), Integer.valueOf(R.drawable.thumb14)};

    public static int height, width;
    GridAdapter gridAdapter;
    GridView bggrid;
    TextView poemGenButton;
    public static int pos;
    EditText name;
    Boolean bgselected = Boolean.valueOf(false);
    Intent intent;

    Map<Integer, Integer> map = new HashMap();
    Map<Integer, String> mapimgname = new HashMap();
    Map<Integer, String> maptextalign = new HashMap();

    LinearLayout adsLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_peom_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        adsLay = findViewById(R.id.native_ad);
        AdUtils.INSTANCE.showAdaptiveBanner1(this, adsLay);
        this.map = new HashMap();

        this.map.put(Integer.valueOf(R.drawable.template7), Integer.valueOf(1));
        this.map.put(Integer.valueOf(R.drawable.template8), Integer.valueOf(0));
        this.map.put(Integer.valueOf(R.drawable.template9), Integer.valueOf(0));
        this.map.put(Integer.valueOf(R.drawable.template10), Integer.valueOf(1));
        this.map.put(Integer.valueOf(R.drawable.template11), Integer.valueOf(0));
        this.map.put(Integer.valueOf(R.drawable.template12), Integer.valueOf(0));
        this.map.put(Integer.valueOf(R.drawable.template13), Integer.valueOf(1));
        this.map.put(Integer.valueOf(R.drawable.template14), Integer.valueOf(0));

        String str = "left";
//        this.maptextalign.put(Integer.valueOf(R.drawable.template1), str);
//        this.maptextalign.put(Integer.valueOf(R.drawable.template2), str);
//        this.maptextalign.put(Integer.valueOf(R.drawable.template3), "right");
//        this.maptextalign.put(Integer.valueOf(R.drawable.template4), str);
//        this.maptextalign.put(Integer.valueOf(R.drawable.template5), str);
//        this.maptextalign.put(Integer.valueOf(R.drawable.template6), str);
        this.maptextalign.put(Integer.valueOf(R.drawable.template7), str);
        this.maptextalign.put(Integer.valueOf(R.drawable.template8), str);
        this.maptextalign.put(Integer.valueOf(R.drawable.template9), str);
        this.maptextalign.put(Integer.valueOf(R.drawable.template10), str);
        this.maptextalign.put(Integer.valueOf(R.drawable.template11), str);
        this.maptextalign.put(Integer.valueOf(R.drawable.template12), str);
        this.maptextalign.put(Integer.valueOf(R.drawable.template13), str);
        this.maptextalign.put(Integer.valueOf(R.drawable.template14), str);


        this.mapimgname.put(Integer.valueOf(R.drawable.template7), "template7");
        this.mapimgname.put(Integer.valueOf(R.drawable.template8), "template8");
        this.mapimgname.put(Integer.valueOf(R.drawable.template9), "template9");
        this.mapimgname.put(Integer.valueOf(R.drawable.template10), "template10");
        this.mapimgname.put(Integer.valueOf(R.drawable.template11), "template11");
        this.mapimgname.put(Integer.valueOf(R.drawable.template12), "template12");
        this.mapimgname.put(Integer.valueOf(R.drawable.template13), "template13");
        this.mapimgname.put(Integer.valueOf(R.drawable.template14), "template14");


        bggrid = (GridView) findViewById(R.id.bggrid);
        poemGenButton = findViewById(R.id.resultbtn);
        name = findViewById(R.id.editname);
        gridAdapter = new GridAdapter(getApplicationContext(), this.bdaythumb);
        bggrid.setAdapter(this.gridAdapter);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        AppUtils.INSTANCE.setUpToolbar(this, toolbar, "Birthday Name Poem", true);
        intent = new Intent(this, NamePoemResultactivity1.class);

        poemGenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genratePoem();
            }
        });
        this.bggrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridClickHandle(position);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        AdsHandler.INSTANCE.launchReviewPopup(NamePoemMainActivity.this, new AdsHandler.ReviewCallBack() {
//            @Override
//            public void onComplete(boolean b) {
//
//            }
//        });
    }

    private void gridClickHandle(int i) {
        bgselected = Boolean.valueOf(true);

        pos = i;
        this.bgselected = Boolean.valueOf(true);
        intent.putExtra("bgimg", this.bdaytempimg[i]);
        for (Map.Entry entry : this.map.entrySet()) {
            if (((Integer) entry.getKey()).equals(this.bdaytempimg[i])) {
                String str = "color";
                if (((Integer) entry.getValue()).equals(Integer.valueOf(0))) {
                    intent.putExtra(str, "white");
                } else {
                    intent.putExtra(str, "black");
                }
            }
        }
        for (Map.Entry entry2 : this.maptextalign.entrySet()) {
            if (((Integer) entry2.getKey()).equals(this.bdaytempimg[i])) {
                String str2 = "left";
                String str3 = "align";
                if (((String) entry2.getValue()).equals(str2)) {
                    this.intent.putExtra(str3, str2);
                } else {
                    this.intent.putExtra(str3, "right");
                }
            }
        }
        for (Map.Entry entry3 : this.mapimgname.entrySet()) {
            if (((Integer) entry3.getKey()).equals(this.bdaytempimg[i])) {
                this.intent.putExtra("imgname", (String) entry3.getValue());
            }
        }
    }

    private void genratePoem() {
        String str = "";
        if (name.getText().toString().matches(str)) {
            Toast.makeText(getApplicationContext(), "Please enter your name.", Toast.LENGTH_LONG).show();
            return;
        }
        boolean booleanValue = bgselected.booleanValue();

        String str2 = " ";
        String str3 = "name";
        if (!booleanValue) {
            intent.putExtra("bgimg", bdaytempimg[1]);
            intent.putExtra(str3, name.getText().toString().replaceAll(str2, str));
            intent.putExtra("color", "black");
            intent.putExtra("align", "left");
            intent.putExtra("imgname", "template1");
            startActivity(intent);
            return;
        }
        AdUtils.INSTANCE.showFullAd(this, new AdUtils.AdListener() {
            @Override
            public void onComplete() {
                intent.putExtra(str3, name.getText().toString().replaceAll(str2, str));
                startActivity(intent);
            }
        });

    }
}
