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

  ownerName: string;
  userList: Observable<User[]>;
  isAdmin:boolean = (sessionStorage.getItem('isAdmin') === 'true');

  constructor(private productService: ProductService, private userService: UserService, private router: Router) { }

  ngOnInit() {
      if(this.isAdmin) {
        this.userList = this.userService.getUserList();
        this.ownerName = this.userList[0].username;
        console.log(this.userList);
      }
  }

  updateOwnerName(text: string) {
    this.ownerName = text;
  }

  saveProduct(regForm: NgForm) {
    let product: Product = new Product();
    product.name = regForm.value.name;
    product.quantity = regForm.value.quantity;
    if(this.isAdmin) {
      product.ownerId  = +(<HTMLSelectElement>document.getElementById('ownerSelect')).value;
      product.ownerName  = this.ownerName;
    } else {
      product.ownerName = sessionStorage.getItem('username');
    }

    this.productService.createProduct(product)
      .subscribe(data => console.log(data), error => console.log(error));

    this.gotoList();
  }

  gotoList() {
    this.router.navigate(['/products']);
  }
}
