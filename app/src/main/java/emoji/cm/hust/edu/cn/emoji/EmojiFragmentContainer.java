package emoji.cm.hust.edu.cn.emoji;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kingcmchen on 2016/3/21.
 */
public class EmojiFragmentContainer extends Fragment {

    @Bind(R.id.pager)
    ViewPager mViewPager;

    public EmojiFragmentContainer() {

    }

    public static EmojiFragmentContainer newInstance() {
        EmojiFragmentContainer container = new EmojiFragmentContainer();
        return container;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emoji_container, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return EmojiFragment.newInstance(position, 28);
            }

            @Override
            public int getCount() {
                return (int) (Emoji.getEmojiList().size() / 28 + 0.5);
            }
        });
    }
}
