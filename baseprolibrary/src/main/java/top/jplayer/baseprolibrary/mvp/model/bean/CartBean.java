package top.jplayer.baseprolibrary.mvp.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Obl on 2018/3/20.
 * top.jplayer.baseprolibrary.mvp.model.bean
 */

public class CartBean extends BaseBean implements Parcelable {


    public ArrayList<AttrsBean> attrs;

    protected CartBean(Parcel in) {
        attrs = in.createTypedArrayList(AttrsBean.CREATOR);
    }

    public static final Creator<CartBean> CREATOR = new Creator<CartBean>() {
        @Override
        public CartBean createFromParcel(Parcel in) {
            return new CartBean(in);
        }

        @Override
        public CartBean[] newArray(int size) {
            return new CartBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(attrs);
    }

    public static class AttrsBean implements Parcelable {
        /**
         * type_name : 套餐
         * type_id : 8
         * attrInfo : [{"attr_value":"标配","attr_id":15}]
         */

        public String type_name;
        public int type_id;
        public ArrayList<AttrInfoBean> attrInfo;

        protected AttrsBean(Parcel in) {
            type_name = in.readString();
            type_id = in.readInt();
            attrInfo = in.createTypedArrayList(AttrInfoBean.CREATOR);
        }

        public static final Creator<AttrsBean> CREATOR = new Creator<AttrsBean>() {
            @Override
            public AttrsBean createFromParcel(Parcel in) {
                return new AttrsBean(in);
            }

            @Override
            public AttrsBean[] newArray(int size) {
                return new AttrsBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(type_name);
            dest.writeInt(type_id);
            dest.writeTypedList(attrInfo);
        }

        public static class AttrInfoBean implements Parcelable {
            /**
             * attr_value : 标配
             * attr_id : 15
             */

            public String attr_value;
            public int attr_id;

            protected AttrInfoBean(Parcel in) {
                attr_value = in.readString();
                attr_id = in.readInt();
            }

            public static final Creator<AttrInfoBean> CREATOR = new Creator<AttrInfoBean>() {
                @Override
                public AttrInfoBean createFromParcel(Parcel in) {
                    return new AttrInfoBean(in);
                }

                @Override
                public AttrInfoBean[] newArray(int size) {
                    return new AttrInfoBean[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(attr_value);
                dest.writeInt(attr_id);
            }
        }
    }
}
