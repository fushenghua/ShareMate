package com.mate.share;

import android.content.Context;
import android.text.TextUtils;

/**
 * create by fushenghua
 */
public class ShareAction {

    private Context mContext;

    private String mText;

    private Action mAction;

//    private int mShareMedia;

    public ShareAction(Context context) {
        this.mContext = context;
    }

    public ShareAction withPlatform(int share_media) {
//        this.mShareMedia = share_media;
        this.mAction = ActionFactory.getAction(share_media);
        return this;
    }

    public ShareAction withText(String text) {
        this.mText = text;
        return this;
    }

    public void share() {
        if (mAction != null && mContext != null && !TextUtils.isEmpty(mText)) {
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
    }

}
