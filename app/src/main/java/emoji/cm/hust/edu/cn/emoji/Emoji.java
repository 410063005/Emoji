package emoji.cm.hust.edu.cn.emoji;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import java.util.HashMap;

public class Emoji {
    public final Drawable drawable; // 表情图标
    public final CharSequence chars;// 表情字符

    private Emoji(Drawable drawable, CharSequence chars) {
        // 不设置bounds时无法显示表情
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        this.drawable = drawable;
        this.chars = chars;
    }

    public static void init(Context context) {
        if (sEmojiMap == null) {
            sEmojiMap = new HashMap<>();
        }
        sEmojiMap.put("\\:1F601", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f601), "\\:1F601"));
        sEmojiMap.put("\\:1F602", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f602), "\\:1F602"));
        sEmojiMap.put("\\:1F603", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f603), "\\:1F603"));
        sEmojiMap.put("\\:1F604", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f604), "\\:1F604"));
        sEmojiMap.put("\\:1F605", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f605), "\\:1F605"));
        sEmojiMap.put("\\:1F606", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f606), "\\:1F606"));
        sEmojiMap.put("\\:1F609", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f609), "\\:1F609"));
        sEmojiMap.put("\\:1F60A", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f60a), "\\:1F60A"));
        sEmojiMap.put("\\:1F60B", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f60b), "\\:1F60B"));
        sEmojiMap.put("\\:1F60C", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f60c), "\\:1F60C"));
        sEmojiMap.put("\\:1F60D", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f60d), "\\:1F60D"));
        sEmojiMap.put("\\:1F60F", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f60f), "\\:1F60F"));
        sEmojiMap.put("\\:1F612", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f612), "\\:1F612"));
        sEmojiMap.put("\\:1F613", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f613), "\\:1F613"));
        sEmojiMap.put("\\:1F614", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f614), "\\:1F614"));
        sEmojiMap.put("\\:1F616", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f616), "\\:1F616"));
        sEmojiMap.put("\\:1F618", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f618), "\\:1F618"));
        sEmojiMap.put("\\:1F61A", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f61a), "\\:1F61A"));
        sEmojiMap.put("\\:1F61C", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f61c), "\\:1F61C"));
        sEmojiMap.put("\\:1F61D", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f61d), "\\:1F61D"));
        sEmojiMap.put("\\:1F61E", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f61e), "\\:1F61E"));
        sEmojiMap.put("\\:1F620", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f620), "\\:1F620"));
        sEmojiMap.put("\\:1F621", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f621), "\\:1F621"));
        sEmojiMap.put("\\:1F622", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f622), "\\:1F622"));
        sEmojiMap.put("\\:1F623", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f623), "\\:1F623"));
        sEmojiMap.put("\\:1F624", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f624), "\\:1F624"));
        sEmojiMap.put("\\:1F625", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f625), "\\:1F625"));
        sEmojiMap.put("\\:1F628", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f628), "\\:1F628"));
        sEmojiMap.put("\\:1F629", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f629), "\\:1F629"));
        sEmojiMap.put("\\:1F62A", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f62a), "\\:1F62A"));
        sEmojiMap.put("\\:1F62B", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f62b), "\\:1F62B"));
        sEmojiMap.put("\\:1F630", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f630), "\\:1F630"));
        sEmojiMap.put("\\:1F631", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f631), "\\:1F631"));
        sEmojiMap.put("\\:1F632", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f632), "\\:1F632"));
        sEmojiMap.put("\\:1F633", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f633), "\\:1F633"));
        sEmojiMap.put("\\:1F635", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f635), "\\:1F635"));
        sEmojiMap.put("\\:1F637", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f637), "\\:1F637"));
        sEmojiMap.put("\\:1F638", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f638), "\\:1F638"));
        sEmojiMap.put("\\:1F639", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f639), "\\:1F639"));
        sEmojiMap.put("\\:1F63A", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f63a), "\\:1F63A"));
        sEmojiMap.put("\\:1F63B", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f63b), "\\:1F63B"));
        sEmojiMap.put("\\:1F63C", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f63c), "\\:1F63C"));
        sEmojiMap.put("\\:1F63D", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f63d), "\\:1F63D"));
        sEmojiMap.put("\\:1F63E", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f63e), "\\:1F63E"));
        sEmojiMap.put("\\:1F63F", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f63f), "\\:1F63F"));
        sEmojiMap.put("\\:1F640", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f640), "\\:1F640"));
        sEmojiMap.put("\\:1F645", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f645), "\\:1F645"));
        sEmojiMap.put("\\:1F646", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f646), "\\:1F646"));
        sEmojiMap.put("\\:1F647", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f647), "\\:1F647"));
        sEmojiMap.put("\\:1F648", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f648), "\\:1F648"));
        sEmojiMap.put("\\:1F64A", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f64a), "\\:1F64A"));
        sEmojiMap.put("\\:1F64B", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f64b), "\\:1F64B"));
        sEmojiMap.put("\\:1F64C", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f64c), "\\:1F64C"));
        sEmojiMap.put("\\:1F64D", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f64d), "\\:1F64D"));
        sEmojiMap.put("\\:1F64E", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f64e), "\\:1F64E"));
        sEmojiMap.put("\\:1F64F", new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_1f64f), "\\:1F64F"));

        sEmojiMap.put("\\:2754", sFallback = new Emoji(ContextCompat.getDrawable(context, R.drawable.ic_2754), "\\:2754"));
    }

    public static Emoji getEmojiByTag(@Nullable String tag) {
        if (sEmojiMap == null) {
            throw new IllegalStateException("Emoji not initialized!");
        }

        Emoji emoji = sEmojiMap.get(tag);
        if (emoji == null) {
            emoji = sFallback;
        }
        return emoji;
    }

    private static HashMap<String, Emoji> sEmojiMap;

    private static Emoji sFallback;

}
