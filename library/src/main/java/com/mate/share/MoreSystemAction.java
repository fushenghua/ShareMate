package com.mate.share;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.mate.utils.PackageUtils;

import static com.mate.utils.Constants.FACEBOOK_BROWSER_SHARE_URL;
import static com.mate.utils.Constants.MESSAGER_REQUEST_CODE;
import static com.mate.utils.Constants.PACKAGE_NAME_MESSAGER;
import static com.mate.utils.Constants.SHARE_MIME_DATA_TYPE;

/**
 * create by fushenghua
 */
public class MoreSystemAction implements Action {

    @Override
    public void share(Context context, String... text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, text[1]);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, text[1]));
    }
}
