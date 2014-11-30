package com.romainpiel.darktown;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class EditorActivity extends ActionBarActivity implements ToolbarPresenter {

    @InjectView(R.id.toolbar) AnimatableToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        ButterKnife.inject(this);

        if (savedInstanceState == null) {

            EditorFragment editorFragment = EditorFragment.create()
                    .toolbarPresenter(this)
                    .build();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_placeholder, editorFragment)
                    .commit();
        }

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public void hideToolbar() {
        toolbar.hide();
    }

    @Override
    public void showToolbar() {
        toolbar.show();
    }

}
