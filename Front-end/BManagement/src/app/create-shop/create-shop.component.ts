import { HttpParams } from '@angular/common/http';
import { Component, OnChanges, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddressService } from '../address.service';
import { Address } from '../Entities/Address';
import { Country } from '../Entities/Country';
import { Shop } from '../Entities/Shop';
import { EnumService } from '../enum.service';
import { ShopService } from '../shop.service';

@Component({
  selector: 'app-create-shop',
  templateUrl: './create-shop.component.html',
  styleUrls: ['./create-shop.component.css']
})
export class CreateShopComponent implements OnInit{
  shop: Shop = new Shop();
  address:Address = new Address();



  public message:string ="";
  countries:Country[];
  statuses:Array<string> = ["Active","Closed"];
  addresses:Array<any> = new Array<any>();

  inputStreet:string = "";
  inputCountry:Country = new Country();
  inputStatus:string ="";

  constructor(private service:ShopService,private router:Router, private addressService:AddressService, private enumService:EnumService) { 
    this.countries = new Array();
  }

  ngOnInit(): void {
  this.getAddresses();
  this.getCountries();
  }

  getAddresses(){
    this.addressService.getAddressesArray().subscribe(data=>{
      this.addresses=data
    });
  }

  getCountries(){
    this.enumService.getCountries().subscribe(data=>{
      this.countries=data;
    });
  }

  saveShop(){
    this.service.addShop(this.shop).subscribe(data=>{
    },error => console.error(error));
    this.goToList();
  }

  goToList(){
    this.router.navigate(["/shops"]);
  }

  async newAddress(){
    this.address.country=this.inputCountry;
    this.address.street=this.inputStreet;
    return await this.addressService.addAddress(this.address).toPromise()
  }

  async onSubmit(){
    this.address = await this.newAddress();
    this.shop.address = this.address;
   
    if(this.shop.name.trim()!=""&&
    this.shop.phoneNumber.trim()!=""){
      this.shop.address=this.address;
      this.saveShop();
    }else{
      this.message = "Please enter valid input";
    }
  }
}
