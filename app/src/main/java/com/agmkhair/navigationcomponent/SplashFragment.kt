package com.agmkhair.navigationcomponent;


import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Property
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController


class SplashFragment : Fragment() {

    private lateinit var rootView:View

    // View Elemente :
    private lateinit var icon : ImageView
    private lateinit var title : TextView

    // Animation Value :
    private val animDuration = 500L


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_splash, container, false)

        icon = rootView.findViewById(R.id.icon)
        title = rootView.findViewById(R.id.title)

        icon.alpha = 0f
        title.alpha = 0f

        icon.translationY = 50f
        title.translationY = 50f

        startAnimation()
        return rootView
    }

    private fun startAnimation() {

        val imageSet = AnimatorSet().apply {
            play(createObjectAnimator(icon,View.ALPHA,1f))
                .with(createObjectAnimator(icon,View.TRANSLATION_Y,0f))
        }

        val textSet = AnimatorSet().apply {
            play(createObjectAnimator(title,View.ALPHA,1f,0))
                .with(createObjectAnimator(icon,View.TRANSLATION_Y,0f,0))
        }


        val animSet = AnimatorSet().apply {
            play(imageSet)
                .before(textSet)
                start()
        }

        animSet.addListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                Thread(Runnable {
                    Thread.sleep(500)

                  //  findNavController(R.id.splash_screen).navigate(R.id.action_splase_home)

                }).start()
            //    Navigation.findNavController(rootView).popBackStack(R.id.homeFragment,true)
                findNavController().navigate(R.id.action_splase_home)
               /* Navigation.findNavController(requireView()).popBackStack(
                    R.id.homeFragment, true)*/
               // findNavController().navigate(SplashFragment.actionSplaseHome())

            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })

    }

    private fun createObjectAnimator(view:View,type: Property<View, Float>, value: Float,delay:Long = 1000):ObjectAnimator
    {
        val animator = ObjectAnimator.ofFloat(view,type,value).apply {
            startDelay = delay
            duration = animDuration
            interpolator = FastOutLinearInInterpolator()

        }
        return animator
    }
}
