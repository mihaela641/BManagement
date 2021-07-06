import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  orders: Array<any>;
  page:number = 1;
  items:number = 0;
  itemsPerPage:number= 10;
  sortBy:string = '';
  sortDirection:string = '';

  params:HttpParams = new HttpParams().append('page', this.page)
  .append('size', this.itemsPerPage)

  inputCode:string="";
  inputProduct:string ="";
  inputShop:string="";
  inputQuantity:number = 0;
  inputStatus:string="";
  stat:number=0;

 
  constructor(private service:OrderService,private router:Router) { 
    this.orders = new Array<any>();
  }

  ngOnInit(): void {
    this.getOrders();
  }

  getOrders(){
    this.service.getOrders(this.params).subscribe(data=>{
      console.log(data);
      this.orders=data['content'];
      this.items = data['totalElements'];
    });
  }

  deleteOrder(id:number){
    this.service.deleteOrder(id).subscribe(data=>{
      console.log(data);
      this.getOrders();
    },error=>console.log(error));
  }

  updateOrder(id:number){
    this.router.navigate(["/update-order",id]);
  }

  changePage(event:any){
    this.page=event;
    this.params= this.params.set('page',event);
    this.getOrders();
  }

  search(){
    if(this.inputCode.trim()!=""){
      this.params = this.params.set('code',this.inputCode);
    }
    if(this.inputProduct.trim()!=""){
      this.params = this.params.set('product',this.inputProduct);
    }
    if(this.inputShop.trim()!=""){
      this.params = this.params.set('shop',this.inputShop);
    }
    if(this.inputStatus.trim()!=""){
      if(this.inputStatus.toLowerCase()=="active"){
        this.stat=1;
      }else{
        this.stat=0;
      }
      this.params = this.params.set('status',this.stat);
    }
    this.getOrders();
  }

  clear(){
    this.params = this.params.delete('code');
    this.params = this.params.delete('product');
    this.params = this.params.delete('shop');
    this.params = this.params.delete('status');
    this.inputCode="";
    this.inputProduct="";
    this.inputStatus="";
    this.inputShop="";
    this.getOrders();
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
    if(by=="code"){
      this.params = this.params.set('sortBy',"code");
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
    if(by=="manufacturer"){
      this.params = this.params.set('sortBy',"manufacturer");
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

    if(by=="status"){
      this.params = this.params.set('sortBy',"status");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }

    this.getOrders();
  }  
}
