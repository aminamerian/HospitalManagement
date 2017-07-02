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
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

public class MainActivity extends AppCompatActivity {

    private Typeface yekanFont;
    private RecyclerView recyclerView;
    ListView navDrawer;
    TextView toolbar_title;
    TextView toolbar_price;
    TextView toolbar_badge;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    int c=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Enable automatic xml icons detection
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        yekanFont = Typeface.createFromAsset(getAssets(), "fonts/b_yekan.ttf");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_content);
        navDrawer = (ListView) findViewById(R.id.listView_navDrawer);
        toolbar_title = (TextView) findViewById(R.id.textView_toolbar_title);
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
                        Toast.makeText(MainActivity.this, "قرار ملاقات!", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "گزارش!", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(MainActivity.this, "پروفایل!", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(MainActivity.this, getString(R.string.exit), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        MainActivity_Recyclerview_Adapter adapter = new MainActivity_Recyclerview_Adapter(recyclerView.getContext(), yekanFont);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        // Inflate the menu_options; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        MenuItem badge = menu.findItem(R.id.item_badge);
        MenuItem price = menu.findItem(R.id.item_price);

        toolbar_price = (TextView) price.getActionView().findViewById(R.id.textView_menuItem);
        toolbar_price.setTypeface(yekanFont);
        toolbar_badge = (TextView) badge.getActionView().findViewById(R.id.menu_badge);
        toolbar_badge.setTypeface(yekanFont);

        badge.getActionView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c++;
                if (c < 10) {
                    updateBadge(String.valueOf(c));
                    updatePrice(String.valueOf(c));
                }else{
                    updateBadge("<10");
                    updatePrice("پیش از اندازه");
                }

                //Toast.makeText(MainActivity.this, "سبد شما خالیست!", Toast.LENGTH_SHORT).show();

            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Animation anim_error = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake_anim);

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.cart) {
//            return true;
//        } else
        if (id == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }


    public void updateBadge(String str) {
        toolbar_badge.setText(str);
    }

    public void updatePrice(String str) {
        toolbar_price.setText(str);
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
