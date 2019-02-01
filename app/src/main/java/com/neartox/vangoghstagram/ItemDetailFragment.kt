package com.neartox.vangoghstagram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.google.android.material.appbar.CollapsingToolbarLayout

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
class ItemDetailFragment : androidx.fragment.app.Fragment() {

  /**
   * The dummy content this fragment is presenting.
   */
  private var mItem: DummyContent.DummyItem? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    if(arguments != null && arguments!!.containsKey(ARG_ITEM_ID)) {
      // Load the dummy content specified by the fragment
      // arguments. In a real-world scenario, use a Loader
      // to load content from a content provider.
      mItem = DummyContent.ITEM_MAP.get(arguments!!.getString(ARG_ITEM_ID))

      val activity = this.activity
      val appBarLayout = activity?.findViewById(R.id.toolbar_layout) as CollapsingToolbarLayout?
      appBarLayout?.title = mItem!!.content
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(R.layout.item_detail, container, false)

    // Show the dummy content as text in a TextView.
    if(mItem != null) {
      (rootView.findViewById(R.id.item_detail) as TextView?)?.text = mItem!!.details
    }

    return rootView
  }

  companion object {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    const val ARG_ITEM_ID = "item_id"
  }
}
