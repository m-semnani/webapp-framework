import { UserDetailsComponent } from './../user-details/user-details.component';
import { Observable } from "rxjs";
import { UserService, User } from "./../service/user.service";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  userList: Observable<User[]>;

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.userList = this.userService.getUserList();
  }

  deleteUser(id: number) {
    this.userService.deleteUser(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  userDetails(id: number){
    this.router.navigate(['user-details', id]);
  }

  updateUser(id: number){
    this.router.navigate(['user-update', id]);
  }
}
