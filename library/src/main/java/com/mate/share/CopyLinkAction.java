package com.mate.share;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * create by fushenghua
 */
public class CopyLinkAction implements Action {


    @Override
    public void share(Context context, String... text) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("Label", text[0]);
        cm.setPrimaryClip(mClipData);
    }
}
