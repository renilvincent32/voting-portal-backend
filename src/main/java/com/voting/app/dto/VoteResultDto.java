package com.voting.app.dto;

import java.util.List;

public class VoteResultDto {

    private List<CandidateData> candidateData;
    private List<WinnerData> winnerData;

    public List<CandidateData> getCandidateData() {
        return candidateData;
    }

    public VoteResultDto setCandidateData(List<CandidateData> candidateData) {
        this.candidateData = candidateData;
        return this;
    }

    public List<WinnerData> getWinnerData() {
        return winnerData;
    }

    public VoteResultDto setWinnerData(List<WinnerData> winnerData) {
        this.winnerData = winnerData;
        return this;
    }

    public static class WinnerData {

        private String designationName;
        private String candidateName;

        public String getDesignationName() {
            return designationName;
        }

        public WinnerData setDesignationName(String designationName) {
            this.designationName = designationName;
            return this;
        }

        public String getCandidateName() {
            return candidateName;
        }

        public WinnerData setCandidateName(String candidateName) {
            this.candidateName = candidateName;
            return this;
        }
    }

    public static class CandidateData {

        private String candidateName;
        private int voteCount;

        public String getCandidateName() {
            return candidateName;
        }

        public CandidateData setCandidateName(String candidateName) {
            this.candidateName = candidateName;
            return this;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public CandidateData setVoteCount(int voteCount) {
            this.voteCount = voteCount;
            return this;
        }
    }
}
