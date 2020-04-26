import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService, User } from '../service/user.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {

  id: number;
  user: User;

  constructor(private route: ActivatedRoute,private router: Router,
    private userService: UserService) { }

  ngOnInit() {
    this.user = new User();
    this.id = this.route.snapshot.params['id'];
    
    this.userService.getUser(this.id)
      .subscribe(data => {
        console.log(data)
        this.user = data;
      }, error => console.log(error));
  }

  saveUser(regForm: NgForm) {
    this.userService.updateUser(this.id, regForm.value)
      .subscribe(data => console.log(data), error => console.log(error));
    this.user = new User();
    this.gotoList();
  }

  gotoList() {
    this.router.navigate(['/users']);
  }
}

