package com.neartox.vangoghstagram

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull


import com.neartox.vangoghstagram.dummy.DummyContent

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

  /**
   * Whether or not the activity is in two-pane mode, i.e. running on a tablet
   * device.
   */
  private var mTwoPane: Boolean = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_item_list)

    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)
    toolbar.title = title

    val fab = findViewById<FloatingActionButton>(R.id.fab)
    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show()
    }

    val recyclerView = findViewById<RecyclerView>(R.id.item_list)!!
    setupRecyclerView(recyclerView)

    if(findViewById<View>(R.id.item_detail_container) != null) {
      // The detail container view will be present only in the
      // large-screen layouts (res/values-w900dp).
      // If this view is present, then the
      // activity should be in two-pane mode.
      mTwoPane = true
    }
  }

  private fun setupRecyclerView(recyclerView: androidx.recyclerview.widget.RecyclerView) {
    recyclerView.adapter = SimpleItemRecyclerViewAdapter(DummyContent.ITEMS)
  }

  inner class SimpleItemRecyclerViewAdapter(private val mValues: List<DummyContent.DummyItem>) : androidx.recyclerview.widget.RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view = LayoutInflater.from(parent.context)
          .inflate(R.layout.item_list_content, parent, false)
      return ViewHolder(view)
    }

    override
    fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.mItem = mValues[position]
      holder.mIdView.text = mValues[position].id
      holder.mContentView.text = mValues[position].content

      holder.mView.setOnClickListener { v ->
        if(mTwoPane) {
          val arguments = Bundle()
          arguments.putString(ItemDetailFragment.ARG_ITEM_ID, holder.mItem!!.id)
          val fragment = ItemDetailFragment()
          fragment.arguments = arguments
          supportFragmentManager.beginTransaction()
              .replace(R.id.item_detail_container, fragment)
              .commit()
        } else {
          val context = v.context
          val intent = Intent(context, ItemDetailActivity::class.java)
          intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, holder.mItem!!.id)

          context.startActivity(intent)
        }
      }
    }


    override fun getItemCount(): Int {
      return mValues.size
    }

    inner class ViewHolder(val mView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(mView) {
      val mIdView: TextView
      val mContentView: TextView
      var mItem: DummyContent.DummyItem? = null

      init {
        mIdView = mView.findViewById(R.id.id) as TextView
        mContentView = mView.findViewById(R.id.content) as TextView
      }

      override fun toString(): String {
        return super.toString() + " '" + mContentView.text + "'"
      }
    }
  }
}