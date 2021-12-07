package Backend;

public class MinimaxWithoutPruning implements IMinimax{

    public static EvaluationState Maximize(State state, int level){
        if(level==0){
            int eval = state.evaluateState();
            state.getEvaluationState().setEvaluationValue(eval);
            return state.getEvaluationState();
        }
        for(int i=0; i<7; i++){
            State child = state.getChild(i , State.COMPUTER_TURN);
            if(child!=null){
                child.getEvaluationState().setEvaluationValue(Minimize(child,level-1).getEvaluationValue());
                state.getEvaluationState().addChild(child.getEvaluationState());
                if(child.getEvaluationState().getEvaluationValue()>state.getEvaluationState().getEvaluationValue()){
                    state.getEvaluationState().setEvaluationValue(child.getEvaluationState().getEvaluationValue());
                }
            }
        }
        return state.getEvaluationState();
    }

    public static EvaluationState Minimize(State state, int level){
        if(level==0){
            int eval = state.evaluateState();
            state.getEvaluationState().setEvaluationValue(eval);
            return state.getEvaluationState();
        }
        for(int i=0; i<7; i++){
            State child = state.getChild(i , State.PLAYER_TURN);
            if(child!=null){
                child.getEvaluationState().setEvaluationValue(Maximize(child,level-1).getEvaluationValue());
                state.getEvaluationState().addChild(child.getEvaluationState());
                if(child.getEvaluationState().getEvaluationValue()<state.getEvaluationState().getEvaluationValue()){
                    state.getEvaluationState().setEvaluationValue(child.getEvaluationState().getEvaluationValue());
                }
            }
        }
        return state.getEvaluationState();
    }

    @Override
    public EvaluationState Decision(State initial, int level) {
        return Maximize(initial,level);
    }
}
