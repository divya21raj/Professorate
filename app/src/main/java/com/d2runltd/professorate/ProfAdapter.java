package com.d2runltd.professorate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfAdapter extends RecyclerView.Adapter<ProfAdapter.ViewHolder>
{
	private ArrayList<Professor> profList;
	private Context context;

	public ProfAdapter(ArrayList<Professor> profList, Context applicationContext)
	{
		this.profList = profList;
		this.context = applicationContext;
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		public TextView name;
		TextView dept;
		CircleImageView photo;

		ViewHolder(View itemView)
		{
			super(itemView);

			name = itemView.findViewById(R.id.p_name);
			dept = itemView.findViewById(R.id.p_dept);
			photo = itemView.findViewById(R.id.p_photo);
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(context).inflate(R.layout.prof_card, parent, false);
		// set the view's size, margins, padding and layout parameters...

		return new ProfAdapter.ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position)
	{
		Professor prof = profList.get(position);

		holder.name.setText(prof.getName());
		holder.dept.setText(prof.getDept());

		if(!prof.getImageLink().equals(""))
			Picasso.with(context).load(prof.getImageLink()).into(holder.photo);
		else
			holder.photo.setImageResource(R.drawable.default_prof_image);
	}

	@Override
	public int getItemCount()
	{
		return profList.size();
	}
}
