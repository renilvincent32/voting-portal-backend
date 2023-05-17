package com.voting.app.security;

import com.voting.app.dao.VoterRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class VoterDetailsService implements UserDetailsService {

    private final VoterRepository voterRepository;

    public VoterDetailsService(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String collegeId) throws UsernameNotFoundException {
        return voterRepository.findByCollegeId(collegeId).map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid college ID: " + collegeId));
    }
}
