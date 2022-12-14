package com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.InterviewerRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * REST service responsible for {@link Interviewer} related business logic operations
 */
@Service
public class InterviewerServiceImpl implements InterviewerService {

    @Autowired
    private InterviewerRepository interviewerDao;

    /**
     * @see InterviewerService#getInterviewerList()
     */
    @Transactional(readOnly = true)
    @Override
    public List<Interviewer> getInterviewerList() {
        return interviewerDao.findAll();
    }

    /**
     * @see InterviewerService#getInterviewer(Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public Interviewer getInterviewer(Integer id) {
        return interviewerDao.findById(id).orElse(null);
    }

    /**
     * @see InterviewerService#createInterviewer(Interviewer)
     */
    @Transactional
    @Override
    public Interviewer createInterviewer(Interviewer interviewer) {
        return interviewerDao.save(interviewer);
    }

    /**
     * @see InterviewerService#updateInterviewer(Interviewer)
     */
    @Transactional
    @Override
    public Interviewer updateInterviewer(Interviewer interviewer) {
        Interviewer interviewerFromDB = interviewerDao.findById(interviewer.getId()).orElse(null);
        if (interviewerFromDB != null) {
            interviewerFromDB.setName(interviewer.getName());
            interviewerFromDB.setDescription(interviewer.getDescription());
            return interviewerDao.save(interviewerFromDB);
        }
        return null;
    }

    /**
     * @see InterviewerService#deleteInterviewer(Integer)
     */
    @Transactional
    @Override
    public void deleteInterviewer(Integer id) {
        interviewerDao.deleteById(id);
    }

    /**
     * @see InterviewerService#createInterviewerAvailability(InterviewerAvailability)
     */
    @Override
    public InterviewerAvailability createInterviewerAvailability(InterviewerAvailability interviewerAvailability) {

        return null;
    }

    /**
     * @see InterviewerService#deleteInterviewerAvailability(Integer)
     */
    @Override
    public void deleteInterviewerAvailability(Integer id) {


    }
}
