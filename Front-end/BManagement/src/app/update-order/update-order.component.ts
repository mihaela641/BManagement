import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Manufacturer } from '../Entities/Manufacturer';
import { Order } from '../Entities/Order';
import { Product } from '../Entities/Product';
import { Shop } from '../Entities/Shop';
import { ManufacturerService } from '../manufacturer.service';
import { OrderService } from '../order.service';
import { ProductService } from '../product.service';
import { ShopService } from '../shop.service';

@Component({
  selector: 'app-update-order',
  templateUrl: './update-order.component.html',
  styleUrls: ['./update-order.component.css']
})
export class UpdateOrderComponent implements OnInit {
  order:Order = new Order();
  id:number = 0;

  products:Product[] = new Array();
  shops:Shop[] = new Array();

  inputStatus:string ="";
  

  constructor(private service:OrderService,private route:ActivatedRoute,private router: Router,
    private shopService:ShopService, private productService:ProductService) { }

 
    ngOnInit(): void {
      this.id = this.route.snapshot.params['id'];
      this.service.getOrder(this.id).subscribe(data=>{
        this.order = data;
        console.log(data);
      });
      this.getProducts();
      this.getShops();
    }

  getShops(){
    this.shopService.getShopsArray().subscribe(data=>this.shops=data);
  }

  getProducts(){
    this.productService.getProductsArray().subscribe(data=>this.products=data);
  }

  onSubmit(){
    this.service.updateOrder(this.order,this.id).subscribe(data=>{
      this.router.navigate(["/orders"]);
    },error=>console.error(error)
    );
  }

}
