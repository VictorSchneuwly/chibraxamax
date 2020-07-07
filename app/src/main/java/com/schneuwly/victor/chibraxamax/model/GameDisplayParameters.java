package com.schneuwly.victor.chibraxamax.model;

/**
 * Parameters of a game
 *
 * @author Victor Schneuwly
 */
public class GameDisplayParameters {
    private boolean touchPoints, showMarks, showGuide, bigScore;


    public GameDisplayParameters(boolean touchPoints, boolean showMarks, boolean showGuide, boolean bigScore) {
        this.touchPoints = touchPoints;
        this.showMarks = showMarks;
        this.showGuide = showGuide;
        this.bigScore = bigScore;
    }

    public boolean touchPointsEnabled() {
        return touchPoints;
    }

    public void setTouchPoints(boolean touchPoints) {
        this.touchPoints = touchPoints;
    }

    public boolean areMarksShown() {
        return showMarks;
    }

    public void setShowMarks(boolean showMarks) {
        this.showMarks = showMarks;
    }

    public boolean isGuideShown() {
        return showGuide;
    }

    public void setShowGuide(boolean showGuide) {
        this.showGuide = showGuide;
    }

    public boolean isScoreBig() {
        return bigScore;
    }

    public void setBigScore(boolean bigScore) {
        this.bigScore = bigScore;
    }
}
