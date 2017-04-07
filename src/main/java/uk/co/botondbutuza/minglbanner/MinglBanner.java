package uk.co.botondbutuza.minglbanner;

import android.support.annotation.ColorInt;

/**
 * Created by brotond on 07/04/2017.
 */

public interface MinglBanner {

    MinglBanner withColour(@ColorInt int colour);

    MinglBanner withText(String text);

    void show();

    void dismiss();
}
