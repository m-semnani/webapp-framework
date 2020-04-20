import { ProductDetailsComponent } from './../product-details/product-details.component';
import { Observable } from "rxjs";
import { ProductService, Product } from "./../service/product.service";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  productList: Observable<Product[]>;

  constructor(private productService: ProductService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.productList = this.productService.getProductList();
  }

  lackProductList() {
    this.productList = this.productService.getLackProductList();
  }

  excessProductList() {
    this.productList = this.productService.getExcessProductList();
  }

  deleteProduct(id: number) {
    this.productService.deleteProduct(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  productDetails(id: number){
    this.router.navigate(['product-details', id]);
  }

  updateProduct(id: number){
    this.router.navigate(['product-update', id]);
  }
}
