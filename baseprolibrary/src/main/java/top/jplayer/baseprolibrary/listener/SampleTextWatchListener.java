package top.jplayer.baseprolibrary.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import top.jplayer.baseprolibrary.utils.StringUtils;

/**
 * Created by Obl on 2018/3/23.
 * top.jplayer.baseprolibrary.listener
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class SampleTextWatchListener implements TextWatcher {
    public EditText mEditText;
    public int maxLen;
    public String msg = "超出字数范围";

    public SampleTextWatchListener(EditText edit, int maxLen) {
        mEditText = edit;
        this.maxLen = maxLen;
    }

    public SampleTextWatchListener(EditText edit, int maxLen, String msg) {
        mEditText = edit;
        this.maxLen = maxLen;
        this.msg = msg;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        StringUtils.init().tipEditTextLength(mEditText, s, maxLen, msg);
    }
}
