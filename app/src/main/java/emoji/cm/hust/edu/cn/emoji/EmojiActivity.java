package emoji.cm.hust.edu.cn.emoji;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class EmojiActivity extends AppCompatActivity implements EmojiAdapter.ViewHolderOnClickListener,
        EmojiFragment.OnFragmentInteractionListener {

    /**
     * display emoji in this EditText
     */
//    @Bind(R.id.emoji)
//    EditText emoji;

    @Bind(R.id.chat_list)
    ListView chatList;

    /**
     * display chars in this EditText
     */
    @Bind(R.id.send_bar_text)
    EditText emojiEdit;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        ButterKnife.bind(this);

        adapter = new ArrayAdapter<String>(this, R.layout.list_item_1, new ArrayList()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                EditText textView = (EditText) view;
                Emoji.setEmoji(textView.getText());
                return view;
            }
        };
        chatList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, Emoji emoji) {
        final EditText edit = emojiEdit;
        if (edit.getSelectionStart() != -1) {
            String text = edit.getText().toString();
            int start = 0;
            int end = text.length();
            int mid = edit.getSelectionStart(); // 记住插入表情的位置

            StringBuilder sb = new StringBuilder(text.substring(start, mid));
            sb.append(emoji.chars);
            sb.append(text.substring(mid, end));

            edit.setText(sb);
            edit.setSelection(mid + emoji.chars.length()); // 插入的表情后光标移到表情字符之后
        } else {
            Timber.w("No selection?");
        }
    }

    @OnClick(R.id.send_bar_emoji)
    public void onOpenEmojiContainer(View view) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("emoji_root");
        if (fragment == null) {
            hideSoftInput(view);
        } else {
            showSoftInput(view);
        }
    }

    @OnClick(R.id.send_bar_text)
    public void onClickEmojiEdit(View view) {
        showSoftInput(view);
    }

    @OnClick(R.id.send_bar_send)
    public void onSend(View view) {
        String msg = emojiEdit.getText().toString();
        adapter.add(msg);

        emojiEdit.setText(null);
        chatList.smoothScrollToPosition(adapter.getCount());
    }

    private void hideSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag("emoji_root");
        if (fragment == null) {
            fragment = EmojiFragmentContainer.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.send_bar_root, fragment, "emoji_root").commit();
        }
    }

    private void showSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(emojiEdit, 0);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag("emoji_root");
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }
}
