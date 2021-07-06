import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductShopService } from '../product-shop.service';

@Component({
  selector: 'app-product-shop',
  templateUrl: './product-shop.component.html',
  styleUrls: ['./product-shop.component.css']
})
export class ProductShopComponent implements OnInit {
  productsShops: Array<any>;
  page:number = 1;
  items:number = 0;
  itemsPerPage:number= 10;

  inputShop:string = "";
  inputProduct:string="";

  params:HttpParams = new HttpParams().append('page', this.page)
  .append('size', this.itemsPerPage)

  constructor(private service:ProductShopService, private router:Router) {
    this.productsShops = new Array();
   }

  ngOnInit(): void {
    this.getProductsShops();
  }

  getProductsShops(){
    this.service.getProductsShops(this.params).subscribe(data=>{
      console.log(data);
      this.productsShops=data['content'];
      this.items = data['totalElements'];
    });
  }

  deleteProductShop(id:number){
    this.service.deleteProductShop(id).subscribe(data=>{
      console.log(data);
      this.getProductsShops();
    },error=>console.log(error));
  }

  
  updateProductShop(id:number){
    this.router.navigate(["/update-productShop",id]);
  }

  changePage(event:any){
    this.page=event;
    this.params= this.params.set('page',event);
    this.getProductsShops();
  }

  sort(by:any){
    if(by=="id"){
      this.params = this.params.set('sortBy',"id");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    if(by=="product"){
      this.params = this.params.set('sortBy',"product");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    if(by=="shop"){
      this.params = this.params.set('sortBy',"shop");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    if(by=="quantity"){
      this.params = this.params.set('sortBy',"quantity");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    this.getProductsShops();
  }

  clear(){
    this.params = this.params.delete('product');
    this.params = this.params.delete('shop');
    this.inputProduct="";
    this.inputShop = "";
    this.getProductsShops();
  }
  search(){
    if(this.inputProduct.trim()!=""){
      this.params = this.params.set('product',this.inputProduct);
    }
    if(this.inputShop.trim()!=""){
      this.params = this.params.set('shop',this.inputShop);
    }
    console.log(this.params);
    this.getProductsShops();
  }
}
