package com.example.chess.Engine

import kotlin.math.abs

object Moves {
    public fun getMoves(p: Int, ypos:Int, xpos:Int, board:MutableList<MutableList<Int>>): MutableList<Pair<Int, Int>>{
        return when(p){
            1, -1 -> pawn(p, ypos, xpos)
            10, -10 -> bishop(p, ypos, xpos, board)
            8, -8 -> rook(p, ypos, xpos, board)
            9, -9 -> knight(p, ypos, xpos)
            15, -15 -> queen(p, ypos, xpos, board)
            50, -50 -> king(p, ypos, xpos)
            else -> pawn(p, ypos, xpos)
        }
    }
    public fun pawn(p: Int, ypos: Int, xpos: Int): MutableList<Pair<Int, Int>>{
        val side=p/abs(p)
        val acts:MutableList<Pair<Int, Int>> =  mutableListOf(
            Pair(ypos+(1*side), xpos),
            Pair(ypos+(1*side), xpos+1),
            Pair(ypos+(1*side), xpos-1)
        )
        return acts
    }
    public fun rook(p: Int, ypos: Int, xpos: Int, board: MutableList<MutableList<Int>>): MutableList<Pair<Int, Int>>{
        val acts:MutableList<Pair<Int, Int>> =  mutableListOf()
        val side=p/ abs(p)
        var ytemp=ypos+1
        while (ytemp<8 && board[ytemp][xpos]==0){
            acts.addLast(Pair(ytemp, xpos))
            ytemp+=1
        }
        if(ytemp<8 && board[ytemp][xpos]/side<0){
            acts.addLast(Pair(ytemp, xpos))
        }
        ytemp=ypos-1
        while (ytemp>-1 && board[ytemp][xpos]==0){
            acts.addLast(Pair(ytemp, xpos))
            ytemp-=1
        }
        if(ytemp>-1 && board[ytemp][xpos]/side<0){
            acts.addLast(Pair(ytemp, xpos))
        }
        var xtemp=xpos+1
        while (xtemp<8 && board[ypos][xtemp]==0) {
            acts.addLast(Pair(ypos, xtemp))
            xtemp+=1
        }
        if(xtemp<8 && board[ypos][xtemp]/side<0){
            acts.addLast(Pair(ypos, xtemp))
        }
        xtemp=xpos-1
        while (xtemp>-1 && board[ypos][xtemp]==0){
            acts.addLast(Pair(ypos, xtemp))
            xtemp-=1
        }
        if(xtemp>-1 && board[ypos][xtemp]/side<0){
            acts.addLast(Pair(ypos, xtemp))
        }
        return acts
    }
    public fun bishop(p: Int, ypos: Int, xpos: Int, board: MutableList<MutableList<Int>>): MutableList<Pair<Int, Int>>{
        val acts:MutableList<Pair<Int, Int>> =  mutableListOf()
        var ytemp=ypos+1
        var xtemp=xpos+1
        val side=p/ abs(p)
        while (ytemp<8 && xtemp <8 && board[ytemp][xpos]==0){
            acts.addLast(Pair(ytemp, xtemp))
            ytemp+=1
            xtemp+=1
        }
        if(ytemp<8 && xtemp <8 && board[ytemp][xtemp]/side<0){
            acts.addLast(Pair(ytemp, xtemp))
        }
        ytemp=ypos-1
        xtemp=xpos-1
        while (ytemp>-1 && xtemp >-1 && board[ytemp][xpos]==0){
            acts.addLast(Pair(ytemp, xtemp))
            ytemp-=1
            xtemp-=1
        }
        if(ytemp>-1 && xtemp >-1 && board[ytemp][xtemp]/side<0){
            acts.addLast(Pair(ytemp, xtemp))
        }

        ytemp=ypos+1
        xtemp=xpos-1
        while (ytemp<8 && xtemp>-1 && board[ytemp][xpos]==0){
            acts.addLast(Pair(ytemp, xtemp))
            ytemp+=1
            xtemp-=1
        }
        if(ytemp<8 && xtemp >-1 && board[ytemp][xtemp]/side<0){
            acts.addLast(Pair(ytemp, xtemp))
        }
        ytemp=ypos-1
        xtemp=xpos+1
        while (ytemp>-1 && xtemp<8 && board[ytemp][xpos]==0){
            acts.addLast(Pair(ytemp, xtemp))
            ytemp-=1
            xtemp+=1
        }
        if(ytemp>-1 && xtemp <8 && board[ytemp][xtemp]/side<0){
            acts.addLast(Pair(ytemp, xtemp))
        }
        return acts
    }
    public fun knight(p: Int, ypos: Int, xpos: Int): MutableList<Pair<Int, Int>>{
        val acts:MutableList<Pair<Int, Int>> =  mutableListOf(
            Pair(ypos+2, xpos+1),
            Pair(ypos+2, xpos-1),
            Pair(ypos+1, xpos+2),
            Pair(ypos+1, xpos-2),
            Pair(ypos-1, xpos+2),
            Pair(ypos-1, xpos-2),
            Pair(ypos-2, xpos+1),
            Pair(ypos-2, xpos-1)
        )
        return acts
    }
    public fun queen(p: Int, ypos: Int, xpos: Int, board: MutableList<MutableList<Int>>): MutableList<Pair<Int, Int>>{
        val acts:MutableList<Pair<Int, Int>> = rook(p, ypos, xpos, board)
        val actBishop= bishop(p, ypos, xpos, board)
        for (pair in actBishop){
            acts.addLast(pair)
        }
        return acts
    }
    public fun king(p: Int, ypos: Int, xpos: Int): MutableList<Pair<Int, Int>>{
        return mutableListOf(
            Pair(ypos, xpos+1),
            Pair(ypos, xpos-1),
            Pair(ypos+1, xpos+1),
            Pair(ypos+1, xpos-1),
            Pair(ypos-1, xpos+1),
            Pair(ypos-1, xpos-1),
            Pair(ypos+1, xpos),
            Pair(ypos-1, xpos),
        )
    }
}
