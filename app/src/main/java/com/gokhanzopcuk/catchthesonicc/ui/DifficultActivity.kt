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
import com.gokhanzopcuk.catchthesonicc.databinding.ActivityDifficultBinding
import com.gokhanzopcuk.catchthesonicc.databinding.ActivityMainBinding
import java.util.Random

class DifficultActivity : AppCompatActivity() {
    var score=0
    var imageArray =ArrayList<ImageView>()
    var runnable = Runnable {}
    var handler= Handler(Looper.getMainLooper())
    private lateinit var binding: ActivityDifficultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDifficultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.button.setOnClickListener {
            val intent= Intent(this@DifficultActivity, MainActivity::class.java)
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
                binding.textView7.text="Time: ${millisUntilFinished/1000}"
            }
            override fun onFinish() {
                binding.textView7.text="Tıme: 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility= View.INVISIBLE
                }
                val alert= AlertDialog.Builder(this@DifficultActivity)
                alert.setTitle("Game Over ")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialogInterface, i ->

                    val intentFromMain=intent
                    finish()
                    startActivity(intentFromMain)
                })
                alert.setNegativeButton("No", DialogInterface.OnClickListener{ DialogInterface, İ->
                    Toast.makeText(this@DifficultActivity,"Congratulations!!!",
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
                handler.postDelayed(runnable,200)
            }
        }
        handler.post(runnable) }
    fun increaseScore(view: View){
        score+=1
        binding.textView8.text= "Score:${score} "
    }

}