package emoji.cm.hust.edu.cn.emoji;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 用于表情输入的EditText, 客户端不必再处理普通字符的变化.
 * <p>
 * 例如, 先输入表情"[哭][哭]", 再将光标输入到两个表情中间时输入非表情字符, "[哭]123[哭]".
 * 如果没有 EmojiEditText, 客户端只能使用TextWatcher监听EditText的文本变化
 */
public class EmojiEditText extends EditText {
    private int mEmojiconSize = 20;

    public EmojiEditText(Context context) {
        super(context);
    }

    public EmojiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmojiEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        updateText();
    }

    public void setEmojiconSize(int pixels) {
        mEmojiconSize = pixels;
        updateText();
    }

    private void updateText() {
        Emoji.setEmoji(getText());
    }
}
