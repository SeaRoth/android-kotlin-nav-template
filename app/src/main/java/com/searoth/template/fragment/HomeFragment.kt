package com.searoth.template.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.searoth.template.model.*
import com.searoth.template.R
import com.searoth.template.view.main.activity.MainActivity
import com.searoth.template.adapter.LocalFoodAdapter
import com.searoth.template.model.LocalFeed
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(){

    // TODO: Rename and change types of parameters
    private var TAG: String = "HomeFragment"
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var mInflater: View? = null
    private var mListener: OnFragmentInteractionListener? = null
    private var mainActivity: MainActivity? = null
    var localFeed: LocalFeed? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mInflater = inflater!!.inflate(R.layout.fragment_home, container, false)
        mInflater?.rv_main?.layoutManager = LinearLayoutManager(activity)

        //get the LocalFeed public var
        mainActivity = activity as MainActivity?
        localFeed =  mainActivity?.localFeed

        activity.runOnUiThread {
            try {
                setNavigationUI(localFeed?.user!!)
                mInflater?.rv_main?.adapter = LocalFoodAdapter(localFeed!!)
            }catch(e: Exception){
                Log.e(TAG, "Error: " + e.localizedMessage)
            }
        }

        return mInflater
    }

    private fun setNavigationUI(user: User){
        println("we are here " + user.name)
        val navigationView = activity.findViewById<View>(R.id.nav_view) as NavigationView
        val headerView = navigationView.getHeaderView(0)

        val navPicture = headerView.findViewById(R.id.iv_nav_user) as CircleImageView
        Picasso.with(activity).load(user.imageUrl).into(navPicture)

        val navName = headerView.findViewById(R.id.tv_nav_name) as TextView
        navName.text = user.name

        val navEmail = headerView.findViewById(R.id.tv_nav_email) as TextView
        navEmail.text = user.email
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
