package com.example.demo.Services;

import com.example.demo.DTO.emailRequest;
import com.example.demo.DTO.UserNewPasswordRequest;
import com.example.demo.Entities.resetpassword;
import com.example.demo.Entities.Users;
import com.example.demo.Repository.ResetPasswordRepo;
import com.example.demo.Repository.UserRepo;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Data
@Service
public class ResetPasswordService {

    private final UserRepo userRepo;
    private final EmailSenderService emailSenderService;
    private final ResetPasswordRepo resetPasswordRepo;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public ResetPasswordService(UserRepo userRepo, EmailSenderService emailSenderService, ResetPasswordRepo resetPasswordRepo){

        this.userRepo=userRepo;
        this.emailSenderService=emailSenderService;
        this.resetPasswordRepo = resetPasswordRepo;
    }

    public Boolean verifyEmail (emailRequest emailRequest){
        return userRepo.findByemail(emailRequest.getEmail()) != null;
    }

    //@EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void sendmail(emailRequest emailRequest){
        resetpassword resetPassword = resetPasswordRepo.findByemail(emailRequest.getEmail());
        emailSenderService.SendEmail(emailRequest.getEmail(),
                "Don't reply to this Message",
                "you have Requested to reset Your password on our Notes Website.." +
                        " if you didn't ask for Resetting Password, ignore this Message," +
                        " your Verification Code is: "+resetPassword.getCode());


    }
    @Transactional
    public void saveEmailAndCodeinDB (emailRequest emailRequest){
        resetpassword resetPass = new resetpassword();
        resetPass.setEmail(emailRequest.getEmail());
        resetPass.setCode(generateVerificationCode());
resetPasswordRepo.save(resetPass);
    }
    public String generateVerificationCode(){

        return RandomStringUtils.randomAlphabetic(10);
    }
    public boolean verifyCode(String email, String userProvidedCode) {

        resetpassword resetPassword = resetPasswordRepo.findByemail(email);

        return resetPassword.getCode().equals(userProvidedCode);


    }
    public void codeHandlingInDB (){

    }

    public Boolean updateUserPassword(String email, UserNewPasswordRequest userNewPasswordRequest) {

        Users user = userRepo.findByemail(email);
        if (user == null) {
            return false;
        }
        try {

            user.setPassword(encoder.encode(userNewPasswordRequest.getPassword()));
            userRepo.save(user);
            return true;
        } catch (Exception e) {
            System.err.println("Error updating password: " + e.getMessage());
            return false;
        }
    }

















}
