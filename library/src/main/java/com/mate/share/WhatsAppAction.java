package com.mate.share;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.mate.utils.PackageUtils;

import static com.mate.utils.Constants.SHARE_MIME_DATA_TYPE;
import static com.mate.utils.Constants.WHATSAPP_BROWSER_SHARE_URL;
import static com.mate.utils.Constants.WHATSAPP_PACKAGE_NAME;
import static com.mate.utils.Constants.WHATSAPP_REQUEST_CODE;

/**
 * create by fushenghua
 */
public class WhatsAppAction implements Action {

    @Override
    public void share(Context context, String text) {

        Intent intent = null;
        boolean isWhatsAppInstalled = PackageUtils.isInstallPackage(context, WHATSAPP_PACKAGE_NAME);

        if (isWhatsAppInstalled) {
            intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setType(SHARE_MIME_DATA_TYPE);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            intent.setPackage(WHATSAPP_PACKAGE_NAME);
        }

        if (!isWhatsAppInstalled || !PackageUtils.startAppForResult(context, intent,
                WHATSAPP_REQUEST_CODE)) {
            String browserUrl = WHATSAPP_BROWSER_SHARE_URL + text;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(browserUrl));
            PackageUtils.startApp(context, intent);
        }
    }

}
