package co.hackingedu.ro.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.hackingedu.ro.R;
/**
 * Created by Spicycurryman on 9/14/15.
 */
public class MapImage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.whole_image, container, false);
    }



}
