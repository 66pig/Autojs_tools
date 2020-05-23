package com.liuliuzhu.autojs.annotate;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({ShareContentType.TEXT, ShareContentType.IMAGE, ShareContentType.AUDIO, ShareContentType.VIDEO, ShareContentType.FILE})
@Retention(RetentionPolicy.SOURCE)
public @interface ShareContentType {
    /**
     * Share Text
     */
    final String TEXT = "text/plain";

    /**
     * Share Image
     */
    final String IMAGE = "image/*";

    /**
     * Share Audio
     */
    final String AUDIO = "audio/*";

    /**
     * Share Video
     */
    final String VIDEO = "video/*";

    /**
     * Share File
     */
    final String FILE = "*/*";
}