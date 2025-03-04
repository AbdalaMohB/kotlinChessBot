package com.example.chess.Engine

import kotlin.system.exitProcess

data class Node(var board: MutableList<MutableList<Int>>, var value: Int, var next:MutableList<Node>)
public fun evaluateNode(node: Node): Int{
    if (node.next.size==0)
    {
        node.value= evalBoard(node)
        return node.value
    }
    var sum=0
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
            res+=p
        }
    }
    return res
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
