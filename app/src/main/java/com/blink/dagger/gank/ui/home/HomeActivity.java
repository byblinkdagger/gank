package com.blink.dagger.gank.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.blink.dagger.gank.R;
import com.blink.dagger.gank.ui.about.AboutActivity;
import com.blink.dagger.gank.ui.home.frag.ganhuo.GanHuoFragment;
import com.blink.dagger.gank.ui.home.frag.meizhi.MeizhiFragment;
import com.blink.dagger.gank.ui.home.frag.video.VideoFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    @InjectView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @InjectView(R.id.frameLayout)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        initBottomNavigationBar();
        initFrag();
        getSupportActionBar().setTitle("福利");
    }

    private void initFrag() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, MeizhiFragment.NewInstance());
        ft.commit();
        //homeToolbar.setTitle("福利");
        //setSupportActionBar(homeToolbar);

    }

    private void initBottomNavigationBar() {
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_gank, "Gank"))
                .addItem(new BottomNavigationItem(R.drawable.ic_meizhi, "Meizhi"))
                .addItem(new BottomNavigationItem(R.drawable.ic_video, "Leisure"))
                .initialise();
        //bottomNavigationBar.setFirstSelectedPosition(1);
        bottomNavigationBar.selectTab(1);
        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        Log.d("lxg", "position : =" + position);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (position) {
            case 0:
                ft.replace(R.id.frameLayout, GanHuoFragment.NewInstance());
                getSupportActionBar().setTitle("干货");
                break;
            case 1:
                ft.replace(R.id.frameLayout, MeizhiFragment.NewInstance());
                getSupportActionBar().setTitle("福利");
                break;
            case 2:
                ft.replace(R.id.frameLayout, VideoFragment.NewInstance());
                getSupportActionBar().setTitle("趣视");
                break;
            default:
                ft.replace(R.id.frameLayout, MeizhiFragment.NewInstance());
                break;
        }
        ft.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.action_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
            intent.putExtra(Intent.EXTRA_TEXT, "hoho~很不错的应用哦~~");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(
                    Intent.createChooser(intent, "Share"));
        }
        return super.onOptionsItemSelected(item);
    }
}
