import { ProductService, Product } from '../service/product.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService, User } from '../service/user.service';
import { Observable } from "rxjs";

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})

export class ProductAddComponent implements OnInit {

  product: Product = new Product();
  submitted = false;
  userList: Observable<User[]>;
  isAdmin:boolean = (sessionStorage.getItem('isAdmin') === 'true');

  constructor(private productService: ProductService, private userService: UserService, private router: Router) { }

  ngOnInit() {
      if(this.isAdmin) {
        console.log('hey, is admin :)')
        this.userList = this.userService.getUserList();
        console.log(this.userList);
      }
  }

  newProduct(): void {
    this.submitted = false;
    this.product = new Product();
  }

  save() {
    this.product.ownerId  = +(<HTMLSelectElement>document.getElementById('ownerSelect')).value;
    this.productService.createProduct(this.product)
      .subscribe(data => console.log(data), error => console.log(error));
    this.product = new Product();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/products']);
  }
}
