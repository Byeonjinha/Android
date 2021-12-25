package com.chobo.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Math.abs
import java.util.*

class MainActivity : AppCompatActivity() {
    var p_num = 3
    var k =1
    val point_list = mutableListOf<Float>()

    fun main(){
        setContentView(R.layout.activity_main)
        var timerTask: Timer? = null
        var stage = 1
        var sec : Int = 0
        val tv: TextView = findViewById(R.id.tv_random)
        val tv_t: TextView = findViewById(R.id.tv_timer)
        val tv_p: TextView = findViewById(R.id.tv_point)
        val tv_people: TextView = findViewById(R.id.tv_people)
        val btn: Button = findViewById(R.id.btn_main)
        val random_box = Random()
        val num =random_box.nextInt(1001)
        tv.text = ((num.toFloat())/100).toString()
        btn.text = "시작"
        tv_people.text = "참가자 $k"
        btn.setOnClickListener{
            stage ++
            if (stage == 2) {
                timerTask = kotlin.concurrent.timer(period = 10) {
                    sec++
                    runOnUiThread {
                        tv_t.text = (sec.toFloat()/100).toString()
                    }
                }
                btn.text = "정지"
            } else if(stage == 3) {
                timerTask?.cancel()
                val point = abs(sec-num).toFloat()/100
                point_list.add(point)

                tv_p.text = point.toString()
                btn.text = "다음"
                stage = 0
            }  else if(stage == 1) {
                if (k< p_num) {
                    k++
                    main()
                } else {
                    println(k)
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main()


    }
}