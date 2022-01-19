package pl.mariodev.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.mariodev.creditapp.core.model.MaritalStatus;
import pl.mariodev.creditapp.core.model.Person;

public class MartialStatusCalculator implements ScoringCalculator {

    private static final Logger log = LoggerFactory.getLogger(MartialStatusCalculator.class);

    @Override
    public int calculate(Person person) {
        MaritalStatus maritalStatus = person.getPersonalData().getMaritalStatus();
        int pointsForMaritalStatus = maritalStatus.getScoringPoints();
        log.info("Martial Status =" + maritalStatus + ScoringUtils.getPointsString(pointsForMaritalStatus));
        return pointsForMaritalStatus;
    }
}
