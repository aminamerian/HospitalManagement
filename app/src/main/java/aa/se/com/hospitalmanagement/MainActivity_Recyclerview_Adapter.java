package aa.se.com.hospitalmanagement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Amin on 20/06/2017.
 */
class MainActivity_Recyclerview_Adapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private Typeface font;
    private String[] depsName;
    private int[] images = {R.drawable.img_brain, R.drawable.img_heart, R.drawable.img_stomache, R.drawable.img_ent2
            , R.drawable.img_infant, R.drawable.img_kidney, R.drawable.img_blood, R.drawable.img_eye
            , R.drawable.img_radiology, R.drawable.img_bone};

    public MainActivity_Recyclerview_Adapter(Context context, Typeface font) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.font = font;
        depsName = context.getResources().getStringArray(R.array.depsName);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Animation slideUp = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.layout.getLayoutParams();
                params.topMargin = (int)(context.getResources().getDimension(R.dimen.anim_margin) * interpolatedTime);
                holder.layout.setLayoutParams(params);
            }
        };

        holder.textView.setText(depsName[position]);
        holder.image.setImageResource(images[position]);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideUp.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation){}
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(context, DoctorsActivity.class);
                        intent.putExtra("SECTION", position);
                        context.startActivity(intent);
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation){}
                });
                slideUp.setDuration(500);
                holder.layout.startAnimation(slideUp);
            }
        });
        holder.textView.setTypeface(font);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}


class MyViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView textView;
    public LinearLayout layout;

    public MyViewHolder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.card_image);
        textView = (TextView) itemView.findViewById(R.id.text_view);
        layout= (LinearLayout) itemView.findViewById(R.id.layout_container);
    }
}
