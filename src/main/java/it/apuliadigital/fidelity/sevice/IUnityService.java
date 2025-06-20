package it.apuliadigital.fidelity.sevice;

import java.util.List;

import it.apuliadigital.fidelity.model.Card;
import it.apuliadigital.fidelity.model.Rewards;

public interface IUnityService {
    public List<Rewards> readRewards();

    public Rewards saveRewards(Rewards reward);

    public Card saveCard(Card card);

    public List<Card> readCards();

    public Card readPoint(int numCard);

    public Rewards ritiraPremio(int id);

    public void aggiungiOrdine(int id, int punti);

    public List<Rewards> listaPremi();

    public List<Rewards> listaPremiUtenti(int numCard);

    

    public int getpointUser(int numCard);

}
