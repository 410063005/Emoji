package emoji.cm.hust.edu.cn.emoji;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import timber.log.Timber;

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
        if (sEmojiList == null) {
            sEmojiList = new ArrayList<>();
        }

        String[] unicode = context.getResources().getStringArray(R.array.Unicode);
        TypedArray unicodeDrawableArray = context.getResources().obtainTypedArray(R.array.UnicodeDrawable);

        Timber.d("unicode len is %d, drawable len is %d", unicode.length, unicodeDrawableArray.length());
        Timber.d("drawable id is %d, drawable id is %d", R.drawable.ic_1f601, unicodeDrawableArray.getResourceId(0, R.drawable.ic_2754));

        final int len = unicode.length;
        for (int i = 0; i < len; i++) {
            int drawableId = unicodeDrawableArray.getResourceId(i, R.drawable.ic_2754);
            Emoji emoji = new Emoji(ContextCompat.getDrawable(context, drawableId), unicode[i]);
            sEmojiMap.put(unicode[i], emoji);
            sEmojiList.add(emoji);
        }
        unicodeDrawableArray.recycle();

        sFallback = sEmojiMap.get("\\\\:2754");
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

    public static List<Emoji> getEmojiList() {
        if (sEmojiList == null) {
            throw new IllegalStateException("Emoji not initialized!");
        }

        return new ArrayList<>(sEmojiList);
    }

    private static HashMap<String, Emoji> sEmojiMap;

    private static List<Emoji> sEmojiList;

    private static Emoji sFallback;

}
