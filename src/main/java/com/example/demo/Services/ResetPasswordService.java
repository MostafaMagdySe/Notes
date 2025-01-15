package com.example.demo.Services;

import com.example.demo.DTO.ResetPasswordRequest;
import com.example.demo.DTO.UserNewPasswordRequest;
import com.example.demo.Entities.resetpassword;
import com.example.demo.Entities.Users;
import com.example.demo.Repository.ResetPasswordRepo;
import com.example.demo.Repository.UserRepo;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    public Boolean verifyEmail (ResetPasswordRequest resetPasswordRequest){
        return userRepo.findByemail(resetPasswordRequest.getEmail()) != null;
    }

    //@EventListener(ApplicationReadyEvent.class)
    public void sendmail(ResetPasswordRequest resetPasswordRequest){
        resetpassword resetPassword = resetPasswordRepo.findByemail(resetPasswordRequest.getEmail());
        emailSenderService.SendEmail(resetPasswordRequest.getEmail(),
                "Don't reply to this Message",
                "you have Requested to reset Your password on our Notes Website.." +
                        " if you didn't ask for Resetting Password, ignore this Message," +
                        " your Verification Code is: "+resetPassword.getCode());


    }
    public void saveEmailAndCodeinDB (ResetPasswordRequest resetPasswordRequest){
        resetpassword resetPass = new resetpassword();
        resetPass.setEmail(resetPasswordRequest.getEmail());
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
