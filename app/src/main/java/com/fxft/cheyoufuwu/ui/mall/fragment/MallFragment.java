package com.fxft.cheyoufuwu.ui.mall.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.ui.mall.adapter.RecommendMerchandiseAdapter;
import com.fxft.cheyoufuwu.ui.mall.customview.RecommendMerchandiseHeaderView;
import com.fxft.cheyoufuwu.ui.mall.iView.IMallView;
import com.fxft.cheyoufuwu.ui.mall.presenter.MallPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnMallFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MallFragment extends Fragment implements IMallView {
    @Bind(R.id.lv_mall_merchandise_list)
    ListView mMallMerchandiseList;
    private OnMallFragmentInteractionListener mListener;
    private RecommendMerchandiseAdapter mMerchantdiseAdapter;
    private MallPresenter mMallPresenter;
    private RecommendMerchandiseHeaderView mRecommendHeaderView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MallFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MallFragment newInstance() {
        MallFragment fragment = new MallFragment();
        return fragment;
    }

    public MallFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mall, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        mMallPresenter.getRecommendMerchandises();
        mMallPresenter.getMerchandise();
        super.onResume();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initComponent();
    }

    private void initComponent() {
        //添加顶部视图
        if (mRecommendHeaderView == null) {
            mRecommendHeaderView = new RecommendMerchandiseHeaderView(getActivity());
        }
        mMallMerchandiseList.addHeaderView(mRecommendHeaderView);

        //适配器
        if (mMerchantdiseAdapter == null) {
            mMerchantdiseAdapter = new RecommendMerchandiseAdapter(getActivity());
        }
        mMallMerchandiseList.setAdapter(mMerchantdiseAdapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMallPresenter = new MallPresenter(this);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnMallFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
