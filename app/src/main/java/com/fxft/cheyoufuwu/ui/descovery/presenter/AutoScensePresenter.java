package com.fxft.cheyoufuwu.ui.descovery.presenter;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.interfase.IActivity;
import com.fxft.cheyoufuwu.common.util.BusProvider;
import com.fxft.cheyoufuwu.model.iinterface.IAutoSense;
import com.fxft.cheyoufuwu.model.imp.AutoSense;
import com.fxft.cheyoufuwu.ui.descovery.event.OnAutoSenseListDataChangeEvent;
import com.fxft.cheyoufuwu.ui.descovery.event.OnAutoSenseTopAdDataChangeEvent;
import com.fxft.cheyoufuwu.ui.descovery.iView.IDescoveryView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenDJ on 2015/7/23.<br>
 */
public class AutoScensePresenter implements IActivity {

    private WeakReference<IDescoveryView> mView;
    public AutoScensePresenter(IDescoveryView view) {
        mView = new WeakReference<IDescoveryView>(view);
        BusProvider.getInstance().register(this);
    }

    /**
     * 获取汽车相关知识广告资源
     */
    public void getAutoSenseAD(){
        //测试用数据
        int[] drawableResourceIDs = new int[]{
                R.drawable.testpicture1,
                R.drawable.testpicture2,
                R.drawable.testpicture3
        };
        BusProvider.getInstance().post(new OnAutoSenseTopAdDataChangeEvent(drawableResourceIDs, null));
    }


    /**
     * 获取汽车常识
     */
    public void getAutoSenseList(){
        List<IAutoSense> autoSenses = new ArrayList<>();
        autoSenses.add(new AutoSense("连油液啥时候换都不懂，去做“大保健”可就被坑惨了",
                "http://i3.dpfile.com/pc/b486cc8fd6910bf1f7a0ac8bf60fda68(278x200)/thumb.jpg",
                "保养知识",
                System.currentTimeMillis()));
        autoSenses.add(new AutoSense("连油液啥时候换都不懂，去做“大保健”可就被坑惨了",
                "http://i3.dpfile.com/pc/b486cc8fd6910bf1f7a0ac8bf60fda68(278x200)/thumb.jpg",
                "保养知识",
                System.currentTimeMillis()));
        autoSenses.add(new AutoSense("连油液啥时候换都不懂，去做“大保健”可就被坑惨了",
                "http://i3.dpfile.com/pc/b486cc8fd6910bf1f7a0ac8bf60fda68(278x200)/thumb.jpg",
                "保养知识",
                System.currentTimeMillis()));
        autoSenses.add(new AutoSense("连油液啥时候换都不懂，去做“大保健”可就被坑惨了",
                "http://i3.dpfile.com/pc/b486cc8fd6910bf1f7a0ac8bf60fda68(278x200)/thumb.jpg",
                "保养知识",
                System.currentTimeMillis()));
        autoSenses.add(new AutoSense("连油液啥时候换都不懂，去做“大保健”可就被坑惨了",
                "http://i3.dpfile.com/pc/b486cc8fd6910bf1f7a0ac8bf60fda68(278x200)/thumb.jpg",
                "保养知识",
                System.currentTimeMillis()));
        autoSenses.add(new AutoSense("连油液啥时候换都不懂，去做“大保健”可就被坑惨了",
                "http://i3.dpfile.com/pc/b486cc8fd6910bf1f7a0ac8bf60fda68(278x200)/thumb.jpg",
                "保养知识",
                System.currentTimeMillis()));
        BusProvider.getInstance().post(new OnAutoSenseListDataChangeEvent(autoSenses));
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestory() {
        BusProvider.getInstance().unregister(this);
        mView.clear();
        mView = null;
    }
}
