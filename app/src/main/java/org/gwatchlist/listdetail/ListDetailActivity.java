package org.gwatchlist.listdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.gwatchlist.R;
import org.gwatchlist.login.LoginFragment;
import org.gwatchlist.util.ActivityUtils;

/**
 *
 * Created by giovanni on 28/02/17.
 */
public class ListDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list_detail);

        // Set up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }

        // Recover previosly used fragment
        ListDetailFragment listDetailFragment = (ListDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (listDetailFragment == null) {
            listDetailFragment = new ListDetailFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    listDetailFragment,
                    R.id.contentFrame
            );
        }

        new ListDetailPresenter(listDetailFragment);

    }
}
