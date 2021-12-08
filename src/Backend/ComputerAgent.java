package Backend;

import java.util.Scanner;

public class ComputerAgent implements IComputerAgent {
    IMinimax minimax;
    int level;
    int turns = 0;
    int maxTurns = State.ROW_COUNT * State.COLUMNS_COUNT;
    State currentState;

    public ComputerAgent(boolean withPruning ,int k){
        currentState = new State();
        level = Math.min(k , maxTurns);
        if( withPruning )
            minimax = new MinimaxPruning();
        else
            minimax = new MinimaxWithoutPruning();
        System.out.println(withPruning);
    }

    public int getNextMove(int playerMove){
        currentState.updateState(playerMove, State.PLAYER_TURN);
        turns++;
        level = Math.min(level , maxTurns - turns);
        currentState.setEvaluationState(new EvaluationState());
        EvaluationState move = minimax.Decision(currentState, level);
        currentState.updateState(move.getFromColumn(), State.COMPUTER_TURN);
        turns++;
        return move.getFromColumn();
    }

    public int getFirstMove(){
        turns++;
        currentState.updateState(State.COLUMNS_COUNT / 2, State.COMPUTER_TURN);
        return State.COLUMNS_COUNT / 2;
    }

    public EvaluationState getEvaluationState(){
        return this.currentState.getEvaluationState();
    }
}

class Main2{
    public static void main(String[] args){
        /*ComputerAgent game = new ComputerAgent(false, 3);
        Scanner sc = new Scanner(System.in);
        while(game.turns < game.maxTurns){
            System.out.println("enter player move: ");
            int oppMove = sc.nextInt();
            int compMove = game.getNextMove(oppMove);
            System.out.println("computer move at: " + compMove);
        }*/
        int[][] board = new int[6][7];
        ComputerAgent game = new ComputerAgent(false, 3);
        board[0][0]=2;
        board[0][1]=2;
        board[1][0]=1;
        board[1][1]=2;
        board[2][0]=2;
        board[3][0]=1;
        board[4][0]=1;
        board[5][0]=1;
        game.currentState.setBoard(board);
        game.getNextMove(1);
    }
}
