package it.apuliadigital.fidelity.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.apuliadigital.fidelity.model.Card;
import it.apuliadigital.fidelity.model.RecordRewards;
import it.apuliadigital.fidelity.model.Rewards;
import it.apuliadigital.fidelity.repository.CardRepository;
import it.apuliadigital.fidelity.repository.RecordRewardsRepository;
import it.apuliadigital.fidelity.repository.RewardsRepository;
import it.apuliadigital.fidelity.sevice.Interface.IRewards;

@Service
public class RewardsServiceImpl implements IRewards {

    @Autowired
    private RewardsRepository rewardsRepository;

    @Autowired
    private RecordRewardsRepository recordRewardsRepository;

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Rewards createReward(Rewards reward) {
        return rewardsRepository.save(reward);
    }

    @Override
    public List<Rewards> getAllRewards() {
        return rewardsRepository.findAll();
    }

    @Override
    public Optional<Rewards> getRewardById(Long rewardId) {
        return rewardsRepository.findById(rewardId);
    }

    @Override
    @Transactional
    public RecordRewards redeemReward(Long cardId, Long rewardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        Rewards reward = rewardsRepository.findById(rewardId)
                .orElseThrow(() -> new RuntimeException("Reward not found"));

        if (card.getBalancePoint() < reward.getPuntiNecessari()) {
            throw new RuntimeException("Not enough points to redeem this reward");
        }

        card.setBalancePoint(card.getBalancePoint() - reward.getPuntiNecessari());
        cardRepository.save(card);

        RecordRewards record = new RecordRewards(cardId, rewardId, reward.getNome());
        return recordRewardsRepository.save(record);
    }

    @Override
    public List<RecordRewards> getRedeemedRewardsByCard(Long cardId) {
        return recordRewardsRepository.findByNumTessera(cardId);
    }

    @Override
    public List<RecordRewards> getAllRedeemedRewards() {
        return recordRewardsRepository.findAll();
    }

    @Override
    public Optional<Rewards> getRewardByName(String name) {
        return rewardsRepository.findByNomeIsContaining(name);
    }

    @Override
    public List<Rewards> getRewardsByPoints(int points) {
        return rewardsRepository.findByPuntiNecessari(points);
    }
}
