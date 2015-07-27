package com.fxft.cheyoufuwu.ui.homePage.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.interfase.IBaseView;
import com.fxft.cheyoufuwu.common.util.SystemUtil;
import com.fxft.cheyoufuwu.common.util.ToastUtil;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;
import com.fxft.cheyoufuwu.common.view.SlideShowView;
import com.fxft.cheyoufuwu.common.view.scrollview.ObservableScrollView;
import com.fxft.cheyoufuwu.common.view.scrollview.ObservableScrollViewCallbacks;
import com.fxft.cheyoufuwu.common.view.scrollview.ScrollState;
import com.fxft.cheyoufuwu.ui.homePage.activity.choose_city.ChooseCityActivity;
import com.fxft.cheyoufuwu.ui.homePage.adapter.NearByMerchantAdapter;
import com.fxft.cheyoufuwu.ui.homePage.adapter.TopADPagerAdapter;
import com.fxft.cheyoufuwu.ui.homePage.iView.IHomeView;
import com.fxft.cheyoufuwu.ui.homePage.presenter.HomePresenter;
import com.nineoldandroids.view.ViewHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnHomePageFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment implements IHomeView, IBaseView {

    private static final String TAG = HomePageFragment.class.getSimpleName();
    @Bind(R.id.ssv_home_nearby_merchant)
    SlideShowView mHomeNearbyMerchant;  //附近商户的显示控件

    @Bind(R.id.ssv_top_ad)
    SlideShowView mTopAdView; //顶部的广告控件

    @Bind(R.id.sv_homePage)
    ObservableScrollView mScrollView;

    @Bind(R.id.overlay)
    View mOverlayView;

    @Bind(R.id.ctb_homepage_top_bar)
    CommonTopBar mHomepageTopBar;   //顶部的TitleView
    private int imageSpaceHeight;   //顶部滚动广告控件的高度
    private int actionBarSize;

    private OnHomePageFragmentInteractionListener mListener;
    private TopADPagerAdapter mTopAdViewAdapter;
    private NearByMerchantAdapter mNearMerchantAdapter;
//    private GuessLikeListAdapter mGuessListAdapter;

    /**
     * 数据提供层
     */
    private HomePresenter presenter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomePageFragment.
     */
    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        return fragment;
    }

    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewContainer = inflater.inflate(R.layout.fragment_home_page, container, false);
        ButterKnife.bind(this, viewContainer);
        imageSpaceHeight = getActivity().getResources().getDimensionPixelSize(R.dimen.flexible_space_image_height);
        actionBarSize = SystemUtil.getActionBarSize(getActivity());
        return viewContainer;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化一些控件
        initComponent();
        initEvent();
    }

    /**
     * 初始化界面控件的事件
     */
    private void initEvent() {
        //中间scrollview的滑动事件
        mScrollView.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            @Override
            public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
                int flexibleRange = imageSpaceHeight - actionBarSize;
                int minOverlayTransitionY = actionBarSize - mOverlayView.getHeight();
                ViewHelper.setTranslationY(mOverlayView, Math.min(0, Math.max(-scrollY, minOverlayTransitionY)));
                ViewHelper.setAlpha(mOverlayView, 1 - Math.max(0, (float) (flexibleRange - scrollY) / flexibleRange));
            }

            @Override
            public void onDownMotionEvent() {

            }

            @Override
            public void onUpOrCancelMotionEvent(ScrollState scrollState) {

            }
        });
        //顶部广告控件点击事件
        mTopAdView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 点击跳入到广告详情
            }
        });
        mTopAdView.setOnSlideViewScrollListener(new SlideShowView.OnSlideViewScrollListener() {
            @Override
            public void onPageScrolled(int scrollDerection, int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }
        });
        //顶部工具栏的事件
        mHomepageTopBar.setOnTitleButtonClickListener(new CommonTopBar.OnTitleButtonClickListener() {
            @Override
            public void onTitleButtonCallback(View view, int buttonPos) {
                switch (buttonPos) {
                    case CommonTopBar.OnTitleButtonClickListener.LEFT_CLICK:    //顶部工具栏的左侧按钮点击事件
                        //跳到城市选择界面
                        Intent intent = new Intent(getActivity(), ChooseCityActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case CommonTopBar.OnTitleButtonClickListener.MIDDLE_CLICK:  //顶部中部自定义布局点击事件
                        ToastUtil.showShortToast(getActivity(), "MIDDLE_CLICK");
                        break;
                    case CommonTopBar.OnTitleButtonClickListener.FIRST_RIGHT_CLICK:     //顶部右侧第一个按钮的点击事件
                        ToastUtil.showShortToast(getActivity(), "FIRST_RIGHT_CLICK");
                        break;
                    case CommonTopBar.OnTitleButtonClickListener.SEC_RIGHT_CLICK:   //顶部右侧第二个按钮的点击事件
                        ToastUtil.showShortToast(getActivity(), "SEC_RIGHT_CLICK");
                        break;
                    case CommonTopBar.OnTitleButtonClickListener.THIR_RIGHT_CLICK:  //顶部右侧第三个按钮的点击事件
                        ToastUtil.showShortToast(getActivity(), "THIR_RIGHT_CLICK");
                        break;
                }
            }
        });
    }

    private void initComponent() {
        //顶部广告控件
        if (mTopAdViewAdapter == null) {
            mTopAdViewAdapter = new TopADPagerAdapter(getActivity());
        }
        mTopAdView.setAdapter(mTopAdViewAdapter);

        //附近商家广告控件
        if (mNearMerchantAdapter == null) {
            mNearMerchantAdapter = new NearByMerchantAdapter(getActivity());
        }
        mHomeNearbyMerchant.setAdapter(mNearMerchantAdapter);

        //猜你喜欢列表适配
//        if (mGuessListAdapter == null) {
//            mGuessListAdapter = new GuessLikeListAdapter(getActivity());
//        }
//        mGuessList.setAdapter(mGuessListAdapter);
        ViewHelper.setAlpha(mOverlayView, 0.0f);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        presenter = new HomePresenter(this);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //获取顶部的广告
        presenter.getADUrls();
        //获取附近商户数据
        presenter.getNearByMerchantDate();
        //获取猜你喜欢数据
        presenter.getFavouriteMerchant();
    }

    @Override
    public void recyclePresenter() {
        if (presenter != null) {
            presenter.onDestory();
        }
    }

    @Override
    public void recycleAdapter() {
        if (mTopAdViewAdapter != null) {
            mTopAdViewAdapter.onDestory();
        }
        if (mNearMerchantAdapter != null) {
            mNearMerchantAdapter.onDestory();
        }
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        recyclePresenter();
        recycleAdapter();
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnHomePageFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
