package aa.se.com.hospitalmanagement;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.iconics.context.IconicsLayoutInflater;

public class DoctorsActivity extends AppCompatActivity {

    private Typeface yekanFont;
    ListView navDrawer;
    TextView toolbar_title;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private RecyclerView recyclerView;
    private int section;  //which section is selected
    private String[] depsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Enable automatic xml icons detection
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        depsName = getResources().getStringArray(R.array.depsName);
        section = getIntent().getIntExtra("SECTION", 0);
        yekanFont = Typeface.createFromAsset(getAssets(), "fonts/b_yekan.ttf");
        navDrawer = (ListView) findViewById(R.id.listView_navDrawer);
        toolbar_title = (TextView) findViewById(R.id.textView_toolbar_title);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_content_doc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                syncState();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                syncState();
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayShowTitleEnabled(false);  //hide actionBar title

        toolbar_title.setText(depsName[section]);
        toolbar_title.setTypeface(yekanFont);

        navDrawer.setAdapter(new NavDrawerAdapter(this, getResources().getStringArray(R.array.navDrawer), yekanFont));
        navDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        Toast.makeText(DoctorsActivity.this, "قرار ملاقات!", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(DoctorsActivity.this, "گزارش!", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(DoctorsActivity.this, "پروفایل!", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(DoctorsActivity.this, getString(R.string.exit), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        DocActivity_Recyclerview_Adapter adapter = new DocActivity_Recyclerview_Adapter(recyclerView.getContext(), yekanFont, getDoctors(section));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(DoctorsActivity.this));
    }

    public String[] getDoctors(int sec) {  //get all doctors in a section
        switch (sec) {
            case 0:
                return getResources().getStringArray(R.array.sec1_docs);
            case 1:
                return getResources().getStringArray(R.array.sec2_docs);
            case 2:
                return getResources().getStringArray(R.array.sec3_docs);
            default:
                return new String[0];
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
