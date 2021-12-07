package Backend;

public class Evaluation {
    private static final int QUADRUPLE_SCORE = 100;
    private static final int TRIPLE_SCORE = 5;
    private static final int DOUBLE_SCORE = 2;
    private static final int WINDOW_SIZE = 4;
    public static int evaluateScore(int[][] board){
        int evalScore = 0;

        //Horizontal groups check 
        for( int i = 0 ; i < State.ROW_COUNT ; i++ )
            for( int j = 0 ; j < State.COLUMNS_COUNT -3 ; j++)
                evalScore += evalHorizontalWindow(board, i, j);
        
        //Vertical groups check
        for( int j = 0 ; j < State.COLUMNS_COUNT ; j++)
            for( int i = 0 ; i < State.ROW_COUNT -3 ; i++ )
                evalScore += evalVerticalWindow(board, i, j);
        
        for( int i = 0 ; i < State.ROW_COUNT - 3 ; i++ )
            for( int j = 0 ; j < State.COLUMNS_COUNT - 3 ; j++ ){
                //Positive diagonal groups check "/""
                evalScore += evalPDiagonalWindow(board, i, j);
                //Negative diagonal groups check "\"
                evalScore += evalNDiagonalWindow(board, i, State.COLUMNS_COUNT - j - 1);
            }
        
        return evalScore;
    }
    private static int evalHorizontalWindow(int[][] board, int startRow, int startColumn){
        int oppPiece = 0;
        int piece = 0;
        int empty = 0;
        for(int i=startColumn; i<startColumn+WINDOW_SIZE; i++){
            if(board[startRow][i]==State.PLAYER_TURN){
                oppPiece++;
            }else if(board[startRow][i]==State.COMPUTER_TURN){
                piece++;
            }else{
                empty++;
            }
        }
        return calculateWeights(piece,oppPiece,empty);
    }

    private static int evalVerticalWindow(int[][] board, int startRow, int startColumn){
        int compPieces = 0;
        int oppPieces = 0;
        int emptyPieces = 0;
        for(int i = startRow; i < startRow + Evaluation.WINDOW_SIZE; i++){
            if(board[i][startColumn] == State.COMPUTER_TURN)
                compPieces++;
            else if(board[i][startColumn] == State.PLAYER_TURN)
                oppPieces++;
            else
                emptyPieces++;
        }
        return calculateWeights(compPieces, oppPieces, emptyPieces);
    }

    private static int evalPDiagonalWindow(int[][] board, int startRow, int startColumn ){
        int compPieces = 0;
        int oppPieces = 0;
        int emptyPieces = 0;
        for(int i = startRow; i < startRow + Evaluation.WINDOW_SIZE; i++){
            if(board[i][startColumn] == State.COMPUTER_TURN)
                compPieces++;
            else if(board[i][startColumn] == State.PLAYER_TURN)
                oppPieces++;
            else
                emptyPieces++;
            startColumn++;
        }
        return calculateWeights(compPieces, oppPieces, emptyPieces);
    }

    private static int evalNDiagonalWindow(int[][] board, int startRow, int startColumn){
        int compPieces = 0;
        int oppPieces = 0;
        int emptyPieces = 0;
        for(int i = startRow; i < startRow + Evaluation.WINDOW_SIZE; i++){
            if(board[i][startColumn] == State.COMPUTER_TURN)
                compPieces++;
            else if(board[i][startColumn] == State.PLAYER_TURN)
                oppPieces++;
            else
                emptyPieces++;
            startColumn --;
        }
        return calculateWeights(compPieces, oppPieces, emptyPieces);
    }

    private static int calculateWeights(int pieces, int oppPiece, int empty){
        if(pieces==4){
            return QUADRUPLE_SCORE;
        }else if(pieces==3 && empty==1){
            return TRIPLE_SCORE;
        }else if(pieces==2 && empty==2){
            return DOUBLE_SCORE;
        }
        if(oppPiece==4){
            return -1*QUADRUPLE_SCORE;
        }else if(oppPiece==3 && empty==1){
            return -1*60;
        }else if(oppPiece==2 && empty==2){
            return -1*DOUBLE_SCORE;
        }
        return 0;
    }

}
