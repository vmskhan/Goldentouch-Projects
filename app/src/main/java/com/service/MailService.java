package com.service;
import java.util.List;

import com.util.Mail;
import com.util.Report;
public interface MailService {
    public void sendEmail(Mail mail);
    public void sendEmail(Mail mail, List<Report> reports);
}
