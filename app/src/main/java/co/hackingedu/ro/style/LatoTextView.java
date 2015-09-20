package co.hackingedu.ro.style;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Spicycurryman on 9/15/15.
 */
public class LatoTextView extends TextView {

    public LatoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Regular.ttf"));
    }
}