package com.mate.share;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.mate.utils.PackageUtils;

import static com.mate.utils.Constants.BROWSER_SHARE_URL_FACEBOOK;
import static com.mate.utils.Constants.MESSAGER_REQUEST_CODE;
import static com.mate.utils.Constants.PACKAGE_NAME_MESSAGER;
import static com.mate.utils.Constants.SHARE_MIME_DATA_TYPE;

/**
 * create by fushenghua
 */
public class MessagerAction implements Action {

    @Override
    public void share(Context context, String... text) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text[0]);
        intent.setType(SHARE_MIME_DATA_TYPE);

        if (!PackageUtils.injectMatchIntent(context, intent, PACKAGE_NAME_MESSAGER) || !PackageUtils.startAppForResult(context, intent,
                MESSAGER_REQUEST_CODE)) {
            String browserUrl = BROWSER_SHARE_URL_FACEBOOK + text[0];
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(browserUrl));
            PackageUtils.startApp(context, intent);
        }
    }
}
