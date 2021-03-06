package com.mate.share;

import android.content.Context;
import android.content.Intent;

/**
 * create by fushenghua
 */
public class MoreSystemAction implements Action {

    @Override
    public void share(Context context, String... text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, text[1]);
        intent.putExtra(Intent.EXTRA_TEXT, text[0]);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, text[1]));
    }
}
