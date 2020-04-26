import { ProductService, Product } from '../service/product.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService, User } from '../service/user.service';
import { Observable } from "rxjs";
import { NgForm, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})

export class ProductAddComponent implements OnInit {

  userList: Observable<User[]>;
  isAdmin:boolean = (sessionStorage.getItem('isAdmin') === 'true');

  constructor(private productService: ProductService, private userService: UserService, private router: Router) { }

  ngOnInit() {
      if(this.isAdmin) {   
        console.log('Yes, I am admin, so getting user list')
        this.userList = this.userService.getUserList();
        console.log('user list: ' , this.userList)
        console.log(this.userList);
      }
  }

  saveProduct(regForm: NgForm) {
    let product: Product = new Product();
    product.name = regForm.value.name;
    product.quantity = regForm.value.quantity;
    if(this.isAdmin) {
      product.ownerId  = +(<HTMLSelectElement>document.getElementById('ownerSelect')).value;
    } 

    this.productService.createProduct(product)
    .subscribe
    (data => {
      console.log(data)
      this.gotoList();
    },
      error => console.log(error)
    );
  }

  gotoList() {
    this.router.navigate(['/products']);
  }
}
