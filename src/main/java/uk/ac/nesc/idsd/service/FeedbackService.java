package uk.ac.nesc.idsd.service;

import uk.ac.nesc.idsd.bean.FeedbackBean;
import uk.ac.nesc.idsd.model.Feedback;
import uk.ac.nesc.idsd.service.exception.ServiceException;

import java.util.List;

public interface FeedbackService {
    void addFeedback(String username, Feedback feedback) throws ServiceException;

    void updateFeedback(String username, Long feedbackId, Feedback feedback) throws ServiceException;

    void deleteFeedback(String username, Long feedbackId) throws ServiceException;

    List<FeedbackBean> getAllFeedbackBean() throws ServiceException;

    FeedbackBean getFeedbackBeanById(Long feedbackId) throws ServiceException;

    Feedback getFeedbackById(Long feedbackId);
}
