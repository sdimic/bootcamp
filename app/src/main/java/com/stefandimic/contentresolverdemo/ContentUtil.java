package com.stefandimic.contentresolverdemo;

import android.net.Uri;

public class ContentUtil {
    public static final int URL_LOADER_ALL = 0;
    public static final int URL_LOADER_SPECIFIC = 1;

    public static final Uri CONTENT_URI_ALL =
            Uri.parse("content://com.stefandimic.provider/texts");
    public static final Uri CONTENT_URI_SPECIFIC =
            Uri.parse("content://com.stefandimic.provider/texts/#");
}
