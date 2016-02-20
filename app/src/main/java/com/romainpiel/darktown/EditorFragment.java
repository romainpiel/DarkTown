package com.romainpiel.darktown;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditorFragment extends Fragment implements ScrollAwareScrollView.OnScrollListener {

    @Bind(R.id.scrollview) ScrollAwareScrollView scrollView;
    @Bind(R.id.input) EditText input;

    private ToolbarPresenter toolbarPresenter;

    public static Builder create() {
        return new Builder();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editor, container, false);
        ButterKnife.bind(this, view);

        scrollView.setOnScrollListener(this);

        return view;
    }

    @Override
    public void onScrollChanged(ScrollView scrollView, int x, int y, int oldX, int oldY) {

        if (y == 0) {
            toolbarPresenter.showToolbar();
        } else {
            toolbarPresenter.hideToolbar();
        }

    }

    public static class Builder {

        private ToolbarPresenter toolbarPresenter;

        public Builder() {
        }

        public Builder toolbarPresenter(ToolbarPresenter toolbarPresenter) {
            this.toolbarPresenter = toolbarPresenter;
            return this;
        }

        public EditorFragment build() {
            EditorFragment editorFragment = new EditorFragment();
            editorFragment.toolbarPresenter = this.toolbarPresenter;
            return editorFragment;
        }
    }
}