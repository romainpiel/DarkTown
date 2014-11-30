package com.romainpiel.darktown;

import android.support.v4.app.FragmentActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class EditorFragmentTest {

    private EditorFragment fragment;
    private ToolbarPresenter toolbarPresenter;

    @Before
    public void setUp() {
        FragmentActivity activity = Robolectric.buildActivity(FragmentActivity.class)
                .create()
                .start()
                .resume()
                .visible()
                .get();

        toolbarPresenter = mock(ToolbarPresenter.class);
        fragment = EditorFragment.create()
                .toolbarPresenter(toolbarPresenter)
                .build();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(fragment, null)
                .commit();
        activity.getSupportFragmentManager()
                .executePendingTransactions();
    }

    @Test
    public void scrollTo_top_shouldShowToolbar() {
        fragment.scrollView.smoothScrollTo(0, 0);
        verify(toolbarPresenter).showToolbar();
    }

    @Test
    public void scrollTo_bottom_shouldHideToolbar() {
        fragment.scrollView.smoothScrollTo(0, 100);
        verify(toolbarPresenter).hideToolbar();
    }
}
