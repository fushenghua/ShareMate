package com.mate.share;

import android.content.Context;
import android.text.TextUtils;

/**
 * create by fushenghua
 */
public class ShareAction {

    private Context mContext;

    private String[] mText = new String[2];

    private Action mAction;

    public ShareAction(Context context) {
        this.mContext = context;
    }

    public ShareAction withPlatform(int share_media) {
        this.mAction = ActionFactory.getAction(share_media);
        return this;
    }

    public ShareAction withText(String text) {
        this.mText[0] = text;
        return this;
    }

    public ShareAction withTitle(String title) {
        this.mText[1] = title;
        return this;
    }

    public void share() {
        if (mAction != null && mContext != null && mText.length != 0) {
            mAction.share(mContext, mText);
        } else {
            throw new RuntimeException("params is NULL");
        }
    }

    public interface SHARE_MEDIA {

        int FACEBOOK = 1000;

        int FACEBOOK_MESSAGER = 1001;

        int TWITTER = 1002;

        int WHATSAPP = 1003;

        int MORE = 2000;
    }

}
