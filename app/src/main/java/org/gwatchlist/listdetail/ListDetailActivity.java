package org.gwatchlist.listdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.gwatchlist.R;
import org.gwatchlist.util.ActivityUtils;

/**
 *
 * Created by giovanni on 28/02/17.
 */
public class ListDetailActivity extends AppCompatActivity {
    public static final String LIST_ID = "LIST_ID";
    public static final long LIST_PERSONAL = 0;

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

        // Recover previously used fragment
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

        // Initialize presenter
        long listId = getIntent().getLongExtra(LIST_ID, 0);
        new ListDetailPresenter(listDetailFragment, listId);
    }
}
