package pl.mariodev.creditapp.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.mariodev.creditapp.core.model.SelfEmployed;
import pl.mariodev.creditapp.core.scoring.ScoringCalculator;
import pl.mariodev.creditapp.core.scoring.ScoringUtils;

public class SelfEmployedScoringCalculator implements ScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(SelfEmployedScoringCalculator.class);


    @Override
    public int calculate(SelfEmployed selfEmployed) {
        if (selfEmployed.getYearsSinceFounded() < 2) {
            log.info("Yeras since founded = " + selfEmployed.getYearsSinceFounded() + ScoringUtils.getPointsString(-200));
            return -200;
        }
        return 0;
    }

}
