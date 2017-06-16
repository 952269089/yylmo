package com.proton.bystone.ui.main.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.proton.bystone.BystoneApplication;
import com.proton.bystone.R;
import com.proton.bystone.ui.main.MainActivity;
import com.proton.bystone.ui.register.registerActivity;
import com.proton.library.ui.MTFBaseFragment;
import com.proton.library.ui.annotation.MTFFragmentFeature;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@MTFFragmentFeature(layout = R.layout.fragment_me)
public class MeFragment extends MTFBaseFragment {

    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public void initialize() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeFragment newInstance(Bundle args) {
       MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
