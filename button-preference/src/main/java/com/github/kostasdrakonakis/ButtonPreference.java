package com.github.kostasdrakonakis;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.preference.Preference;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.kostasdrakonakis.buttonpreference.R;

public class ButtonPreference extends Preference {

    private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";

    private int mGravity;
    private int mColor;
    private int mPadding;
    private int mDrawable;
    private int mButtonBackgroundColor;
    private int mVisibility;
    private boolean mAllCaps;

    private View.OnClickListener mClickListener;

    @SuppressWarnings("unused")
    public ButtonPreference(Context context) {
        super(context);
        init(context, null);
    }

    @SuppressWarnings("unused")
    public ButtonPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @SuppressWarnings("unused")
    public ButtonPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        LinearLayout container = view.findViewById(R.id.button_preference_container);
        container.setBackgroundColor(mColor);
        container.setPadding(mPadding, mPadding, mPadding, mPadding);

        Button button = view.findViewById(R.id.button_preference);
        if (mDrawable != 0) {
            button.setBackgroundResource(mDrawable);
        }
        if (mButtonBackgroundColor != 0) {
            button.setBackgroundColor(mButtonBackgroundColor);
        }
        button.setText(getTitle());
        button.setAllCaps(mAllCaps);
        button.setVisibility(mVisibility);
        setButtonGravity(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onClick(v);
                }
            }
        });
    }

    public void setOnClickListener(View.OnClickListener listener) {
        mClickListener = listener;
    }

    public void setButtonBackgroundColor(int color) {
        mButtonBackgroundColor = ContextCompat.getColor(getContext(), color);
    }

    public void setVisibility(int visibility) {
        mVisibility = visibility;
    }

    private void init(Context context, AttributeSet attrs) {
        setLayoutResource(R.layout.preference_button);
        if (attrs == null) return;

        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ButtonPreference);
        final int color = array.
                getColor(R.styleable.ButtonPreference_preferenceLayoutColor, Color.WHITE);
        final int padding = array.
                getDimensionPixelOffset(R.styleable.ButtonPreference_preferenceLayoutPadding,
                        context.getResources().getDimensionPixelOffset(
                                R.dimen.preference_button_container_default_padding));
        final int drawable = array.
                getResourceId(R.styleable.ButtonPreference_preferenceBackground, 0);

        final int buttonBackgroundColor = array.
                getColor(R.styleable.ButtonPreference_preferenceBackgroundColor, 0);

        mAllCaps = array.
                getBoolean(R.styleable.ButtonPreference_preferenceAllCaps, true);

        mVisibility = array.getBoolean(R.styleable.ButtonPreference_preferenceVisible, true)
                ? View.VISIBLE : View.GONE;

        if (color != 0) {
            mColor = color;
        }

        if (padding != 0) {
            mPadding = padding;
        }

        if (drawable != 0) {
            mDrawable = drawable;
        }

        if (buttonBackgroundColor != 0) {
            mButtonBackgroundColor = buttonBackgroundColor;
        }

        array.recycle();

        mGravity = attrs.getAttributeIntValue(NAMESPACE, "layout_gravity", Gravity.START);
    }

    private void setButtonGravity(Button button) {
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        linearLayoutParams.gravity = mGravity;
        button.setLayoutParams(linearLayoutParams);
    }
}

