package com.motanad.motanad.custome;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.motanad.motanad.AppInitialization;
import com.motanad.motanad.R;


/**
 * Created by Android1 on 1/9/2016.
 */

public class EditTextViewRegular extends AppCompatEditText {


    public EditTextViewRegular(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.setTypeface(AppInitialization.getFontRegular());
        this.setBackground(context.getResources().getDrawable(R.drawable.et_bg));
        this.setPadding((int)context.getResources().getDimension(R.dimen.dp_10),0,(int)context.getResources().getDimension(R.dimen.dp_10),0);
    }


    public EditTextViewRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EditTextViewRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}