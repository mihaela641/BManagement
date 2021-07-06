import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../Entities/Product';
import { ProductShop } from '../Entities/ProductShop';
import { Shop } from '../Entities/Shop';
import { ProductShopService } from '../product-shop.service';
import { ProductService } from '../product.service';
import { ShopService } from '../shop.service';

@Component({
  selector: 'app-create-product-shop',
  templateUrl: './create-product-shop.component.html',
  styleUrls: ['./create-product-shop.component.css']
})
export class CreateProductShopComponent implements OnInit {
  productShop:ProductShop=new ProductShop();
  message:string = "";

  products:Product[] = new Array();
  shops:Shop[] = new Array();


  constructor(private service:ProductShopService
    ,private productService:ProductService
    ,private shopService:ShopService
    ,private router:Router) { }

  ngOnInit(): void {
    this.getProducts();
    this.getShops();
  }


  getProducts(){
    this.productService.getProductsArray().subscribe(data=>this.products=data);
  }

  getShops(){
    this.shopService.getShopsArray().subscribe(data=>this.shops=data);
  }

  saveProductShop(){
    this.service.addProductShop(this.productShop).subscribe(data=>{
      console.log(data);
    },error=>console.log(error));
    this.goToList();
  }

  goToList(){
    this.router.navigate(["/productsShops"]);
  }

  onSubmit(){
    if(this.productShop.product.id!=0
      &&this.productShop.shop.id!=0
      &&this.productShop.quantity>0){
      this.saveProductShop();
    }else{
      this.message = "Please enter valid input";
    }
  }
}
