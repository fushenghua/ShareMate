package com.mate.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mate.share.ShareAction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new BottomDialog(MainActivity.this)
                        .title(R.string.app_name)
                        .orientation(BottomDialog.HORIZONTAL)
                        .inflateMenu(R.menu.menu_share, new OnItemClickListener() {
                            @Override
                            public void click(Item item) {

                                switch (item.getId()) {
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
                                    case R.id.more:
                                        new ShareAction(MainActivity.this)
                                                .withPlatform(ShareAction.SHARE_MEDIA.MORE)
                                                .withTitle("Share to")
                                                .withText("https://github.com/fushenghua/ShareMate.git")
                                                .share();
                                        break;
                                }

                            }
                        }).show();
            }
        });
    }
}
