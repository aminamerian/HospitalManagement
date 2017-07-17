package aa.se.com.hospitalmanagement;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Amin on 22/06/2017.
 */

public class DocActivity_Recyclerview_Adapter extends RecyclerView.Adapter<DocViewHolder> {
    private String[] doctors;
    private String[] doctorsDes;
    private int[] doctorsImg;
    private Context context;
    private LayoutInflater inflater;
    private Typeface font;
    private int section;
    private String[] doctorsDesComplete;


    public DocActivity_Recyclerview_Adapter(Context context, Typeface font, String[] doctors, String[] doctorsDes, int[] doctorsImg, int section) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.font = font;
        this.doctors = doctors;
        this.doctorsDes = doctorsDes;
        this.doctorsImg = doctorsImg;
        this.section = section;
        doctorsDesComplete = getDoctorsDes(section);
    }

    @Override
    public DocViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DocViewHolder(inflater.inflate(R.layout.item_cardview_doc, parent, false));
    }

    @Override
    public void onBindViewHolder(final DocViewHolder holder, final int position) {
        final int rate = new Random().nextInt(4) + 1;
        holder.ratingBar.setRating(rate);

        holder.docName.setText(doctors[position]);
        holder.docDes.setText(doctorsDes[position]);
        holder.docImage.setImageResource(doctorsImg[position]);

        holder.docName.setTypeface(font);
        holder.docDes.setTypeface(font);

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleDocActivity.class);
                intent.putExtra("NAME", doctors[position]);
                intent.putExtra("IMAGE", doctorsImg[position]);
                intent.putExtra("COMDES", doctorsDesComplete[position]);  //CompleteDescription
                intent.putExtra("RATE", rate);

                Pair<View, String> p1 = Pair.create((View) holder.docImage, "sharedImage");
                Pair<View, String> p2 = Pair.create((View) holder.docName, "sharedText");
                Pair<View, String> p3 = Pair.create((View) holder.ratingBarLayout, "sharedRatingBar");

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, p1, p2, p3);
                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctors.length;
    }

    public String[] getDoctorsDes(int sec) {
        switch (sec) {
            case 0:
                return context.getResources().getStringArray(R.array.sec1_docs_des_complete);
            case 1:
                return context.getResources().getStringArray(R.array.sec2_docs_des_complete);
            case 2:
                return context.getResources().getStringArray(R.array.sec3_docs_des_complete);
            case 3:
                return context.getResources().getStringArray(R.array.sec4_docs_des_complete);
            default:
                return new String[0];
        }
    }
}


class DocViewHolder extends RecyclerView.ViewHolder {

    CardView rootView;
    TextView docName;
    TextView docDes;
    ImageView docImage;
    RatingBar ratingBar;
    LinearLayout ratingBarLayout;

    public DocViewHolder(View itemView) {
        super(itemView);

        rootView = (CardView) itemView.findViewById(R.id.cardView_root);
        docName = (TextView) itemView.findViewById(R.id.text_doc);
        docDes = (TextView) itemView.findViewById(R.id.text_doc_des);
        docImage = (ImageView) itemView.findViewById(R.id.image_doc);
        ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        ratingBarLayout = (LinearLayout) itemView.findViewById(R.id.ratingbar_layout);
    }
}
