package com.example.chess.Engine

import kotlin.math.abs

object MoveFilter {
    public fun filter(p:Int, moves: MutableList<Pair<Int, Int>>, board:List<List<Int>>):MutableList<Pair<Int, Int>>{
        when(p){
            0 -> {}
            1, -1 -> return pawn(p, moves, board)
            10, -10 -> return bishop(p, moves, board)
            8, -8 -> return rook(p, moves, board)
            9, -9 -> return knight(p, moves, board)
            15, -15 -> return queen(p, moves, board)
            50, -50 -> return king(p, moves, board)
        }
        return mutableListOf()
    }
    public fun pawn(p:Int, moves:MutableList<Pair<Int, Int>>, board:List<List<Int>>): MutableList<Pair<Int, Int>> {
        val side=p/abs(p)
        val copy=moves.toMutableList()
        if (moves[2].first>-1 && moves[2].second>-1 && moves[2].first<8 && moves[2].second< 8 &&
            board[moves[2].first][moves[2].second]/side>=0) {moves[2]=Pair(-1, -1)}
        if (moves[1].first>-1 && moves[1].second>-1 && moves[1].first<8&& moves[1].second< 8
            && board[moves[1].first][moves[1].second]/side>=0) moves[1]=Pair(-1, -1)
        if (board[moves[0].first][moves[0].second]!=0) moves[0]=Pair(-1, -1)
        val acts=moves.filter { it.first!=-1}
        return acts.toMutableList()
    }
    public fun knight(p:Int, moves:MutableList<Pair<Int, Int>>, board:List<List<Int>>): MutableList<Pair<Int, Int>> {
        val side=p/abs(p)
        val acts=moves.filter {
            (it.first <8 && it.first >-1 && it.second <8 && it.second >-1) &&
                    (board[it.first][it.second]/side<=0)
        }
        return acts.toMutableList()
    }
    public fun rook(p:Int, moves:MutableList<Pair<Int, Int>>, board:List<List<Int>>): MutableList<Pair<Int, Int>> {
        return moves
    }
    public fun bishop(p:Int, moves:MutableList<Pair<Int, Int>>, board:List<List<Int>>): MutableList<Pair<Int, Int>> {
        return rook(p, moves, board)
    }
    public fun queen(p:Int, moves:MutableList<Pair<Int, Int>>, board:List<List<Int>>): MutableList<Pair<Int, Int>> {
            return rook(p, moves, board)
    }
    public fun king(p:Int, moves:MutableList<Pair<Int, Int>>, board:List<List<Int>>): MutableList<Pair<Int, Int>> {
        val side=p/abs(p)
        val acts=moves.filter {
            (it.first <8 && it.first >-1 && it.second <8 && it.second >-1) &&
                    (board[it.first][it.second]/side<=0)
        }
        return acts.toMutableList()
    }
}