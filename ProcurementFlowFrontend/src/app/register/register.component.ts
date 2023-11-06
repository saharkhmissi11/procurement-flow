import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationResponse } from 'classes/AuthenticationResponse';
import { Employee } from 'classes/Employee';
import { VerificationRequest } from 'classes/VerificationRequest';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: any = {};
  authResponse: any = {};
  message = '';
  otpCode = '';

  constructor(private authService: AuthenticationService,private router: Router) { }

  ngOnInit(): void {
  }
  registerUser() {
    this.message = '';
    this.authService.registerUser(this.user)
      .subscribe({
        next: (response) => {
          if (response) {
            this.authResponse = response;
          } else {
            // inform the user
            this.message = 'Account created successfully\nYou will be redirected to the Login page in 3 seconds';
            setTimeout(() => {
              this.router.navigate(['login']);
            }, 3000)
          }
        }
      });

  }
  verifyTfa() {
    this.message = '';
    const verifyRequest: VerificationRequest = {
      email: this.user.email,
      code: this.otpCode
    };
    this.authService.verifyCode(verifyRequest)
      .subscribe({
        next: (response) => {
          this.message = 'Account created successfully\nYou will be redirected to the Welcome page in 3 seconds';
          setTimeout(() => {
            localStorage.setItem('token', response.accessToken as string);
            this.router.navigate(['welcome']);
          }, 3000);
        }
      });
  }

}
