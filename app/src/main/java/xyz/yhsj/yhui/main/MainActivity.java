package xyz.yhsj.yhui.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import xyz.yhsj.yhui.R;
import xyz.yhsj.yhui.base.YH_Activity;
import xyz.yhsj.yhui.main.fragment.Fragment_DB;
import xyz.yhsj.yhui.main.fragment.TabAdapter;
import xyz.yhsj.yhui.main.fragment.TestFragment;
import xyz.yhsj.yhui.plugins.Activity_Setting;


public class MainActivity extends YH_Activity {

    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;
    @ViewInject(R.id.tabs)
    private TabLayout tabLayout;
    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;
    @ViewInject(R.id.drawer_layout)
    private DrawerLayout mDrawerLayout;
    @ViewInject(R.id.nav_view)
    private NavigationView navigationView;
    @ViewInject(R.id.content)
    private CoordinatorLayout lay_content;
//    @ViewInject(R.id.fab)
//    private FloatingActionButton fab;

    private ActionBar ab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewUtils.inject(this);

        setSupportActionBar(toolbar);

        ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);


        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        //tab和滑动界面绑定
        tabLayout.setupWithViewPager(viewPager);

    }

    /**
     * 设置左滑菜单
     *
     * @param navigationView
     */
    private void setupDrawerContent(NavigationView navigationView) {
        //设置自动关闭
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                Snackbar.make(lay_content, menuItem.getTitle(), Snackbar.LENGTH_LONG).setActionTextColor(Color.parseColor("#ffffff")).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();

                return true;
            }
        });

    }

    /**
     * 设置可滑动界面
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_DB(), "待办");
        adapter.addFragment(new TestFragment("消息"), "消息");
        adapter.addFragment(new TestFragment("联系人"), "联系人");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:

                startActivity(new Intent(MainActivity.this, Activity_Setting.class));
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
