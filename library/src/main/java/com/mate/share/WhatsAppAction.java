package com.mate.share;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.mate.utils.PackageUtils;

import static com.mate.utils.Constants.SHARE_MIME_DATA_TYPE;
import static com.mate.utils.Constants.BROWSER_SHARE_URL_WHATSAPP;
import static com.mate.utils.Constants.PACKAGE_NAME_WHATSAPP;
import static com.mate.utils.Constants.WHATSAPP_REQUEST_CODE;

/**
 * create by fushenghua
 */
public class WhatsAppAction implements Action {

    @Override
    public void share(Context context, String... text) {

        Intent intent = null;
        boolean isWhatsAppInstalled = PackageUtils.isInstallPackage(context, PACKAGE_NAME_WHATSAPP);

        if (isWhatsAppInstalled) {
            intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setType(SHARE_MIME_DATA_TYPE);
            intent.putExtra(Intent.EXTRA_TEXT, text[0]);
            intent.setPackage(PACKAGE_NAME_WHATSAPP);
        }

        if (!isWhatsAppInstalled || !PackageUtils.startAppForResult(context, intent,
                WHATSAPP_REQUEST_CODE)) {
            String browserUrl = BROWSER_SHARE_URL_WHATSAPP + text[0];
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(browserUrl));
            PackageUtils.startApp(context, intent);
        }
    }

}
