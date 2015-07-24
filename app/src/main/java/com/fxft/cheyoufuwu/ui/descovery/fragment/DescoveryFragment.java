package com.fxft.cheyoufuwu.ui.descovery.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.util.ToastUtil;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;
import com.fxft.cheyoufuwu.common.view.SlideShowView;
import com.fxft.cheyoufuwu.ui.descovery.adapter.AutoSensePagerAdapter;
import com.fxft.cheyoufuwu.ui.descovery.adapter.DescoveryListAdapter;
import com.fxft.cheyoufuwu.ui.descovery.iView.IDescoveryView;
import com.fxft.cheyoufuwu.ui.descovery.presenter.AutoScensePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnDescoveryFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DescoveryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescoveryFragment extends Fragment implements IDescoveryView {

    private static final String TAG = DescoveryFragment.class.getSimpleName();
    @Bind(R.id.ctb_descovery_top_bar)
    CommonTopBar mDescoveryTopBar;
    @Bind(R.id.lv_decovery_list)
    ListView mDecoveryList;
    private OnDescoveryFragmentInteractionListener mListener;
    private DescoveryListAdapter mDecoveryListAdapter;
    private AutoSensePagerAdapter mAutoSenseADAdapter;
    private AutoScensePresenter mAutoSencePresenter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DescoveryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DescoveryFragment newInstance() {
        DescoveryFragment fragment = new DescoveryFragment();
        return fragment;
    }

    public DescoveryFragment() {
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
        View view = inflater.inflate(R.layout.fragment_descovery, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initComponent();
        initEvent();
    }

    private void initComponent() {
        //顶部广告控件初始化
        if (mAutoSenseADAdapter == null) {
            mAutoSenseADAdapter = new AutoSensePagerAdapter(getActivity());
        }
        //为了方便加载，通过加载布局的方式来添加头布局
        ViewGroup headerView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.common_top_ad_layout, null, false);
        SlideShowView adView = (SlideShowView) headerView.findViewById(R.id.common_top_ad_view);
        adView.setAutoScrollEnable(true);
        adView.setAdapter(mAutoSenseADAdapter);
        mDecoveryList.addHeaderView(headerView);

        //汽车常识列表适配器初始化
        if (mDecoveryListAdapter == null) {
            mDecoveryListAdapter = new DescoveryListAdapter(getActivity());
        }
        mDecoveryList.setAdapter(mDecoveryListAdapter);
    }

    private void initEvent() {
        mDescoveryTopBar.setOnTitleButtonClickListener(new CommonTopBar.OnTitleButtonClickListener() {
            @Override
            public void onTitleButtonCallback(View view, int buttonPos) {
                switch (buttonPos) {
                    case LEFT_CLICK:
                        //左边的秘籍按钮被点击
                        ToastUtil.showShortToast(getActivity(), "LEFT_CLICK");
                        break;
                    case FIRST_RIGHT_CLICK:
                        //右边的下拉分类选项框被点击
                        ToastUtil.showShortToast(getActivity(), "FIRST_RIGHT_CLICK");
                        break;
                }
            }
        });
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mAutoSencePresenter = new AutoScensePresenter(this);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onResume() {
        mAutoSencePresenter.getAutoSenseAD();
        mAutoSencePresenter.getAutoSenseList();
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mAutoSencePresenter.onDestory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
    public interface OnDescoveryFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
