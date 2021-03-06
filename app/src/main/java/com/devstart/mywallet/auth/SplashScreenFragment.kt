package com.devstart.mywallet.auth


import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devstart.mywallet.R
import com.devstart.mywallet.prefs

class SplashScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            if(userExist()) {
                findNavController().navigate(R.id.action_splashScreenFragment_to_dashboardFragment)
            }else {
               findNavController().navigate(R.id.action_splashScreenFragment_to_signUpFragment)
            }
        }, 3000)
    }


    private fun userExist(): Boolean {
        val user = prefs.userPref
        return !user.equals("")
    }
}