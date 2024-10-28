package birthdaygreetings.birthdayframe.greetingswishes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import birthdaygreetings.birthdayframe.greetingswishes.R;
import birthdaygreetings.birthdayframe.greetingswishes.activity.NamePoemMainActivity;

public class GridAdapter extends BaseAdapter {
    ImageView categoryimg;
    Context context;
    Integer[] img;

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public GridAdapter(Context context2, Integer[] numArr) {
        this.context = context2;
        this.img = numArr;
    }

    public int getCount() {
        return this.img.length;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.griditem, viewGroup, false);
        }
        this.categoryimg = (ImageView) view.findViewById(R.id.categoryimg);
        this.categoryimg.setImageResource(this.img[i].intValue());
        if ((this.context.getResources().getConfiguration().screenLayout & 15) == 4) {
            LayoutParams layoutParams = this.categoryimg.getLayoutParams();
            double d = (double) NamePoemMainActivity.width;
            Double.isNaN(d);
            layoutParams.height = (int) (d * 0.45d);
            LayoutParams layoutParams2 = this.categoryimg.getLayoutParams();
            double d2 = (double) NamePoemMainActivity.width;
            Double.isNaN(d2);
            layoutParams2.width = (int) (d2 * 0.45d);
        } else if ((this.context.getResources().getConfiguration().screenLayout & 15) == 4) {
            LayoutParams layoutParams3 = this.categoryimg.getLayoutParams();
            double d3 = (double) NamePoemMainActivity.width;
            Double.isNaN(d3);
            layoutParams3.height = (int) (d3 * 0.45d);
            LayoutParams layoutParams4 = this.categoryimg.getLayoutParams();
            double d4 = (double) NamePoemMainActivity.width;
            Double.isNaN(d4);
            layoutParams4.width = (int) (d4 * 0.45d);
        } else {
            LayoutParams layoutParams5 = this.categoryimg.getLayoutParams();
            double d5 = (double) NamePoemMainActivity.width;
            Double.isNaN(d5);
            layoutParams5.height = (int) (d5 * 0.45d);
            LayoutParams layoutParams6 = this.categoryimg.getLayoutParams();
            double d6 = (double) NamePoemMainActivity.width;
            Double.isNaN(d6);
            layoutParams6.width = (int) (d6 * 0.45d);
        }
        return view;
    }
}
