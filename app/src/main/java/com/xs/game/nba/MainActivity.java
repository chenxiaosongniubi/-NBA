package com.xs.game.nba;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xs.game.nba.fragment.DiscussFragment;
import com.xs.game.nba.fragment.GameVideoFragment;
import com.xs.game.nba.fragment.HomeFragemnt;
import com.xs.game.nba.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getName();
    public final static String TAG_HOME = "Home";
    public final static String TAG_GAME_VIDEO = "GameVideo";
    public final static String TAG_COMMUNITY = "Community";
    public final static String TAG_MINE = "Mine";
    public final static String EXT_KEY_SHOW_PAGE = "show_page";
    @BindView(R.id.home_tv)
    TextView homeTv;
    @BindView(R.id.game_tv)
    TextView gameTv;
    @BindView(R.id.community_tv)
    TextView communityTv;
    @BindView(R.id.me_tv)
    TextView meTv;
    @BindView(R.id.content)
    FrameLayout content;


    private List<Page> mPageList = new ArrayList<>();
    private Page mCurPage;

    class Page {
        String TAG;
        Fragment pageFragment;
        int FocusIconResId;
        int UnFocusIconResId;
        TextView BottomTitle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPages();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void initPages() {
        mPageList.clear();
        Page page = new Page();
        page.TAG = TAG_HOME;
        page.pageFragment = new HomeFragemnt();
        page.FocusIconResId = R.drawable.ic_action_home;
        page.UnFocusIconResId = R.drawable.ic_action_home_u;
        page.BottomTitle = homeTv;
        mPageList.add(page);

        page = new Page();
        page.TAG = TAG_GAME_VIDEO;
        page.pageFragment = new GameVideoFragment();
        page.FocusIconResId = R.drawable.ic_action_achievement;
        page.UnFocusIconResId = R.drawable.ic_action_achievement_u;
        page.BottomTitle = gameTv;
        mPageList.add(page);

        page = new Page();
        page.TAG = TAG_COMMUNITY;
        page.pageFragment = new DiscussFragment();
        page.FocusIconResId = R.drawable.ic_action_emo_basic;
        page.UnFocusIconResId = R.drawable.ic_action_emo_basic_u;
        page.BottomTitle = communityTv;
        mPageList.add(page);

        page = new Page();
        page.TAG = TAG_MINE;
        page.pageFragment = new MineFragment();
        page.FocusIconResId = R.drawable.ic_action_user;
        page.UnFocusIconResId = R.drawable.ic_action_user_u;
        page.BottomTitle = meTv;
        mPageList.add(page);
        String showPage = getIntent().getStringExtra(EXT_KEY_SHOW_PAGE);
        if (TextUtils.isEmpty(showPage)) {
            showPage = TAG_HOME;
        }
        Log.e(TAG, page.FocusIconResId + "");
        Log.e(TAG, page.BottomTitle + "");
        Log.d(TAG, showPage.toString());
        showPage(showPage);
    }

    private void showPage(String tag) {
        Log.d(TAG, "showPage");
        for (Page page : mPageList) {
            if (page.TAG.equals(tag)) {
                page.BottomTitle.setCompoundDrawablesWithIntrinsicBounds(0, page.FocusIconResId, 0, 0);
                page.BottomTitle.setTextColor(getResources().getColor(R.color.blue));
                if (mCurPage != page) {
                    onPageChange(page);
                }
            } else {
                page.BottomTitle.setCompoundDrawablesWithIntrinsicBounds(0, page.UnFocusIconResId, 0, 0);
                page.BottomTitle.setTextColor(getResources().getColor(R.color.text_color));
            }
        }
    }

    private void onPageChange(Page page) {
        mCurPage = page;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, page.pageFragment);
        transaction.commit();
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
