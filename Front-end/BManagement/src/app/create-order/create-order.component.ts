import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from '../Entities/Order';
import { Product } from '../Entities/Product';
import { Shop } from '../Entities/Shop';
import { OrderService } from '../order.service';
import { ProductService } from '../product.service';
import { ShopService } from '../shop.service';

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {
  order:Order = new Order();
  message:string = "";

  products:Product[] = new Array();
  shops:Shop[] = new Array();
  inputStatus:string ="";
  statuses:Array<string> = ["Active","Closed"];

  constructor(private service:OrderService,private router:Router,private productService:ProductService,
   private shopService:ShopService ) { }

  ngOnInit(): void {
    this.getShops();
    this.getProducts();
  }

  getShops(){
    this.shopService.getShopsArray().subscribe(data=>this.shops=data);
  }

  getProducts(){
    this.productService.getProductsArray().subscribe(data=>this.products=data);
  }

  goToList(){
    this.router.navigate(["/orders"]);
  }

  saveOrder(){
    this.service.addOrder(this.order).subscribe(data=>{
      console.log(data);
    },error => console.error(error));
    this.goToList();
  }

  onSubmit(){
    if(this.order.code.trim()!=""
      &&this.order.product.name.trim()!=""
      &&this.order.shop.name.trim()!=""
      &&this.order.quantity>0
      ){
      this.saveOrder();
    }else{
      this.message = "Please enter valid input";
    }
  }
}
