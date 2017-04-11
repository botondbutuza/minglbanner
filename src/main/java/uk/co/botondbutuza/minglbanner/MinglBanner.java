package uk.co.botondbutuza.minglbanner;

import android.support.annotation.ColorInt;
import android.view.View;

/**
 * Created by brotond on 07/04/2017.
 */

public interface MinglBanner {

    MinglBanner withBackgroundColour(@ColorInt int colour);

    MinglBanner withTextColour(@ColorInt int colour);

    MinglBanner withText(String text);

    MinglBanner withOnClick(View.OnClickListener listener);

    MinglBanner withAction(Action action);

    void show();

    void dismiss();

    void info(String message, Action action);


    interface Action extends View.OnClickListener {

        String getText();
    }
}
