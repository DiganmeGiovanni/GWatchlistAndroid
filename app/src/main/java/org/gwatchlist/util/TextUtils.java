package org.gwatchlist.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * Created by giovanni on 5/03/17.
 */
public class TextUtils {

    private static final SimpleDateFormat releaseDateFormatter =
            new SimpleDateFormat("yyyy, MMMM dd", Locale.getDefault());

    public static String formatRelaseDate(Date releaseDate) {
        return releaseDateFormatter.format(releaseDate);
    }
}
