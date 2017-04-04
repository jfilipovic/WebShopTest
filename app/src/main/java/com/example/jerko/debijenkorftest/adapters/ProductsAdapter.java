package com.example.jerko.debijenkorftest.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jerko.debijenkorftest.R;
import com.example.jerko.debijenkorftest.models.Product;

import java.util.List;

/**
 * Created by Jerko on 18.1.2017..
 */

public class ProductsAdapter extends ArrayAdapter<Product> {
    private Activity activity;
    private List<Product> products;
    private ViewHolder holder;
    private List<Bitmap> images;

    public ProductsAdapter(Activity activity, List<Product> products, List<Bitmap> images){
        super(activity, R.layout.grid_item, products);
        this.activity = activity;
        this.products = products;
        this.images = images;
    }

    public int getCount() {
        return products.size();
    }

    public Product getItem(int position) {
        return products.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        if( convertView == null ){
            holder = new ViewHolder();
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.grid_item, null);

            holder.brandNameHolder = (TextView) convertView.findViewById(R.id.brand_name_holder);
            holder.productNameHolder = (TextView) convertView.findViewById(R.id.product_name_holder);
            holder.priceHolder = (TextView) convertView.findViewById(R.id.price_holder);
            holder.imageHolder = (ImageView) convertView.findViewById(R.id.image_holder);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product product = getItem(position);
        if (product.getBrand() != null && product.getBrand().getName() != null)holder.brandNameHolder.setText(product.getBrand().getName());
        if (product.getName() != null)holder.productNameHolder.setText(product.getName());
        if (product.getSellingPrice() != null && product.getSellingPrice().getValue() != null) holder.priceHolder.setText(product.getSellingPrice().getValue());
        if (images.get(position) != null )holder.imageHolder.setImageBitmap(images.get(position));

        return convertView;
    }

    static class ViewHolder {
        private TextView brandNameHolder;
        private TextView productNameHolder;
        private TextView priceHolder;
        private ImageView imageHolder;
    }

}
