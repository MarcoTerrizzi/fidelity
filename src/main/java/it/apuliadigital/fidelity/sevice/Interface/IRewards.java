package it.apuliadigital.fidelity.sevice.Interface;

import java.util.List;
import java.util.Optional;

import it.apuliadigital.fidelity.model.RecordRewards;
import it.apuliadigital.fidelity.model.Rewards;

public interface IRewards {

    Rewards createReward(Rewards reward);

    List<Rewards> getAllRewards();

    Optional<Rewards> getRewardById(Long rewardId);

    RecordRewards redeemReward(Long cardId, Long rewardId);

    List<RecordRewards> getRedeemedRewardsByCard(Long cardId);

    List<RecordRewards> getAllRedeemedRewards();

    Optional<Rewards> getRewardByName(String name);

    List<Rewards> getRewardsByPoints(int points);
}
