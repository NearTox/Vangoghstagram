package com.neartox.vangoghstagram

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.ActionBar
import android.support.v4.app.NavUtils
import android.view.MenuItem

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ItemListActivity].
 */
class ItemDetailActivity : AppCompatActivity() {

  @Override
  protected fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_item_detail)
    val toolbar = findViewById(R.id.detail_toolbar) as Toolbar
    setSupportActionBar(toolbar)

    val fab = findViewById(R.id.fab) as FloatingActionButton
    fab.setOnClickListener(object : View.OnClickListener() {
      @Override
      fun onClick(view: View) {
        Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
      }
    })

    // Show the Up button in the action bar.
    val actionBar = getSupportActionBar()
    if (actionBar != null) {
      actionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    // savedInstanceState is non-null when there is fragment state
    // saved from previous configurations of this activity
    // (e.g. when rotating the screen from portrait to landscape).
    // In this case, the fragment will automatically be re-added
    // to its container so we don't need to manually add it.
    // For more information, see the Fragments API guide at:
    //
    // http://developer.android.com/guide/components/fragments.html
    //
    if (savedInstanceState == null) {
      // Create the detail fragment and add it to the activity
      // using a fragment transaction.
      val arguments = Bundle()
      arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
          getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID))
      val fragment = ItemDetailFragment()
      fragment.setArguments(arguments)
      getSupportFragmentManager().beginTransaction()
          .add(R.id.item_detail_container, fragment)
          .commit()
    }
  }

  @Override
  fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id = item.getItemId()
    if (id == android.R.id.home) {
      // This ID represents the Home or Up button. In the case of this
      // activity, the Up button is shown. Use NavUtils to allow users
      // to navigate up one level in the application structure. For
      // more details, see the Navigation pattern on Android Design:
      //
      // http://developer.android.com/design/patterns/navigation.html#up-vs-back
      //
      NavUtils.navigateUpTo(this, Intent(this, ItemListActivity::class.java))
      return true
    }
    return super.onOptionsItemSelected(item)
  }
}