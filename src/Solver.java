import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {


    public Solver(Board initial) {
        MinPQ<Board.Move> moves = new MinPQ<Board.Move>();
        moves.insert(new Board.Move(initial));

        MinPQ<Board.Move> twinMoves = new MinPQ<Board.Move>();
        twinMoves.insert(new Board.Move(initial.twin()));

        while(true) {
            Board.lastMove = Board.expand(moves);
            if (Board.lastMove != null || Board.expand(twinMoves) != null) return;
        }
    }


    public int moves() {
        return Board.isSolvable() ? Board.lastMove.numMoves : -1;
    }

    public Iterable<Board> solution() {
        if (!Board.isSolvable()) return null;

        Stack<Board> moves = new Stack<Board>();
        while(Board.lastMove != null) {
            moves.push(Board.lastMove.board);
            Board.lastMove = Board.lastMove.previous;
        }

        return moves;
    }


    public static void main(String[] args){
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for(int i =0;i<N;i++){
            for(int j=0;j<N;j++){
                blocks[i][j]=in.readInt();
            }
        }
        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);
            StdOut.println("Minumum number of moves = " + solver.moves());
            try{
                for(Board board:solver.solution())
                {
                    StdOut.println(board);
                }
            }
            catch (Exception e){
                throw new IllegalArgumentException("Input is not solvable!");
            }

        }

    }