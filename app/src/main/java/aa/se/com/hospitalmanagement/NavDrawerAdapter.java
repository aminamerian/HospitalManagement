package aa.se.com.hospitalmanagement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Amin on 06/10/2016.
 */
public class NavDrawerAdapter extends ArrayAdapter<String> {
    Context context;
    String[] titles;
    int[] images = {R.drawable.ic_dep, R.drawable.ic_event_black, R.drawable.ic_order_status, R.drawable.ic_perm_identity, R.drawable.ic_exit};
    Typeface font;
    private PrefManager prefManager;

    public NavDrawerAdapter(Context context, String[] titles, Typeface font) {
        super(context, R.layout.item_navdrawer, R.id.textView_item_nav, titles);

        this.context = context;
        this.titles = titles;
        this.font = font;
        prefManager = new PrefManager(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if (position == 0) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.nav_header, parent, false);
            TextView name = (TextView) row.findViewById(R.id.textView_userName);
            TextView number = (TextView) row.findViewById(R.id.textView_userNumber);
            ImageView image = (ImageView) row.findViewById(R.id.imageView_account);

            name.setTypeface(font);
            number.setTypeface(font);

            number.setText(prefManager.getUserPhoneNumber());
            name.setText(prefManager.getUserName() + " " + prefManager.getUserLastName());
//            Animation anim_minusBtn = AnimationUtils.loadAnimation(context, R.anim.fade_anim);
//            image.startAnimation(anim_minusBtn);

        } else {
            row = convertView;
            NavDrawerViewHolder holder = null;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.item_navdrawer, parent, false);
                holder = new NavDrawerViewHolder(row);
                row.setTag(holder);
            } else {
                holder = (NavDrawerViewHolder) row.getTag();
            }
            if (position == titles.length) {// last item
                View divider = row.findViewById(R.id.view_divider);
                divider.setVisibility(View.VISIBLE);
            }
            holder.text.setText(titles[position - 1]);
            holder.text.setTypeface(font);
            holder.image.setImageResource(images[position - 1]);

        }
        return row;
    }

    @Override
    public int getCount() {
        return titles.length + 1;
    }
}

class NavDrawerViewHolder {
    ImageView image;
    TextView text;

    NavDrawerViewHolder(View view) {
        image = (ImageView) view.findViewById(R.id.imageView_item_nav);
        text = (TextView) view.findViewById(R.id.textView_item_nav);
    }
}
