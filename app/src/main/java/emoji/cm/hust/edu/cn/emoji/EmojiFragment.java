package emoji.cm.hust.edu.cn.emoji;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;


/**
 * Emoji Fragment
 */
public class EmojiFragment extends Fragment implements EmojiAdapter.ViewHolderOnClickListener {
    private static final String ARG_PAGE_NUM = "PAGE_NUM";
    private static final String ARG_PAGE_SIZE = "PAGE_SIZE";

    private int mParamPageNum;
    private int mParamPageSize;

    private OnFragmentInteractionListener mListener;

    @Bind(R.id.emoji_grid)
    RecyclerView mEmojiGrid;
    private EmojiAdapter mEmojiAdapter;
    
    public EmojiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EmojiFragment.
     */
    public static EmojiFragment newInstance(int pageNum, int pageSize) {
        EmojiFragment fragment = new EmojiFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUM, pageNum);
        args.putInt(ARG_PAGE_SIZE, pageSize);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamPageNum = getArguments().getInt(ARG_PAGE_NUM);
            mParamPageSize = getArguments().getInt(ARG_PAGE_SIZE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_emoji, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEmojiGrid.setLayoutManager(new GridLayoutManager(getActivity(), 7));
        mEmojiAdapter = new EmojiAdapter(getContext(), R.layout.item_emoji, this);
        mEmojiGrid.setAdapter(mEmojiAdapter);
        List<Emoji> emojiList = Emoji.getEmojiList();
        int start = mParamPageNum * mParamPageSize;
        int end = (mParamPageNum + 1) * mParamPageSize;
        end = end > emojiList.size() ? emojiList.size() : end;
        if (start < emojiList.size()) {
            mEmojiAdapter.addAll(Emoji.getEmojiList().subList(start, end));
        }
        mEmojiAdapter.notifyDataSetChanged();
        Timber.d("pos=%d,n=%d, start=%d, end=%d", mParamPageNum, mEmojiAdapter.getItemCount(), start, end);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(View view, Emoji emoji) {
        if (mListener != null) {
            mListener.onItemClick(view, emoji);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener extends EmojiAdapter.ViewHolderOnClickListener {
    }
}
