package com.mate.share;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.mate.utils.PackageUtils;

import static com.mate.utils.Constants.FACEBOOK_BROWSER_SHARE_URL;
import static com.mate.utils.Constants.FACEBOOK_REQUEST_CODE;
import static com.mate.utils.Constants.PACKAGE_NAME_FACEBOOK;
import static com.mate.utils.Constants.SHARE_MIME_DATA_TYPE;

/**
 * create by fushenghua
 */
public class FacebookAction implements Action {


    @Override
    public void share(Context context, String... text) {
        Intent intent = null;
        boolean isFacebookInstalled = PackageUtils.isInstallPackage(context, PACKAGE_NAME_FACEBOOK);

        if (isFacebookInstalled) {
            intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setType(SHARE_MIME_DATA_TYPE);
            intent.putExtra(Intent.EXTRA_TEXT, text[0]);
            intent.setPackage(PACKAGE_NAME_FACEBOOK);
        }

        if (!isFacebookInstalled || !PackageUtils.startAppForResult(context, intent,
                FACEBOOK_REQUEST_CODE)) {
            String browserUrl = FACEBOOK_BROWSER_SHARE_URL + text[0];
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(browserUrl));
            PackageUtils.startApp(context, intent);
        }
    }
}
