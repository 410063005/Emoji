package emoji.cm.hust.edu.cn.emoji;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ImageSpan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * Init Emoji. Caution: SHOULD BE CALLED ONLY ONCE!
     */
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

    public static void setEmoji(Spannable spannable) {
        int textLength = spannable.length();
        ImageSpan[] imageSpans = spannable.getSpans(0, textLength, ImageSpan.class);
        for (ImageSpan is : imageSpans) {
            spannable.removeSpan(is);
        }

        Pattern p = Pattern.compile("(\\\\:[0-9a-fA-F]{4,5}).*?");
        Matcher m = p.matcher(spannable);
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
            spannable.setSpan(span, from, to, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
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
