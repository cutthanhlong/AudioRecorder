package com.example.audiorecorder.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.audiorecorder.Interface.IClickItemLanguage;
import com.example.audiorecorder.Model.LanguageModel;
import com.example.audiorecorder.R;

import java.util.List;

public class LanguageStartAdapter extends RecyclerView.Adapter<LanguageStartAdapter.LanguageViewHolder> {
    private final List<LanguageModel> languageModelList;
    private final IClickItemLanguage iClickItemLanguage;
    private final Context context;

    public LanguageStartAdapter(List<LanguageModel> languageModelList, IClickItemLanguage listener, Context context) {
        this.languageModelList = languageModelList;
        this.iClickItemLanguage = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_language_start, parent, false);
        return new LanguageViewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {
        LanguageModel languageModel = languageModelList.get(position);
        if (languageModel == null) {
            return;
        }
        holder.tvLang.setText(languageModel.getName());
        if (languageModel.getActive()) {
            holder.layoutItem.setBackgroundResource(R.drawable.ic_lang_s);
            holder.tvLang.setTextColor(context.getColor(R.color.color_FC4184));
        } else {
            holder.layoutItem.setBackgroundResource(R.drawable.ic_lang_sn);
            holder.tvLang.setTextColor(context.getColor(R.color.color_3A5EA3));
        }

        switch (languageModel.getCode()) {
            case "fr":
                Glide.with(context).asBitmap().load(R.drawable.ic_lang_fr).into(holder.icLang);
                break;
            case "es":
                Glide.with(context).asBitmap().load(R.drawable.ic_lang_es).into(holder.icLang);
                break;
            case "zh":
                Glide.with(context).asBitmap().load(R.drawable.ic_lang_zh).into(holder.icLang);
                break;
            case "in":
                Glide.with(context).asBitmap().load(R.drawable.ic_lang_in).into(holder.icLang);
                break;
            case "hi":
                Glide.with(context).asBitmap().load(R.drawable.ic_lang_hi).into(holder.icLang);
                break;
            case "de":
                Glide.with(context).asBitmap().load(R.drawable.ic_lang_ge).into(holder.icLang);
                break;
            case "pt":
                Glide.with(context).asBitmap().load(R.drawable.ic_lang_pt).into(holder.icLang);
                break;
            case "en":
                Glide.with(context).asBitmap().load(R.drawable.ic_lang_en).into(holder.icLang);
                break;
        }

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheck(languageModel.getCode());
                iClickItemLanguage.onClickItemLanguage(languageModel.getCode());
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (languageModelList != null) {
            return languageModelList.size();
        } else {
            return 0;
        }
    }

    public static class LanguageViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvLang;
        private final LinearLayout layoutItem;
        private final ImageView icLang;

        public LanguageViewHolder(@NonNull View itemView) {
            super(itemView);
            icLang = itemView.findViewById(R.id.icLang);
            tvLang = itemView.findViewById(R.id.tvLang);
            layoutItem = itemView.findViewById(R.id.layoutItem);
        }
    }

    public void setCheck(String code) {
        for (LanguageModel item : languageModelList) {
            item.setActive(item.getCode().equals(code));

        }
        notifyDataSetChanged();
    }
}
