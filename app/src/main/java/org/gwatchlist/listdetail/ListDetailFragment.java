package org.gwatchlist.listdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.gwatchlist.R;

/**
 *
 * Created by giovanni on 28/02/17.
 */
public class ListDetailFragment extends Fragment {

    private ListDetailContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_list_detail, container, false);

        return rootView;
    }
}
