package com.d2runltd.professorate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

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

		ViewHolder(View itemView)
		{
			super(itemView);

			name = itemView.findViewById(R.id.p_name);
			dept = itemView.findViewById(R.id.p_dept);
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
	}

	@Override
	public int getItemCount()
	{
		return profList.size();
	}
}
