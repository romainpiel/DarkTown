package com.romainpiel.darktown;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class EditorActivity extends AppCompatActivity implements ToolbarPresenter {

    @Bind(R.id.toolbar) AnimatableToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        ButterKnife.bind(this);

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
