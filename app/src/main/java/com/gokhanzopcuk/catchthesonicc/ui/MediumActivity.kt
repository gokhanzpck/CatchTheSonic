package com.gokhanzopcuk.catchthesonicc.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.gokhanzopcuk.catchthesonicc.MainActivity
import com.gokhanzopcuk.catchthesonicc.R
import com.gokhanzopcuk.catchthesonicc.databinding.ActivityMainBinding
import com.gokhanzopcuk.catchthesonicc.databinding.ActivityMediumBinding
import java.util.Random

class MediumActivity : AppCompatActivity() {
    var score=0
    var imageArray =ArrayList<ImageView>()
    var runnable = Runnable {}
    var handler= Handler(Looper.getMainLooper())
    private lateinit var binding: ActivityMediumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediumBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.button.setOnClickListener {
            val intent= Intent(this@MediumActivity, MainActivity::class.java)
            startActivity(intent)
        }
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        hideImages()
        object : CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.textView5.text="Time: ${millisUntilFinished/1000}"
            }
            override fun onFinish() {
                binding.textView5.text="Tıme: 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility= View.INVISIBLE
                }
                val alert= AlertDialog.Builder(this@MediumActivity)
                alert.setTitle("Game Over ")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialogInterface, i ->

                    val intentFromMain=intent
                    finish()
                    startActivity(intentFromMain)
                })
                alert.setNegativeButton("No", DialogInterface.OnClickListener{ DialogInterface, İ->
                    Toast.makeText(this@MediumActivity,"Congratulations!!!",
                        Toast.LENGTH_LONG).show()
                })
                alert.show()
            }
        }.start()
    }
    fun hideImages(){
        runnable=object : Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                val random= Random()
                val randomIndex=random.nextInt(9)
                imageArray[randomIndex].visibility=View.VISIBLE
                handler.postDelayed(runnable,400)
            }
        }
        handler.post(runnable) }
    fun increaseScore(view: View){
        score+=1
        binding.textView6.text= "Score:${score} "
    }
}