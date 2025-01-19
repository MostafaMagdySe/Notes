package com.example.demo.Controller;

import com.example.demo.DTO.emailRequest;
import com.example.demo.DTO.UserNewPasswordRequest;
import com.example.demo.DTO.UserProvidedcodeRequest;
import com.example.demo.Services.ResetPasswordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgotPasswordController {
    private final ResetPasswordService resetPasswordService;

    public ForgotPasswordController(ResetPasswordService resetPasswordService) {
        this.resetPasswordService = resetPasswordService;
    }

    @PostMapping("/ResetPassword")
    public ResponseEntity ResetPassword(@RequestBody emailRequest emailRequest) {
        if (resetPasswordService.verifyEmail(emailRequest)) {
            resetPasswordService.saveEmailAndCodeinDB(emailRequest);
            resetPasswordService.sendmail(emailRequest);
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        else
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

}
    @PostMapping("/verifyCode")
    public ResponseEntity verifyCode(@RequestParam String email, @RequestBody UserProvidedcodeRequest userProvidedcodeRequest) {
        if(resetPasswordService.verifyCode(email, userProvidedcodeRequest.getCode())){

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    @PostMapping("/UpdatePassword")
    public ResponseEntity UpdatePassword (@RequestParam String email, @RequestBody UserNewPasswordRequest userNewPasswordRequest){

       if ( resetPasswordService.updateUserPassword(email,userNewPasswordRequest)){
        return new ResponseEntity<>(HttpStatus.OK);
    }
else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
}



    }