package com.example.chess

import com.example.chess.Engine.Bot
import com.example.chess.Engine.Node
import kotlin.system.exitProcess

fun play(board: MutableList<MutableList<Int>>){
    val moves:MutableList<Int> = mutableListOf(0, 0, 0, 0)
    var idx=0
    while (idx<4){
        moves[idx]= readln().toInt()
        idx+=1
    }
    //if (board[moves[0]][moves[1]]==-1) board[moves[0]][moves[1]]+=1
    board[moves[2]][moves[3]]=board[moves[0]][moves[1]]
    board[moves[0]][moves[1]]=0
}
fun transform(p:Int):String{
    return when(p){
        -1 -> "WP"
        1 -> "BP"
        8 -> "BR"
        -8 -> "WR"
        9 -> "BN"
        -9 -> "WN"
        -10 -> "WB"
        10 -> "BB"
        15 -> "BQ"
        -15 -> "WQ"
        50 -> "BK"
        -50 -> "WK"
        else -> "__"
    }
}
fun printB(board: MutableList<MutableList<Int>>){
    for (row in board){
        for (p in row){
            val c= transform(p)
            print("$c ")
        }
        println()
    }
}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var baseBoard: MutableList<MutableList<Int>> = mutableListOf(
        mutableListOf(8, 9, 10, 15, 50, 10, 9, 8),
        mutableListOf(1, 1, 1, 1, 1, 1, 1, 1),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(-1, -1, -1, -1, -1, -1, -1, -1),
        mutableListOf(-8, -9, -10, -15, -50, -10, -9, -8),
    )
    var current:MutableList<MutableList<Int>>;
    val node=Node(baseBoard.toMutableList(), 0, mutableListOf())
    val bot=Bot(node, 6)
    while (true){
        bot.ponder()
        bot.evaluate()
        current=bot.choose()
        printB(current)
        play(current)
        bot.myturn(current)
    }
}
