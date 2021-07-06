import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Manufacturer } from '../Entities/Manufacturer';
import { ManufacturerService } from '../manufacturer.service';
import { EnumService } from '../enum.service';
import { Country } from '../Entities/Country';
import { Address } from '../Entities/Address';
import { AddressService } from '../address.service';

@Component({
  selector: 'app-create-manufacturer',
  templateUrl: './create-manufacturer.component.html',
  styleUrls: ['./create-manufacturer.component.css']
})
export class CreateManufacturerComponent implements OnInit {
  manufacturer: Manufacturer = new Manufacturer();
  public message:string ="";

  address:Address = new Address();
  countries:Country[];
  addresses:Array<any> = new Array<any>();

  inputStreet:string = "";
  inputCountry:Country = new Country();
 
  constructor(private service: ManufacturerService,private router: Router,private enumService:EnumService,private addressService:AddressService) {
    this.countries = new Array();
   }

  saveManufacturer(){
    this.service.addManufacturer(this.manufacturer).subscribe(data=>{
      console.log(data);
    },error => console.error(error));
    this.goToList();
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
  
    saveManufactuerer(){
      this.service.addManufacturer(this.manufacturer).subscribe(data=>{
      },error => console.error(error));
      this.goToList();
    }
  
    goToList(){
      this.router.navigate(["/manufacturers"]);
    }
  
    async newAddress(){
      this.address.country=this.inputCountry;
      this.address.street=this.inputStreet;
      return await this.addressService.addAddress(this.address).toPromise()
    }
  
    async onSubmit(){
      this.address = await this.newAddress();
      this.manufacturer.address = this.address;
     
      if(this.manufacturer.name.trim()!=""){
        this.manufacturer.address=this.address;
        this.saveManufactuerer();
      }else{
        this.message = "Please enter valid input";
      }
    }
}
