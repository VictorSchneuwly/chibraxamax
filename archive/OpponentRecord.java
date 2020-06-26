import com.schneuwly.victor.chibraxamax.model.abstractEntitiy.PlayingEntity;

/**
 * A record against a specific playingEntity
 *
 * @author Victor Schneuwly
 */
public class OpponentRecord extends Record{
    //TODO: Later, PlayingEntity will have a list of OpponentRecord

    private final PlayingEntity playingEntity;

    public OpponentRecord(PlayingEntity opponent, int wins, int losses) {
        super(wins, losses);
        this.playingEntity = opponent;
    }

    public String getOpponentName(){
        return playingEntity.getName();
    }

}
