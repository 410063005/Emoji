package emoji.cm.hust.edu.cn.emoji;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class EmojiActivity extends AppCompatActivity implements EmojiAdapter.ViewHolderOnClickListener {

    /**
     * display emoji in this EditText
     */
    @Bind(R.id.emoji)
    EditText emoji;
    /**
     * display chars in this EditText
     */
    @Bind(R.id.emoji_edit)
    EditText emojiEdit;
    @Bind(R.id.emoji_grid)
    RecyclerView emojiGrid;

    EmojiAdapter emojiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int visibility = emojiGrid.getVisibility();
                if (visibility == View.VISIBLE) {
                    emojiGrid.setVisibility(View.GONE);
                } else {
                    emojiGrid.setVisibility(View.VISIBLE);
                }
            }
        });

        ButterKnife.bind(this);

        Emoji.init(this);

        emojiGrid.setLayoutManager(new GridLayoutManager(this, 7));
        emojiAdapter = new EmojiAdapter(this, R.layout.item_emoji, this);
        emojiGrid.setAdapter(emojiAdapter);
        emojiAdapter.addAll(Emoji.getEmojiList());
        emojiAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.emoji_demo, R.id.emoji_demo2})
    public void onClick(View view) {
        final EditText edit = emojiEdit;

        Emoji emoji = getEmojiFromView(view);

        if (edit.getSelectionStart() != -1) {
            String text = edit.getText().toString();
            int start = 0;
            int end = text.length();
            int mid = edit.getSelectionStart(); // 记住插入表情的位置

            SpannableString spanned = new SpannableString(text.substring(start, mid) + emoji.chars + text.substring(mid, end));
            edit.setText(spanned);
            edit.setSelection(mid + emoji.chars.length()); // 插入的表情后光标移到表情字符之后

            displayEmoji(spanned);
        } else {
            Timber.w("No selection?");
        }
    }

    private void displayEmoji(SpannableString spanned) {
        Pattern p = Pattern.compile("(\\\\:[0-9a-fA-F]{4,5}).*?");
        Matcher m = p.matcher(spanned);
        int start = 0;
        while (m.find(start)) {
            start = m.start() + 1;

            int from = m.start();
            int to = m.end();
            String matched = m.group(1);

            Emoji emoji = Emoji.getEmojiByTag(matched);
            if (emoji == null) {
                continue;
            }

            ImageSpan span = new ImageSpan(emoji.drawable, "");
            spanned.setSpan(span, from, to, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        emoji.setText(spanned);
    }

    private Emoji getEmojiFromView(View view) {
        switch (view.getId()) {
            case R.id.emoji_demo:
                return Emoji.getEmojiByTag("\\:1F601");

            case R.id.emoji_demo2:
                return Emoji.getEmojiByTag("\\:1F602");

            default:
                return Emoji.getEmojiByTag(null);
        }
    }

    @Override
    public void onItemClick(View view, int pos) {
        Emoji emoji = emojiAdapter.get(pos);

        final EditText edit = emojiEdit;
        if (edit.getSelectionStart() != -1) {
            String text = edit.getText().toString();
            int start = 0;
            int end = text.length();
            int mid = edit.getSelectionStart(); // 记住插入表情的位置

            SpannableString spanned = new SpannableString(text.substring(start, mid) + emoji.chars + text.substring(mid, end));
            edit.setText(spanned);
            edit.setSelection(mid + emoji.chars.length()); // 插入的表情后光标移到表情字符之后

            displayEmoji(spanned);
        } else {
            Timber.w("No selection?");
        }
    }
}
