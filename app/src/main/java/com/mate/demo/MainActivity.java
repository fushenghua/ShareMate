package com.mate.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mate.dialog.MenuItem;
import com.mate.dialog.OnItemClickListener;
import com.mate.dialog.ShareDialog;
import com.mate.share.ShareAction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ShareDialog shareDialog = new ShareDialog(MainActivity.this);
                shareDialog.title(R.string.app_name);
                shareDialog.layout(ShareDialog.GRID);
                shareDialog.orientation(ShareDialog.GRID);
                shareDialog.inflateMenu(R.menu.menu_share, new OnItemClickListener() {
                    @Override
                    public void onClick(MenuItem menuItem) {
                        shareDialog.dismiss();
                        switch (menuItem.getId()) {
                            case R.id.facebbook:
                                new ShareAction(MainActivity.this)
                                        .withPlatform(ShareAction.SHARE_MEDIA.FACEBOOK)
                                        .withText("https://github.com/fushenghua/ShareMate.git")
                                        .share();
                                break;

                            case R.id.twitter:
                                new ShareAction(MainActivity.this)
                                        .withPlatform(ShareAction.SHARE_MEDIA.TWITTER)
                                        .withText("https://github.com/fushenghua/ShareMate.git")
                                        .share();
                                break;
                            case R.id.whatsapp:
                                new ShareAction(MainActivity.this)
                                        .withPlatform(ShareAction.SHARE_MEDIA.WHATSAPP)
                                        .withText("https://github.com/fushenghua/ShareMate.git")
                                        .share();
                                break;
                            case R.id.messager:
                                new ShareAction(MainActivity.this)
                                        .withPlatform(ShareAction.SHARE_MEDIA.FACEBOOK_MESSAGER)
                                        .withText("https://github.com/fushenghua/ShareMate.git")
                                        .share();
                                break;
                            case R.id.copy_link:
                                new ShareAction(MainActivity.this)
                                        .withPlatform(ShareAction.SHARE_MEDIA.COPY_LINK)
                                        .withText("https://github.com/fushenghua/ShareMate.git")
                                        .share();
                                break;
                            case R.id.more:
                                new ShareAction(MainActivity.this)
                                        .withPlatform(ShareAction.SHARE_MEDIA.MORE)
                                        .withTitle("Share to")
                                        .withText("https://github.com/fushenghua/ShareMate.git")
                                        .share();
                                break;
                        }
                    }
                });
                shareDialog.show();
            }
        });
    }
}
