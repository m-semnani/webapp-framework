import { UserService, User } from '../service/user.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.css']
})

export class UserAddComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {}

  saveUser(regForm: NgForm) {
    this.userService.createUser(regForm.value)
      .subscribe(data => console.log(data), error => console.log(error));
    this.gotoList();
  }

  gotoList() {
    this.router.navigate(['/users']);
  }
}
