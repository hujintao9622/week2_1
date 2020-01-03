package com.bawei.week2_1.view.activity;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week2_1.R;
import com.bawei.week2_1.base.BaseActivity;

import com.bawei.week2_1.database.DaoMaster;
import com.bawei.week2_1.database.DaoSession;
import com.bawei.week2_1.database.LeftBeanDao;
import com.bawei.week2_1.database.RightBeanDao;
import com.bawei.week2_1.model.bean.CategoryBean;
import com.bawei.week2_1.model.bean.LeftBean;
import com.bawei.week2_1.model.bean.ListBean;
import com.bawei.week2_1.model.bean.RightBean;
import com.bawei.week2_1.presenter.MainPresenter;
import com.bawei.week2_1.util.NetUtil;
import com.bawei.week2_1.view.adapter.LeftAdapter;
import com.bawei.week2_1.view.adapter.RightAdapter;
import com.bawei.week2_1.view.adapter.RightListAdapter;
import com.bawei.week2_1.view.adapter.TitleAdapter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.List;
import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> {
    @BindView(R.id.ma_rc_left)
    RecyclerView maRcLeft;
    @BindView(R.id.ma_rc_right)
    RecyclerView maRcRight;
    private LeftBeanDao leftBeanDao;
   private RightBeanDao rightBeanDao;

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initData() {
        if (NetUtil.getInstance().hasNet(this)){
            presenter.getTitleData();
        }else {
            LeftBean unique = leftBeanDao.queryBuilder().unique();
            String leftjson = unique.getLeftjson();
            CategoryBean categoryBean = new Gson().fromJson(leftjson, CategoryBean.class);
            List<CategoryBean.ResultBean> result = categoryBean.getResult();
            List<CategoryBean.ResultBean.SecondCategoryVoBean> categoryVo = result.get(0).getSecondCategoryVo();
            LeftAdapter leftAdapter = new LeftAdapter(categoryVo);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {
                    List<RightBean> list = rightBeanDao.queryBuilder().list();
                    RightBean rightBean = list.get(position);
                    String rightjson = rightBean.getRightjson();
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(MainActivity.this);
                    ListBean listBean = new Gson().fromJson(rightjson, ListBean.class);
                    List<ListBean.ResultBean> result1 = listBean.getResult();
                    RightAdapter rightAdapter = new RightAdapter(result1);
                    maRcRight.setLayoutManager(linearLayoutManager1);
                    maRcRight.setAdapter(rightAdapter);
                }
            });
            maRcLeft.setLayoutManager(linearLayoutManager);
            maRcLeft.setAdapter(leftAdapter);
        }
    }

    @Override
    protected void initView() {
        //数据库
        DaoSession daoSession = DaoMaster.newDevSession(this, "app.db");
        leftBeanDao = daoSession.getLeftBeanDao();
        rightBeanDao = daoSession.getRightBeanDao();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    //右侧列表的数据
    public void onMainSuccess(ListBean listBean) {
        //转json串
        String rightjson = new Gson().toJson(listBean);
        //存储到数据库
        rightBeanDao.insert(new RightBean(rightjson));
        //设置列表展示
        List<ListBean.ResultBean> result = listBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        maRcRight.setLayoutManager(linearLayoutManager);
        maRcRight.setAdapter(new RightListAdapter(result));

    }

    @Override
    public void onMainFailure(Throwable throwable) {
        Log.e("tag",throwable.getMessage());
    }

    @Override
    public void onTitleSuccess(CategoryBean categoryBean) {
        leftBeanDao.deleteAll();
        //转json串
        String leftjson = new Gson().toJson(categoryBean);
        //存储到数据库
        leftBeanDao.insert(new LeftBean(leftjson));
        //设置列表展示
        List<CategoryBean.ResultBean> result = categoryBean.getResult();
        List<CategoryBean.ResultBean.SecondCategoryVoBean> secondCategoryVo = result.get(0).getSecondCategoryVo();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        maRcLeft.setLayoutManager(linearLayoutManager);
        TitleAdapter titleAdapter = new TitleAdapter(secondCategoryVo);
        //适配器条目点击事件
        titleAdapter.setOnItemClickListener(new TitleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                EventBus.getDefault().post(secondCategoryVo.get(position).getId());
            }
        });
        //设置适配器
        maRcLeft.setAdapter(titleAdapter);
        //加载右侧列表数据
        presenter.getMainData(secondCategoryVo.get(0).getId());
    }

    @Override
    public void onTitleFailure(Throwable throwable) {
        Log.e("tag",throwable.getMessage());
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPosition(String position){
        presenter.getMainData(position);
    };
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
