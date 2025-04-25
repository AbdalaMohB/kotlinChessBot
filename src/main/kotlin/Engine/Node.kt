package com.example.chess.Engine

import kotlin.system.exitProcess
const val PRESENT_IMPORTANCE=2
data class Node(var board: MutableList<MutableList<Int>>, var value: Int, var next:MutableList<Node>)
public fun evaluateNode(node: Node): Int{
    if (node.next.size==0)
    {
        node.value= evalBoard(node)
        return node.value
    }
    var sum=evalBoard(node)* PRESENT_IMPORTANCE
    for (next: Node in node.next){

        sum+=evaluateNode(next)
    }
    node.value=sum
    return sum
}
public fun evalBoard(node: Node): Int {
    var res=0
    for(row: List<Int> in node.board){
        for (p: Int in row){
            val piece=when(p){
                50 -> 100
                -50 -> -120
                15 -> 25
                -15 -> -30
                8, 9 -> 10
                -8, -9 -> -10
                else -> p
            }
            res+=piece
        }
    }
    return res
}
public fun evalParent(node: Node): Int {
    var sum=0
    for (next: Node in node.next){
        sum+=evaluateNode(next)
    }
    node.value=sum
    return sum
}
public fun applier(node: Node, side:Int, pred: (MutableList<Pair<Int, Int>>, Int, Int) -> Unit){
    for(y in 0..7){
        for (x in 0..7){
            if (node.board[y][x]/side>0) {
                pred(MoveFilter.filter(node.board[y][x], Moves.getMoves(node.board[y][x], y, x, node.board), node.board),y, x)
            }
        }
    }
}
public fun clonemat(board: MutableList<MutableList<Int>>): MutableList<MutableList<Int>>{
    val clone: MutableList<MutableList<Int>> = mutableListOf()
    for (row in board){
        clone.addLast(row.toMutableList())
    }
    return clone
}
public fun gameover(node: Node):Int{
    val kings: MutableList<Int> = mutableListOf(0, 0)
    val board=node.board
    for (row in board){
        for (p in row){
            if (p==50) kings[0]=1
            if (p==-50) kings[1]=1
        }
    }
    return kings[0]-kings[1]
}
