package com.xxx.recommendfilm.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.xxx.recommendfilm.BR;
import com.xxx.recommendfilm.databinding.ItemFooterProgressbarBinding;

import java.util.List;

public abstract class BaseRecyclerAdapter<Bean, Binding extends ViewDataBinding> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TYPE_HEADER = 0;
    private static final int ITEM_TYPE_CONTENT = 1;
    private static final int ITEM_TYPE_LOAD_MORE = 2;
    private static final int FOOTER_SIZE = 1;

    private int layoutRes;
    private Boolean hasLoadMore;
    public List<Bean> baseList;
    private OnCellClickListener<Bean> onCellClickListener;
    private View headerView;
    public MutableLiveData<Boolean> onLoadMore = new MutableLiveData<>(false);

    public interface OnCellClickListener<Bean> {
        void onCellClick(Bean bean);
    }

    public void setOnCellClickListener(OnCellClickListener<Bean> onCellClickListener) {
        this.onCellClickListener = onCellClickListener;
    }

    public BaseRecyclerAdapter(
            LifecycleOwner lifecycleOwner,
            int layoutRes,
            Boolean hasLoadMore,
            final List<Bean> baseList
    ) {
        super();
        this.layoutRes = layoutRes;
        this.hasLoadMore = hasLoadMore;
        this.baseList = baseList;
        onLoadMore.observe(lifecycleOwner, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                notifyItemChanged(baseList.size() - 1);
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE_CONTENT:
                return new ContentViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(
                                layoutRes,
                                parent,
                                false
                        )
                );
            case ITEM_TYPE_HEADER:
                if (headerView != null) return new HeaderViewHolder(headerView);
                else throw new RuntimeException("no headerView");
            case ITEM_TYPE_LOAD_MORE:
                return new FooterViewHolder(
                        ItemFooterProgressbarBinding.inflate(
                                LayoutInflater.from(parent.getContext()), parent, false
                        ).getRoot()
                );
            default:
                throw new RuntimeException("no such ViewType");
        }
    }


    class ContentViewHolder extends RecyclerView.ViewHolder {
        Binding binding;

        public ContentViewHolder(View itemView) {
            super(itemView);
            this.binding = DataBindingUtil.bind(itemView);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        Binding binding;

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        Binding binding;

        public FooterViewHolder(View itemView) {
            super(itemView);
        }

        void onLoadMore() {
            if (onLoadMore.getValue()) itemView.setVisibility(View.VISIBLE);
            else itemView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {

        if (headerView == null) {
            if (hasLoadMore) {
                return baseList.size() + FOOTER_SIZE;
            } else {
                return baseList.size();
            }
        } else {
            if (hasLoadMore)
                return baseList.size() + 1 + FOOTER_SIZE;
            else return baseList.size() + 1;
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (headerView != null) {
            if (position == 0) return ITEM_TYPE_HEADER;
            if (position >= baseList.size() + 1) return ITEM_TYPE_LOAD_MORE;
            return ITEM_TYPE_CONTENT;
        } else {
            if (position >= baseList.size()) return ITEM_TYPE_LOAD_MORE;
            else return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ITEM_TYPE_CONTENT:
                final int pos;
                if (headerView != null) pos = position - 1;
                else pos = position;
                ContentViewHolder holder1 = (ContentViewHolder) holder;
                holder1.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onCellClickListener != null)
                            onCellClickListener.onCellClick(baseList.get(pos));
                    }
                });
                bindData(holder1.binding, pos);
                break;
            case ITEM_TYPE_HEADER:
                break;
            case ITEM_TYPE_LOAD_MORE:
                FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
                footerViewHolder.onLoadMore();
                break;
        }

    }

    public abstract void bindData(Binding binding, int position);

    public void replaceData(List<Bean> newList) {
        baseList = newList;
        notifyDataSetChanged();
    }
}
