import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService, Product } from '../service/product.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-product-update',
  templateUrl: './product-update.component.html',
  styleUrls: ['./product-update.component.css']
})

export class ProductUpdateComponent implements OnInit {

  id: number;
  product: Product;
  public submitted: boolean = false;
  isAdmin: boolean = (sessionStorage.getItem('isAdmin') === 'true');

  constructor(private route: ActivatedRoute, private router: Router, private productService: ProductService) { }

  ngOnInit() {
    this.product = new Product();
    this.id = this.route.snapshot.params['id'];

    this.productService.getProduct(this.id)
      .subscribe(data => {
        console.log(data)
        this.product = data;
      }, error => console.log(error));
  }

  saveProduct(regForm: NgForm) {
    this.productService.updateProduct(this.id, this.product)
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

