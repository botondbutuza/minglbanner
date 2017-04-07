package uk.co.botondbutuza.minglbanner;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by brotond on 07/04/2017.
 */

public class MinglBannerImpl extends FrameLayout implements MinglBanner {
    private static final int ANIM_DURATION = 400;
    private View left, right, textContainer;
    private TextView text;

    private int textX, textY;
    private float textRadius;

    public MinglBannerImpl(@NonNull Context context) {
        this(context, null);
    }

    public MinglBannerImpl(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MinglBannerImpl(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.mingl_banner, this);
        textContainer = findViewById(R.id.text_container);
        text = (TextView) findViewById(R.id.text);
        right = findViewById(R.id.right);
        left = findViewById(R.id.left);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        left.setTranslationX(-getWidth() / 2);
        right.setTranslationX(getWidth());

        textX = textContainer.getWidth() / 2;
        textY = textContainer.getHeight() / 2;
        textRadius = (float) Math.hypot(textX, textY);
    }

    @Override
    public MinglBannerImpl withBackgroundColour(@ColorInt int colour) {
        left.setBackgroundColor(colour);
        right.setBackgroundColor(colour);
        return this;
    }

    @Override
    public MinglBanner withTextColour(@ColorInt int colour) {
        text.setTextColor(colour);
        return this;
    }

    @Override
    public MinglBannerImpl withText(String txt) {
        text.setText(txt);
        return this;
    }

    @Override
    public void show() {
        left.animate().translationX(0).setDuration(ANIM_DURATION).start();
        right.animate().translationX(0).setDuration(ANIM_DURATION).start();
        text.getHandler().postDelayed(this::showText, ANIM_DURATION / 4 * 3);
    }

    @Override
    public void dismiss() {
        left.animate().translationX(-getWidth() / 2).setDuration(ANIM_DURATION).start();
        right.animate().translationX(getWidth()).setDuration(ANIM_DURATION).start();
        dismissText();
    }


    private void showText() {
        Animator anim = ViewAnimationUtils.createCircularReveal(textContainer, textX, textY, 0, textRadius);
        anim.setDuration(ANIM_DURATION / 2);

        textContainer.setVisibility(VISIBLE);
        anim.start();
    }

    private void dismissText() {
        Animator anim = ViewAnimationUtils.createCircularReveal(textContainer, textX, textY, textRadius, 0);
        anim.setDuration(ANIM_DURATION / 2);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                textContainer.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();
    }
}
