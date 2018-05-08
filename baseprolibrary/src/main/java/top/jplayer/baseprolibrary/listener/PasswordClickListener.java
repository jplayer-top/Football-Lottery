package top.jplayer.baseprolibrary.listener;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Obl on 2018/3/23.
 * top.jplayer.baseprolibrary.listener
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class PasswordClickListener implements View.OnClickListener {
    public EditText mEditText;
    public MethodListener mListener;

    public PasswordClickListener(EditText editText, MethodListener listener) {
        mEditText = editText;
        mListener = listener;
    }

    public PasswordClickListener(EditText editText) {
        mEditText = editText;
    }

    public interface MethodListener {
        void setMethodSel();

        void setMethodUnSel();
    }

    @Override
    public void onClick(View v) {
        v.setSelected(!v.isSelected());
        if (mListener != null) {
            if (v.isSelected()) {
                mListener.setMethodSel();
            } else {
                mListener.setMethodUnSel();
            }
        }
        mEditText.setInputType(v.isSelected() ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mEditText.setSelection(mEditText.getText().length());
    }
}
