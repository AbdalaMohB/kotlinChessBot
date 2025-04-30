package com.example.chess.Engine
import kotlin.math.abs
import kotlin.random.Random
import kotlin.system.exitProcess

class Bot(var root:Node, private val maxdepth:Int=4) {
//    public fun evaluate(){
//
//    }
private var isEnd: Int = when((maxdepth-1)%2){
        0 -> maxdepth-1
        else -> maxdepth-2
    }

    public fun choose() : MutableList<MutableList<Int>> {
        val maxval=root.next.maxBy { it.value }.value
        val pruned=root.next.filter { it.value==maxval }
        val rng=abs(Random.nextInt()) %pruned.size
        root=pruned[rng]
        return clonemat(root.board)
    }
    public fun myturn(board: MutableList<MutableList<Int>>){
        root.board= clonemat(board)
        root.next.clear()
        evaluateNode(root)
        val gameOver=gameover(root)
        if (gameOver!=0){
            if (gameOver==1){
                println("YOU LOSE!")
            }
            else{
                println("YOU WIN!")
            }
            exitProcess(0)
        }
    }
    public fun ponder(node: Node=root, depth:Int=1){
        if(depth==maxdepth) return
        if (depth%2==0) return counter(node, depth)
        applier(node, 1){ moves, y, x ->
            for(i in moves){
                if (i.first>7 || i.first<0 || i.second>7 || i.second<0){
                    continue
                }
                val b=makeMove(clonemat(node.board), y, x, i)
                val n=Node(board = b, 0, mutableListOf())
                ponder(n, depth+1)
                if (depth==1) {
                    evaluateNode(n)
                    n.next.clear()
                }
                node.next.addLast(n)
            }
        }
        //evaluateNode(node)
    }
    public fun counter(node: Node=root, depth: Int=1){
        applier(node, -1){ moves, y, x ->
            for(i in moves){
                if (i.first>7 || i.first<0 || i.second>7 || i.second<0){
                    continue
                }
                val b=makeMove(clonemat(node.board), y, x, i)
                val n=Node(board = b, 0, mutableListOf())
                node.next.addLast(n)
            }
        }

        for (n in node.next){
            ponder(n, depth+1)
        }
        if (depth==2) {
            evaluateNode(node)
            val lowval = node.next.minBy { it.value }.value
            val pruned = node.next.filter { it.value == lowval }
            node.next = pruned.toMutableList()
        }
    }
    public fun makeMove(board:MutableList<MutableList<Int>>, y:Int, x:Int, pair:Pair<Int, Int>): MutableList<MutableList<Int>>{
        //if (board[y][x]<8) board[y][x]+=1
        //else if (board[y][x]>-8) board[y][x]-=1
        if (board[y][x]==1 && pair.first==7){
            board[pair.first][pair.second] = 15
            board[y][x] = 0
            return board
        }
        else if (board[y][x]==-1 && pair.first==0){
            board[pair.first][pair.second] = -15
            board[y][x] = 0
            return board
        }
        board[pair.first][pair.second] = board[y][x]
        board[y][x] = 0
        return board
    }
}
