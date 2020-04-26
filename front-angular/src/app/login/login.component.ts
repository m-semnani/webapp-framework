import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  invalidLogin = false
  errMsg = ''

  constructor(private router: Router, private loginservice: AuthenticationService) { }

  ngOnInit() {
  }

  loginUser(regForm: NgForm) {
    (this.loginservice.authenticate(regForm.value.username, regForm.value.password).subscribe(
      data => {
        this.router.navigate([''])
        this.invalidLogin = false
      },
      error => {
        this.invalidLogin = true
        this.errMsg = 'username or password is wrong!'
      }
    )
    );
  }
}
