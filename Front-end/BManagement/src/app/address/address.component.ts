import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddressService } from '../address.service';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {

  addresses: Array<any>;
  page:number = 1;
  items:number = 0;
  itemsPerPage:number= 10;
  sortBy:string = '';
  sortDirection:string = '';

  nameChecked = false;
  codeChecked = false;
  inputStreet = "";
  inputCountry = 0;
  
  street: string ='';
  countryId =0;

  params:HttpParams = new HttpParams().append('page', this.page)
  .append('size', this.itemsPerPage)
  .append('sortBy',this.sortBy)
  .append('sortDirection', this.sortDirection)
  .append('street',this.street)

  constructor(private service: AddressService,private router:Router) { 
    this.addresses= new Array<any>();
  }

  ngOnInit(): void {
    this.getAddresses();
  }

  getAddresses(){
    console.log(this.params);
    this.service.getAddresses(this.params).subscribe(data=>{
      console.log(data);
      this.addresses=data['content'];
      this.items = data['totalElements'];
    });
  }

  deleteAddress(id:number){
    this.service.deleteAddress(id).subscribe(data=>{
      console.log(data);
      this.getAddresses();
    },error=>console.log(error));
  }

  updateAddress(id:number){
    this.router.navigate(["/update-address",id]);
  }

  changePage(event:any){
    this.page=event;
    this.params= this.params.set('page',event);
    this.getAddresses();
  }

  search(){
    if(this.inputStreet.trim()!=""){
      this.params = this.params.set('street',this.inputStreet);
    }
   
    this.getAddresses();
  }
  clear(){
    this.params = this.params.delete('street');
    this.params = this.params.delete('countryId');
    this.inputStreet="";
    this.getAddresses();
  }
  

}
