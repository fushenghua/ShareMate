package com.mate.share;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.mate.utils.PackageUtils;

import static com.mate.utils.Constants.PACKAGE_NAME_TWITTER;
import static com.mate.utils.Constants.SHARE_MIME_DATA_TYPE;
import static com.mate.utils.Constants.TWITTER_BROWSER_SHARE_URL;
import static com.mate.utils.Constants.TWITTER_REQUEST_CODE;

/**
 * create by fushenghua
 */
public class TwitterAction implements Action {

    @Override
    public void share(Context context, String... text) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text[0]);
        intent.setType(SHARE_MIME_DATA_TYPE);

        if (!PackageUtils.injectMatchIntent(context, intent, PACKAGE_NAME_TWITTER) || !PackageUtils.startAppForResult(context, intent,
                TWITTER_REQUEST_CODE)) {
            String browserUrl = TWITTER_BROWSER_SHARE_URL + text[0];
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(browserUrl));
            PackageUtils.startApp(context, intent);
        }
    }

}
