package com.romainpiel.darktown;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class EditorActivity extends Activity implements ToolbarPresenter {

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

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_placeholder, editorFragment)
                    .commit();
        }

        if (toolbar != null) {
            setActionBar(toolbar);
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
