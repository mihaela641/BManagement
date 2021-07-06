import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddressService } from '../address.service';
import { Address } from '../Entities/Address';

@Component({
  selector: 'app-create-address',
  templateUrl: './create-address.component.html',
  styleUrls: ['./create-address.component.css']
})
export class CreateAddressComponent implements OnInit {
  address: Address = new Address();
  message:string = "";

  constructor(private service: AddressService,private router: Router) { }

  ngOnInit(): void {
  }

  saveAddress(){
    this.service.addAddress(this.address).subscribe(data=>{
      console.log(data);
    },error => console.error(error));
    this.goToList();
  }

  goToList(){
    this.router.navigate(["/addresses"]);
  }

  onSubmit(){
    if(this.address.street.trim()!=""){
      this.saveAddress();
      
    }else{
      this.message = "Please enter valid input";
    }
    console.log(this.address);
  }
}

