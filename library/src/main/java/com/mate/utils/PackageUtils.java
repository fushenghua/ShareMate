package com.mate.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

/**
 * create by fushenghua
 */
public class PackageUtils {

    public static boolean startApp(Context context, Intent intent) {
        if (intent == null) {
            return false;
        }
        boolean result = false;
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
            result = true;
        }
        return result;
    }

    public static boolean isInstallPackage(Context context, String packageName) {

        List<PackageInfo> pkgInfoList = getInstalledPackagesNoThrow(context.getPackageManager(), 0);
        if (pkgInfoList != null) {
            for (int i = 0; i < pkgInfoList.size(); i++) {
                String pn = pkgInfoList.get(i).packageName;
                if (packageName.equals(pn)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static synchronized List<PackageInfo> getInstalledPackagesNoThrow(PackageManager pm, int flags) {
        List<PackageInfo> pkgList = null;
        try {
            pkgList = pm.getInstalledPackages(flags);
        } catch (Throwable ignore) {
        }
        return pkgList;
    }

    public static boolean startAppForResult(Context context, Intent intent, int requestCode) {
        if (intent == null) {
            return false;
        }
        boolean result = false;
        if (context instanceof Activity) {
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                ((Activity) context).startActivityForResult(intent, requestCode);
                result = true;
            }
        }
        return result;
    }

    public static boolean injectMatchIntent(Context context, Intent intent, String packageName) {
        if (null == context) return false;

        PackageManager packManager = context.getPackageManager();
        List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(intent, PackageManager
                .MATCH_DEFAULT_ONLY);

        boolean resolved = false;
        for (ResolveInfo resolveInfo : resolvedInfoList) {
            if (resolveInfo.activityInfo.packageName.startsWith(packageName)) {
                intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                resolved = true;
                break;
            }
        }

        return resolved;
    }
}
