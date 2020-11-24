package com.kietngo.example.laws.traffic.ui.content

import kotlin.random.Random

class Content {
    companion object{

        // random
        fun getRandomListId(index: Int, first : Int, last: Int): List<Int>{
            return  List(index){
                Random.nextInt(first, last)
            }
        }
    }
}