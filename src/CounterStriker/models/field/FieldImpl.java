package CounterStriker.models.field;

import CounterStriker.common.OutputMessages;
import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FieldImpl implements Field{


    @Override
    public String start(List<Player> players) {
        List<Player> terrorist = new ArrayList<>();
        List<Player> counterTerrorist = new ArrayList<>();

        for (Player player : players) {
            if(player.getClass().getSimpleName().equals("Terrorist")){
                terrorist.add(player);
            } else {
                counterTerrorist.add(player);
            }
        }

        while (counterTerrorist.stream().anyMatch(Player::isAlive) && terrorist.stream().anyMatch(Player::isAlive)) {

            for (int i = 0; i < terrorist.size(); i++) {
                Player player = terrorist.get(i);

                for (int j = 0; j < counterTerrorist.size(); j++) {
                    Player player1 = counterTerrorist.get(j);

                    player1.takeDamage(player.getGun().fire());
                }
            }
            counterTerrorist = counterTerrorist.stream().filter(Player::isAlive).collect(Collectors.toList());

            for (Player conTerror : counterTerrorist) {
                for (Player terror : terrorist) {
                    terror.takeDamage(conTerror.getGun().fire());
                }
            }
            terrorist = terrorist.stream().filter(Player::isAlive).collect(Collectors.toList());
        }

        return terrorist.stream().anyMatch(Player::isAlive) ? OutputMessages.TERRORIST_WINS : OutputMessages.COUNTER_TERRORIST_WINS;
    }
}
