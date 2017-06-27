package com.neartox.vangoghstagram

import android.app.Activity
import android.support.design.widget.CollapsingToolbarLayout
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.neartox.vangoghstagram.dummy.DummyContent

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class ItemDetailFragment : Fragment() {

  /**
   * The dummy content this fragment is presenting.
   */
  private var mItem: DummyContent.DummyItem? = null

  @Override
  fun onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)

    if (getArguments().containsKey(ARG_ITEM_ID)) {
      // Load the dummy content specified by the fragment
      // arguments. In a real-world scenario, use a Loader
      // to load content from a content provider.
      mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID))

      val activity = this.getActivity()
      val appBarLayout = activity.findViewById(R.id.toolbar_layout) as CollapsingToolbarLayout
      if (appBarLayout != null) {
        appBarLayout!!.setTitle(mItem!!.content)
      }
    }
  }

  @Override
  fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                   savedInstanceState: Bundle): View {
    val rootView = inflater.inflate(R.layout.item_detail, container, false)

    // Show the dummy content as text in a TextView.
    if (mItem != null) {
      (rootView.findViewById(R.id.item_detail) as TextView).setText(mItem!!.details)
    }

    return rootView
  }

  companion object {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    val ARG_ITEM_ID = "item_id"
  }
}
