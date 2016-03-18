package emoji.cm.hust.edu.cn.emoji;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Emoji adapter
 */
public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.ViewHolder> {

    private Context context;
    private int resId;
    private ArrayList<Emoji> emojiList;
    private ViewHolderOnClickListener onClickListener;


    public EmojiAdapter(Context context, int resId, ViewHolderOnClickListener onClickListener) {
        this.context = context;
        this.resId = resId;
        this.emojiList = new ArrayList<>();
        this.onClickListener = onClickListener;
    }

    public void add(Emoji emoji) {
        emojiList.add(emoji);
    }

    public void addAll(List<Emoji> list) {
        emojiList.addAll(list);
    }

    public void setEmojiList(List<Emoji> list) {
        emojiList.clear();
        emojiList.addAll(list);
    }

    public Emoji get(int index) {
        return emojiList.get(index);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resId, parent, false);
        return new ViewHolder(view, onClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Emoji emoji = emojiList.get(position);
        holder.ivEmoji.setImageDrawable(emoji.drawable);
        holder.llRoot.setTag(R.id.ll_root, emoji.chars);
    }

    @Override
    public int getItemCount() {
        return emojiList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.ll_root)
        LinearLayout llRoot;
        @Bind(R.id.iv_emoji)
        ImageView ivEmoji;

        private ViewHolderOnClickListener onClickListener;

        public ViewHolder(View itemView, ViewHolderOnClickListener onClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.onClickListener = onClickListener;
        }

        @OnClick(R.id.ll_root)
        public void onClick(View view) {
            if (onClickListener != null) {
                onClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ViewHolderOnClickListener {
        void onItemClick(View view, int pos);
    }
}
