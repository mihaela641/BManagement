import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ShopService } from '../shop.service';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  shops: Array<any>;
  page:number = 1;
  items:number = 0;
  itemsPerPage:number= 10;
  sortBy:string = '';
  sortDirection:string = '';

  params:HttpParams = new HttpParams().append('page', this.page)
  .append('size', this.itemsPerPage)


  inputName:string="";
  inputAddress:string ="";
  inputPhoneNumber:string="";
  inputStatus:string="";
  stat:number=0;

  constructor(private service:ShopService,private router:Router) { 
    this.shops = new Array<any>();
  }

  ngOnInit(): void {
    this.getShops();
  }

  getShops(){
    console.log(this.params);
    this.service.getShops(this.params).subscribe(data=>{
      console.log(data);
      this.shops=data['content'];
      this.items = data['totalElements'];
    });
  }

  deleteShop(id:number){
    this.service.deleteShop(id).subscribe(data=>{
      console.log(data);
      this.getShops();
    },error=>console.log(error));
  }

  updateShop(id:number){
    this.router.navigate(["/update-shop",id]);
  }

  changePage(event:any){
    this.page=event;
    this.params= this.params.set('page',event);
    this.getShops();
  }

  search(){
    if(this.inputName.trim()!=""){
      this.params = this.params.set('name',this.inputName);
    }
    if(this.inputStatus.trim()!=""){
      if(this.inputStatus.toLowerCase()=="active"){
        this.stat=1;
      }else{
        this.stat=0;
      }
      this.params = this.params.set('status',this.stat);
    }
    if(this.inputPhoneNumber.trim()!=""){
      this.params = this.params.set('phoneNumber',this.inputPhoneNumber);
    }
    if(this.inputAddress.trim()!=""){
      this.params = this.params.set('address',this.inputAddress);
    }
    this.getShops();
  }

  clear(){
    this.params = this.params.delete('name');
    this.params = this.params.delete('status');
    this.params = this.params.delete('phoneNumber');
    this.params = this.params.delete('address');
    this.inputName="";
    this.inputAddress="";
    this.inputStatus="";
    this.inputPhoneNumber="";
    this.getShops();
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
    if(by=="name"){
      this.params = this.params.set('sortBy',"name");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    if(by=="address"){
      this.params = this.params.set('sortBy',"address");
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

    this.getShops();
  }
}
