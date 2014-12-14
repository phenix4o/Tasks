package com.example.exenselsit;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MyAdapter extends BaseAdapter {

	public void add(Expense exp) {
		expList.add(exp);
	}

	private final static class ViewHolder {
		public TextView name;
		public TextView number;
		public ImageView img;
	}

	private List<Expense> expList = new ArrayList<Expense>();
	


	@Override
	public int getCount() {
		return expList.size();
	}

	@Override
	public Object getItem(int i) {
		return null;
	}

	@Override
	public long getItemId(int i) {
		return 0;
	}

	@SuppressLint({ "InflateParams", "ClickableViewAccessibility" }) @Override
	public View getView(final int position, View convertView,
			final ViewGroup viewGroup) {
		// //

		LinearLayout layout = null;
		if (convertView != null) {
			layout = (LinearLayout) convertView;
		} else {
			layout = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
					.inflate(R.layout.row_of_expenses, null, false);

			TextView name = (TextView) layout.findViewById(R.id.name);
			TextView number = (TextView) layout.findViewById(R.id.number);
			ImageView img = (ImageView) layout.findViewById(R.id.img);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.name = name;
			viewHolder.number = number;
			viewHolder.img = img;
			layout.setTag(viewHolder);

			layout.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							viewGroup.getContext());

					AlertDialog dialog = builder
							.setTitle("Dialog")
							.setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											Toast.makeText(
													viewGroup.getContext(),
													"Yes clicked",
													Toast.LENGTH_SHORT).show();
										}
									}).setNegativeButton("No", null).create();

					dialog.show();
					return false;
				}
			});
		}

		Expense expence = expList.get(position);
		ViewHolder holder = (ViewHolder) layout.getTag();
		holder.name.setTextColor(Color.parseColor("#334d5c"));
		holder.name.setText(expence.getName());
		holder.number.setTextColor(Color.parseColor("#334d5c"));
		holder.number.setText(expence.getNumber());
	
		
		
		
		
		
		holder.img.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup
						.getContext());

				AlertDialog dialog = builder
						.setTitle("Delete").setMessage("Are you sure you want to delete the item?").setNegativeButton("No", null)
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										expList.remove(position);
										notifyDataSetChanged();
									}
								}).create();

				dialog.show();
				return false;
			}
		});
		return layout;
	}
}