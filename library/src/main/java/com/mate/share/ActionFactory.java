package com.mate.share;

/**
 * create by fushenghua
 */
public class ActionFactory {

    public static Action getAction(int shareMedia) {

        switch (shareMedia) {
            case ShareAction.SHARE_MEDIA.FACEBOOK:
                return new FacebookAction();

            case ShareAction.SHARE_MEDIA.WHATSAPP:
                return new WhatsAppAction();

            case ShareAction.SHARE_MEDIA.TWITTER:
                return new TwitterAction();

            case ShareAction.SHARE_MEDIA.FACEBOOK_MESSAGER:
                return new MessagerAction();

            case ShareAction.SHARE_MEDIA.MORE:
                return new MoreSystemAction();
        }

        return null;
    }
}
