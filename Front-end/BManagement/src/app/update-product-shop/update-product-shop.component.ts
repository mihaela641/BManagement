import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../Entities/Product';
import { ProductShop } from '../Entities/ProductShop';
import { Shop } from '../Entities/Shop';
import { ProductShopService } from '../product-shop.service';
import { ProductService } from '../product.service';
import { ShopService } from '../shop.service';

@Component({
  selector: 'app-update-product-shop',
  templateUrl: './update-product-shop.component.html',
  styleUrls: ['./update-product-shop.component.css']
})
export class UpdateProductShopComponent implements OnInit {
  productShop:ProductShop= new ProductShop();
  id:number=0;
  message:string = "";

  shops:Shop[]=new Array();
  products:Product[]=new Array();

  constructor(private service:ProductShopService
    ,private productService:ProductService
    ,private shopService:ShopService
    ,private router:Router
    ,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.service.getProductShop(this.id).subscribe(data=>{
      this.productShop = data;
      console.log(data);
    });
    this.getProducts();
    this.getShops();
  }

  getProducts(){
    this.productService.getProductsArray().subscribe(data=>this.products=data);
  }

  getShops(){
    this.shopService.getShopsArray().subscribe(data=>this.shops=data);
  }

  onSubmit(){
    if(this.productShop.quantity>0){
      this.service.updateProductShop(this.productShop,this.id).subscribe(data=>{
        this.router.navigate(["/productsShops"]);
      },error=>console.error(error)
      );
     }else{
       this.message = "Please enter valid input";
     }
}
}
