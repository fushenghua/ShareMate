package com.mate.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mate.share.R;
import com.mate.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * create by fushenghua
 */
public class ShareDialog {

    public static final int HORIZONTAL = OrientationHelper.HORIZONTAL;
    public static final int VERTICAL = OrientationHelper.VERTICAL;

    public static final int LINEAR = 0;
    public static final int GRID = 1;

    private CustomDialog customDialog;

    public ShareDialog(Context context) {
        customDialog = new CustomDialog(context);
    }

    public ShareDialog title(String title) {
        customDialog.title(title);
        return this;
    }

    public ShareDialog title(int title) {
        customDialog.title(title);
        return this;
    }

    public ShareDialog background(int res) {
        customDialog.background(res);
        return this;
    }

    public ShareDialog inflateMenu(int menu, OnItemClickListener onItemClickListener) {
        customDialog.inflateMenu(menu, onItemClickListener);
        return this;
    }

    public ShareDialog layout(int layout) {
        customDialog.layout(layout);
        return this;
    }

    public ShareDialog orientation(int orientation) {
        customDialog.orientation(orientation);
        return this;
    }

    public ShareDialog addItems(List<MenuItem> menuItems, OnItemClickListener onItemClickListener) {
        customDialog.addItems(menuItems, onItemClickListener);
        return this;
    }

    /**
     * @deprecated
     */
    public ShareDialog itemClick(OnItemClickListener listener) {
        customDialog.setItemClick(listener);
        return this;
    }

    public void show() {
        customDialog.show();
    }

    private final class CustomDialog extends Dialog {
        private LinearLayout background;
        private LinearLayout container;
        private TextView titleView;

        private DialogAdapter adapter;

        private int padding;
        private int topPadding;
        private int leftPadding;
        private int topIcon;
        private int leftIcon;

        private int orientation;
        private int layout;

        CustomDialog(Context context) {
            super(context, R.style.ShareDialog);

            init();
        }

        private void init() {
            padding = getContext().getResources().getDimensionPixelSize(R.dimen.app_normal_margin);
            topPadding = getContext().getResources().getDimensionPixelSize(R.dimen.app_tiny_margin);
            leftPadding = getContext().getResources().getDimensionPixelSize(R.dimen.app_normal_margin);
            topIcon = getContext().getResources().getDimensionPixelSize(R.dimen.bottom_dialog_top_icon);
            leftIcon = getContext().getResources().getDimensionPixelSize(R.dimen.bottom_dialog_left_icon);

            setContentView(R.layout.share_dialog);
            setCancelable(true);
            setCanceledOnTouchOutside(true);
            getWindow().setGravity(Gravity.BOTTOM);
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            background = (LinearLayout) findViewById(R.id.background);
            titleView = (TextView) findViewById(R.id.title);
            container = (LinearLayout) findViewById(R.id.container);
            findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }

        void addItems(List<MenuItem> menuItems, OnItemClickListener onItemClickListener) {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            RecyclerView.LayoutManager manager;

            adapter = new DialogAdapter(menuItems, layout, orientation);
            adapter.setItemClick(onItemClickListener);

            if (layout == LINEAR)
                manager = new LinearLayoutManager(getContext(), orientation, false);
            else if (layout == GRID)
                manager = new GridLayoutManager(getContext(), 5, orientation, false);
            else manager = new LinearLayoutManager(getContext(), orientation, false);

            RecyclerView recyclerView = new RecyclerView(getContext());
            recyclerView.setLayoutParams(params);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);

            container.addView(recyclerView);
        }

        public void title(int title) {
            title(getContext().getString(title));
        }

        public void title(String title) {
            titleView.setText(title);
            titleView.setVisibility(View.VISIBLE);
        }

        public void layout(int layout) {
            this.layout = layout;
            if (adapter != null) adapter.setLayout(layout);
        }

        public void orientation(int orientation) {
            this.orientation = orientation;
            if (adapter != null) adapter.setOrientation(orientation);
        }

        public void background(int res) {
            background.setBackgroundResource(res);
        }

        void inflateMenu(int menu, OnItemClickListener onItemClickListener) {
            MenuInflater menuInflater = new MenuInflater(getContext());
            @SuppressLint("RestrictedApi") MenuBuilder menuBuilder = new MenuBuilder(getContext());
            menuInflater.inflate(menu, menuBuilder);
            List<MenuItem> menuItems = new ArrayList<>();
            for (int i = 0; i < menuBuilder.size(); i++) {
                android.view.MenuItem menuMenuItem = menuBuilder.getItem(i);
                menuItems.add(new MenuItem(menuMenuItem.getItemId(), menuMenuItem.getTitle().toString(), menuMenuItem.getIcon()));
            }
            addItems(menuItems, onItemClickListener);
        }

        void setItemClick(OnItemClickListener onItemClickListener) {
            adapter.setItemClick(onItemClickListener);
        }

        /**
         * recycler view adapter, provide HORIZONTAL and VERTICAL item style
         */
        private class DialogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

            private List<MenuItem> mMenuItems = Collections.emptyList();
            private OnItemClickListener itemClickListener;

            private int orientation;
            private int layout;

            DialogAdapter(List<MenuItem> mMenuItems, int layout, int orientation) {
                setList(mMenuItems);
                this.layout = layout;
                this.orientation = orientation;
            }

            private void setList(List<MenuItem> menuItems) {
                mMenuItems = menuItems == null ? new ArrayList<MenuItem>() : menuItems;
            }

            void setItemClick(OnItemClickListener onItemClickListener) {
                this.itemClickListener = onItemClickListener;
            }

            public void setOrientation(int orientation) {
                this.orientation = orientation;
                notifyDataSetChanged();
            }

            public void setLayout(int layout) {
                this.layout = layout;
                notifyDataSetChanged();
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if (layout == GRID)
                    return new TopHolder(new LinearLayout(parent.getContext()));
                else if (orientation == HORIZONTAL)
                    return new TopHolder(new LinearLayout(parent.getContext()));
                else return new LeftHolder(new LinearLayout(parent.getContext()));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                final MenuItem menuItem = mMenuItems.get(position);

                TopHolder topHolder;
                LeftHolder leftHolder;

                if (layout == GRID) {
                    topHolder = (TopHolder) holder;

                    topHolder.item.setText(menuItem.getTitle());
                    topHolder.item.setCompoundDrawablesWithIntrinsicBounds(null, topHolder.icon(menuItem.getIcon()), null, null);
                    topHolder.item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (itemClickListener != null) itemClickListener.click(menuItem);
                        }
                    });
                } else if (orientation == HORIZONTAL) {
                    topHolder = (TopHolder) holder;

                    topHolder.item.setText(menuItem.getTitle());
                    topHolder.item.setCompoundDrawablesWithIntrinsicBounds(null, topHolder.icon(menuItem.getIcon()), null, null);
                    topHolder.item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (itemClickListener != null) itemClickListener.click(menuItem);
                        }
                    });
                } else {
                    leftHolder = (LeftHolder) holder;

                    leftHolder.item.setText(menuItem.getTitle());
                    leftHolder.item.setCompoundDrawablesWithIntrinsicBounds(leftHolder.icon(menuItem.getIcon()), null, null, null);
                    leftHolder.item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (itemClickListener != null) itemClickListener.click(menuItem);
                        }
                    });
                }
            }

            @Override
            public int getItemCount() {
                return mMenuItems.size();
            }

            /**
             * horizontal item adapter
             */
            class TopHolder extends RecyclerView.ViewHolder {
                private TextView item;

                TopHolder(View view) {
                    super(view);

                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.width = Utils.getScreenWidth(getContext()) / 5;

                    item = new TextView(view.getContext());
                    item.setLayoutParams(params);
                    item.setMaxLines(1);
                    item.setEllipsize(TextUtils.TruncateAt.END);
                    item.setGravity(Gravity.CENTER);
                    item.setTextColor(ContextCompat.getColor(view.getContext(), R.color.gray_font_dark));
                    item.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimension(R.dimen.font_small));
                    item.setCompoundDrawablePadding(topPadding);
                    item.setPadding(0, padding, 0, padding);

                    TypedValue typedValue = new TypedValue();
                    view.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
                    item.setBackgroundResource(typedValue.resourceId);

                    ((LinearLayout) view).addView(item);
                }

                private Drawable icon(Drawable drawable) {
                    if (drawable != null) {
                        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                        @SuppressWarnings("SuspiciousNameCombination") Drawable resizeIcon = new BitmapDrawable(getContext().getResources(), Bitmap.createScaledBitmap(bitmap, topIcon, topIcon, true));
                        Drawable.ConstantState state = resizeIcon.getConstantState();
                        resizeIcon = DrawableCompat.wrap(state == null ? resizeIcon : state.newDrawable().mutate());
                        return resizeIcon;
                    }
                    return null;
                }
            }

            class LeftHolder extends RecyclerView.ViewHolder {
                private TextView item;

                LeftHolder(View view) {
                    super(view);

                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    view.setLayoutParams(params);
                    item = new TextView(view.getContext());
                    item.setLayoutParams(params);
                    item.setMaxLines(1);
                    item.setEllipsize(TextUtils.TruncateAt.END);
                    item.setGravity(Gravity.CENTER_VERTICAL);
                    item.setTextColor(ContextCompat.getColor(view.getContext(), R.color.black));
                    item.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimension(R.dimen.font_normal));
                    item.setCompoundDrawablePadding(leftPadding);
                    item.setPadding(padding, padding, padding, padding);

                    TypedValue typedValue = new TypedValue();
                    view.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
                    item.setBackgroundResource(typedValue.resourceId);

                    ((LinearLayout) view).addView(item);
                }

                private Drawable icon(Drawable drawable) {
                    if (drawable != null) {
                        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                        @SuppressWarnings("SuspiciousNameCombination") Drawable resizeIcon = new BitmapDrawable(getContext().getResources(), Bitmap.createScaledBitmap(bitmap, leftIcon, leftIcon, true));
                        Drawable.ConstantState state = resizeIcon.getConstantState();
                        resizeIcon = DrawableCompat.wrap(state == null ? resizeIcon : state.newDrawable().mutate());
                        return resizeIcon;
                    }
                    return null;
                }
            }
        }
    }
}
