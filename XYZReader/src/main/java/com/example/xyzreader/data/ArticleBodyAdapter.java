package com.example.xyzreader.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.text.Html;
import android.graphics.Typeface;
import java.util.regex.Pattern;

import com.example.xyzreader.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class ArticleBodyAdapter extends RecyclerView.Adapter<ArticleBodyAdapter.TextHolder> {
    private Context context;
    private List<String> bodyList;


    public ArticleBodyAdapter(Context context, String body) {
        this.context = context;
        this.bodyList = initList(body);
    }

    private List<String> initList(String body) {
        // fix chapter new lines
        body = body.replaceAll("\n\n", "\r\n\r\n");
        // split into paragraphs
        String[] splitArray = body.split("\r\n\r\n");
        return new ArrayList<>(Arrays.asList(splitArray));
    }

    @NonNull
    @Override
    public ArticleBodyAdapter.TextHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.list_item_body_splits;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, parent, false);

        return new TextHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleBodyAdapter.TextHolder holder, int position) {
        String paragraph = bodyList.get(position);
        holder.bind(paragraph);
    }

    @Override
    public int getItemCount() {
        return bodyList.size();
    }

    class TextHolder extends RecyclerView.ViewHolder {

        TextView paragraphView;

        TextHolder(View itemView) {
            super(itemView);
            paragraphView = itemView.findViewById(R.id.tv_paragraph);
            paragraphView.setTypeface(Typeface.createFromAsset(context.getResources().getAssets(), "Rosario-Regular.ttf"));
        }

        void bind(String paragraph) {
            //fix new lines in paragraph text
            String text = Pattern.compile("\r\n").matcher(paragraph).replaceAll(" ");
            paragraphView.setText(Html.fromHtml(text));
        }
    }
}
