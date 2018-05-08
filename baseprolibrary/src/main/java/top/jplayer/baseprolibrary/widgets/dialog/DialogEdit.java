package top.jplayer.baseprolibrary.widgets.dialog;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import top.jplayer.baseprolibrary.R;

/**
 * Created by Obl on 2018/3/15.
 * top.jplayer.baseprolibrary.widgets.dialog
 */

public class DialogEdit extends BaseCustomDialog {

    public DialogEdit(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.btnCancel).setOnClickListener(v -> cancel());
        EditText editPassword = view.findViewById(R.id.editPassword);
        view.findViewById(R.id.ivCancel).setOnClickListener(v -> editPassword.setText(""));
        ImageButton ivShow = view.findViewById(R.id.ivShow);
        ivShow.setOnClickListener(v -> {
            ivShow.setSelected(!ivShow.isSelected());
            editPassword.setInputType(ivShow.isSelected() ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            editPassword.setSelection(editPassword.getText().length());
        });
    }
    @Override
    public int setSoftInputState() {
        return WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;
    }
    @Override
    public int setWidth(int i) {
        return super.setWidth(8);
    }

    @Override
    public int setAnim() {
        return R.style.AnimFade;
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_edit;
    }
}
